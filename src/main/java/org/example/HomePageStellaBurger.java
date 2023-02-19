package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePageStellaBurger {

    private final WebDriver driver;
    private final JavascriptExecutor jsExecutor;

    //кнопка "Личный кабинет" в шапке страницы
    private final By personalAreaButton = By.linkText("Личный Кабинет");

    //кнопка "Войти в аккаунт"
    private final By signIn = By.xpath(".//button[text()= 'Войти в аккаунт']");

    //кнопка "Оформить заказ"
    private final By makeOrder = By.xpath(".//button[text() = 'Оформить заказ']");

    //кнопка перехода к разделу "Булки"
    private final By buns = By.xpath(".//span[text()= 'Булки']");

    //кнопка перехода к разделу "Соусы"
    private final By souse = By.xpath(".//span[text()= 'Соусы']");

    //кнопка перехода к разделу "Начинки"
    private final By main = By.xpath(".//span[text()= 'Начинки']");

    public HomePageStellaBurger(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor)driver;
    }

    public void waitForSignInIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(signIn));
    }

    public void waitForButtonMakeOrder() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(makeOrder));
    }

    public boolean buttonMakeOrderIsDisplayed(){
        return driver.findElement(makeOrder).isDisplayed();
    }

    public void clickToPersonalAreaButton() {
        driver.findElement(personalAreaButton).click();
    }

    public void clickToSignIn() {
        driver.findElement(signIn).click();
    }

    public void clickToBuns() {
        driver.findElement(buns).click();
    }

    public void clickToSouse() {
        driver.findElement(souse).click();
    }

    public void clickToMain() {
        driver.findElement(main).click();
    }

    public int getHeadlineOffset(int index) {
        Object headlineOffsetTopRaw = jsExecutor.executeScript("return document.querySelectorAll('.text.text_type_main-medium.mb-6.mt-10')[" + index + "].offsetTop");
        Object wrapperOffsetTopRaw = jsExecutor.executeScript("return document.querySelector('.BurgerIngredients_ingredients__menuContainer__Xu3Mo').offsetTop");

        int headlineOffsetTop = Parser.parseInt(headlineOffsetTopRaw);
        int wrapperOffsetTop = Parser.parseInt(wrapperOffsetTopRaw);

        return headlineOffsetTop - wrapperOffsetTop;
    }

    public int getScrollPosition() {
        Object object = jsExecutor.executeScript("return document.querySelector('.BurgerIngredients_ingredients__menuContainer__Xu3Mo').scrollTop");

        return Parser.parseInt(object);
    }
}
