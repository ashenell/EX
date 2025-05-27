package exceptions.numbers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class NumberConverter {
    private final Properties props = new Properties();
    private final String language;

    public NumberConverter(String lang) {
        this.language = lang;
        String filePath = "src/exceptions/numbers/numbers_" + lang + ".properties";
        FileInputStream fileSteam = null;
        try {
            fileSteam = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(fileSteam, StandardCharsets.ISO_8859_1);
            props.load(reader);
        }
        catch (FileNotFoundException e) {
            throw new MissingLanguageFileException(lang, e);
        }
        catch (Exception e) {
            throw new BrokenLanguageFileException(lang, e);
        }
        finally {
            closeQuietly(fileSteam);
        }
    }


    private void closeQuietly(FileInputStream is) {
        if (is == null) {
            return;
        }
        try {
            is.close();
        }
        catch (IOException e) {
            throw new BrokenLanguageFileException(language, e);
        }
    }

    public String numberInWords(int number) {
        String prop = props.getProperty(String.valueOf(number));
        if (prop != null) {
            return prop;
        }
        if (number == 0) {
            throw new MissingTranslationException("No translation found for " + number);
        }

        return convertNumber(number);
    }

    private String convertNumber(int number) {
        if (number < 10) {
            return getDigit(number);
        } else if (number < 20) {
            return getTeens(number);
        } else if (number < 100) {
            return getTens(number);
        } else {
            return getHundreds(number);
        }
    }

    private String getDigit(int number) {
        String val = props.getProperty(String.valueOf(number));
        if (val == null) {
            throw new MissingTranslationException("No word  " + number);
        }
        return val;
    }

    private String getTeens(int number) {
        String direct = props.getProperty(String.valueOf(number));
        if (direct != null) {
            return direct;
        }
        int ones = number % 10;
        String digitPart = getDigit(ones);
        String teenSuffix = props.getProperty("teen", "");
        return digitPart + teenSuffix;
    }

    private String getTens(int number) {
        String prop = props.getProperty(String.valueOf(number));
        if (prop != null) {
            return prop;
        }
        int tensVal = number / 10 * 10;
        int ones = number % 10;

        String tensWord = props.getProperty(String.valueOf(tensVal));
        if (tensWord == null) {
            String digitWord = getDigit(tensVal / 10);
            String tensSuffix = props.getProperty("tens-suffix", "");
            tensWord = digitWord + tensSuffix;
        }

        if (ones == 0) {
            return tensWord;
        } else {
            String delimiter = getTensDelimiterFallback();
            return tensWord + delimiter + getDigit(ones);
        }
    }

    private String getTensDelimiterFallback() {
        String prop = props.getProperty("tens-delimiter");
        if (prop != null) {
            return prop;
        }

        switch (language) {
            case "et":
                return " ";
            case "cu":
                return "DELIMITER1";
            default:
                return "-";
        }
    }

    private String getHundreds(int number) {
        int hundredDigit = number / 100;
        int remainder = number % 100;

        String digitWord = getDigit(hundredDigit);
        String hundredWord = props.getProperty("hundred", "");


        String combined = combineHundreds(digitWord, hundredWord);

        if (remainder == 0) {
            return combined;
        } else {
            String connector = getHundredsConnectorFallback();
            return combined + connector + convertNumber(remainder);
        }
    }

    private String combineHundreds(String digitWord, String hundredWord) {
        String props = this.props.getProperty("hundred-delimiter");
        if (props != null) {
            return digitWord + props + hundredWord;
        }
        if ("et".equals(language)) {
            if ("üks".equals(digitWord) && "sada".equals(hundredWord)) {
                return "ükssada";
            }
            return digitWord + hundredWord;
        } else if ("cu".equals(language)) {
            return digitWord + "DELIMITER2" + hundredWord;
        } else {
            return digitWord + " " + hundredWord;
        }
    }

    private String getHundredsConnectorFallback() {
        String props = this.props.getProperty("hundred-connector");
        if (props != null) {
            return props;
        }

        switch (language) {
            case "cu":
                return "DELIMITER3";
            default:
                return " ";
        }
    }
}
