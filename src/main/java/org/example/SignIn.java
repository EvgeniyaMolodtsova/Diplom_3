package org.example;

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

    //переход на регистрацию
    private final By signUpButton = By.linkText("Зарегистрироваться");


    private final By restorePassword = By.linkText("Восстановить пароль");

    public void waitForSignUpIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(signInButton));
    }

    public boolean buttonSignInIsDisplayed(){
        return driver.findElement(signInButton).isDisplayed();
    }

    public void setEmail(String userEmail){
        driver.findElement(email).sendKeys(userEmail);
    }

    public void setPassword(String userPassword){
        driver.findElement(password).sendKeys(userPassword);
    }

    public void clickToSignInButton(){
        driver.findElement(signInButton).click();
    }

    public void clickToSignUpButton(){
        driver.findElement(signUpButton).click();
    }

    public void fillSignInForm(String userEmail, String userPassword){
        setEmail(userEmail);
        setPassword(userPassword);
        clickToSignInButton();
    }
}
