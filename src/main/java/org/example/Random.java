package org.example;

import org.apache.commons.lang3.RandomStringUtils;

public class Random {
    public static String getRandom(int length) {
        boolean useLetters = true;
        boolean useNumbers = false;

        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
