package com.dorset.coffeeshop.util;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceUtil {

    public static final String LANGUAGE = "sk";
    public static final String COUNTRY = "SK";

    public static String refectorEuroCurrency(String price) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY));
        String euroCurrency = format.format(convertToDouble(price));
        return euroCurrency;
    }

    private static Double convertToDouble(String price) {
        return Double.parseDouble(price);
    }
}
