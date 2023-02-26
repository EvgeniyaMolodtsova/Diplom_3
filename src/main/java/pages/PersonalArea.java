package pages;

import io.qameta.allure.Step;
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

    @Step("проверка, что на экране есть кнопка выхода")
    public boolean exitButtonIsDisplayed() {
        return driver.findElement(exitButton).isDisplayed();
    }

    @Step("клик по кнопке выхода")
    public void clickToExitButton() {
        driver.findElement(exitButton).click();
    }

    @Step("клик по кнопке Конструктор")
    public void clickToConstructor() {
        driver.findElement(constructor).click();
    }

    @Step("клик по кнопке логотипу")
    public void clickToLogo() {
        driver.findElement(logo).click();
    }

    @Step("ожидание кликабельности кнопки Выход")
    public void waitLoad() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(exitButton));
    }
}
