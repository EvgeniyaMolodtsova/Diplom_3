package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageStellaBurger {

    private WebDriver driver;

    //кнопка "Личный кабинет" в шапке страницы
    private By personalAreaButton = By.linkText("Личный Кабинет");

    //кнопка "Конструктор" в шапке страницы
    private By constructor = By.linkText("Конструктор");

    //кнопка логотипа в шапке страницы
    private By logo = By.className("AppHeader_header__logo__2D0X2");

    //кнопка "Войти в аккаунт"
    private By singIn = By.xpath(".//button[text()= 'Войти в аккаунт']");

    //кнопка "Оформить заказ"
    private By makeOrder = By.xpath(".//button[text() = 'Оформить заказ']");

    //кнопка перехода к разделу "Булки"
    private By buns = By.xpath(".//span[text()= 'Булки']");

    //кнопка перехода к разделу "Соусы"
    private By souse = By.xpath(".//span[text()= 'Соусы']");

    //кнопка перехода к разделу "Начинки"
    private By main = By.xpath(".//span[text()= 'Начинки']");

    public HomePageStellaBurger(WebDriver driver){
        this.driver = driver;
    }

    public void waitForPersonalAreaButtonIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(personalAreaButton));
    }

    public void waitForСonstructorIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(constructor));
    }

    public void waitForLogoIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(logo));
    }

    public void waitForSingInIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(singIn));
    }

    public void waitForBunsIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(buns));
    }

    public void waitForSouseIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(souse));
    }

    public void waitForMainIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(main));
    }

    public void clickToPersonalAreaButton(){
        driver.findElement(personalAreaButton).click();
    }

    public void clickToConstructor(){
        driver.findElement(constructor);
    }

    public void clickToLogo(){
        driver.findElement(logo).click();
    }

    public void clickToSingIn(){
        driver.findElement(singIn).click();
    }

    public void clickToBuns(){
        driver.findElement(buns).click();
    }

    public void clickToSouse(){
        driver.findElement(souse).click();
    }

    public void clickToMain(){
        driver.findElement(main);
    }
}
