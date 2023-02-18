package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Drivers {
    public static WebDriver getChromeDriver() {
        // уставливаем путь до локального драйвера хрома
        System.setProperty("webdriver.chrome.driver", "/Users/pingvina/WebDriver/bin/chromedriver");

        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");


        return new ChromeDriver(options);
    }

    public static WebDriver getYandexDriver() {
        // уставливаем путь до локального драйвера хрома
        System.setProperty("webdriver.chrome.driver", "/Users/pingvina/WebDriver/bin/chromedriver_108_0_5359_22");

        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        return new ChromeDriver(options);
    }
}
