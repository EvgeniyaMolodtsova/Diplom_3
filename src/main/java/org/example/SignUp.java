package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {

    private WebDriver driver;

    public SignUp(WebDriver driver){
        this.driver = driver;
    }

    //поле ввода имени
    private By name = By.xpath(".//label[text() = 'Имя']");

    //поле ввода Email
    private By email = By.xpath(".//label[text() = 'Email']");

    //поле ввода пароля
    private By password = By.xpath(".//label[text() = 'Пароль']");

    //кнопка зарегистрироваться
    private By singUp = By.className("button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa");

    //
    private By messageAboutError = By.className("input__error text_type_main-default");

    public void waitForSingUpIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(singUp));
    }

    public void setName(String userName) {
        driver.findElement(name).sendKeys(userName);
    }

    public void setEmail(String userEmail){
        driver.findElement(email).sendKeys(userEmail);
    }

    public void setPassword(String userPassword){
        driver.findElement(password).sendKeys(userPassword);
    }

    public void clickToSingUp(){
        driver.findElement(singUp).click();
    }

    public void fillRegistrationForm(String userName, String userEmail, String userPassword){
        setName(userName);
        setEmail(userEmail);
        setPassword(userPassword);
        clickToSingUp();
    }
}
