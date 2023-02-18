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

        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");


        return new ChromeDriver(options);
    }
}
