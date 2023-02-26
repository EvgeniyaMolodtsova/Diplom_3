package utils;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class Random {
    @Step("генерация рандомной строки")
    public static String getRandom(int length) {
        boolean useLetters = true;
        boolean useNumbers = false;

        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
