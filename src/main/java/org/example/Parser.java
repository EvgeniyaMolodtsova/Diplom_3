package org.example;

public class Parser {

    public static int parseInt(Object object) {
        try {
            Long result = (Long) object;

            return Math.round(result);
        } catch (Exception e) {
            double result = (double) object;

            return (int) Math.round(result);
        }
    }
}
