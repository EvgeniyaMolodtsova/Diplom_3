package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalArea {

    private final WebDriver driver;

    public PersonalArea(WebDriver driver) {
        this.driver = driver;
    }

    ////кнопка "Выход"
    private final By exitButton = By.xpath(".//button[text() = 'Выход']");

    //кнопка "Конструктор" в шапке страницы
    private final By constructor = By.linkText("Конструктор");

    //кнопка логотипа в шапке страницы
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    public boolean exitButtonIsDisplayed() {
        return driver.findElement(exitButton).isDisplayed();
    }

    public void clickToExitButton() {
        driver.findElement(exitButton).click();
    }

    public void clickToConstructor() {
        driver.findElement(constructor).click();
    }

    public void clickToLogo() {
        driver.findElement(logo).click();
    }

    public void waitLoad() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(exitButton));
    }
}
