package utils;

import io.qameta.allure.Step;

public class Parser {

    @Step("преобразование неизвестного числового типа в известный")
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
