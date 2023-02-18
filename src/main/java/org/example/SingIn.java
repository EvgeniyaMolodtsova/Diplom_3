package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SingIn {

    private WebDriver driver;

    private By email = By.name("name");

    private By password = By.name("Пароль");

    private By singInButton = By.className("button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa");

    private By singUpButton = By.linkText("Зарегистрироваться");

    public void waitForSingUpIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(singInButton));
    }

    public void setEmail(String userEmail){
        driver.findElement(email).sendKeys(userEmail);
    }

    public void setPassword(String userPassword){
        driver.findElement(password).sendKeys(userPassword);
    }

    public void clickToSingInButton(){
        driver.findElement(singInButton).click();
    }

    public void clickToSingUpButton(){
        driver.findElement(singUpButton).click();
    }

    public void fillSingInForm(String userEmail, String userPassword){
        setEmail(userEmail);
        setPassword(userPassword);
        clickToSingInButton();
    }
}
