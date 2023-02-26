package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn {

    private final WebDriver driver;

    public SignIn(WebDriver driver) {
        this.driver = driver;
    }

    //поле ввода email
    private final By email = By.name("name");

    //поле ввода пароля
    private final By password = By.name("Пароль");

    //кнопка "войти"
    private final By signInButton = By.xpath(".//button[text() = 'Войти']");

    @Step("ожидание кликбельности кнопки Войти")
    public void waitForSignInIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(signInButton));
    }

    @Step("проверка, что кнопка Войти есть на экране")
    public boolean buttonSignInIsDisplayed() {
        return driver.findElement(signInButton).isDisplayed();
    }

    @Step("ввод Email в поле")
    public void setEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    @Step("ввод пароля в поле")
    public void setPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }

    @Step("клик по кнопке Войти")
    public void clickToSignInButton() {
        driver.findElement(signInButton).click();
    }

    @Step("ввод Email и пароля, клик по кнопке Войти")
    public void fillSignInForm(String userEmail, String userPassword){
        setEmail(userEmail);
        setPassword(userPassword);
        clickToSignInButton();
    }
}
