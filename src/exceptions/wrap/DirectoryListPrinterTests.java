package exceptions.wrap;

import org.junit.jupiter.api.Test;

public class DirectoryListPrinterTests {

    @Test
    public void readingThrowsExample() {
        new DirectoryListPrinter().printDirectoryList();
    }

}
