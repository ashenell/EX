package types;

import java.math.BigDecimal;

public class FloatDecomposition {
    public static void main(String[] args) {
        float[] values = {1, 2, 3, 4, 5, 6, 0.5f, 0.25f, 0.75f, 0.1f};

        for (float value : values) {
            int bits = Float.floatToIntBits(value);

            int exponent = ((bits >> 23) & 0xFF) - 127;
            int mantissa = bits & 0x7FFFFF;
            float significand = 1.0f + (mantissa / Float.valueOf(1 << 23));

            System.out.printf("%s = %s x 2^%s (%s)\n",
                    value, new BigDecimal(Float.toString(significand)),
                    exponent, new BigDecimal(Float.toString(value)));
        }
    }

}
