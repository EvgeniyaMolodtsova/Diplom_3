package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Drivers {
    @Step("вызов Chrome драйвера")
    public static WebDriver getChromeDriver() {
        // уставливаем путь до локального драйвера хрома
        System.setProperty("webdriver.chrome.driver", "/Users/pingvina/WebDriver/bin/chromedriver");

        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }

    @Step("вызов Яндекс драйвера")
    public static WebDriver getYandexDriver() {
        // уставливаем путь до локального драйвера хрома
        System.setProperty("webdriver.chrome.driver", "/Users/pingvina/WebDriver/bin/chromedriver_108_0_5359_22");

        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        return new ChromeDriver(options);
    }
}
