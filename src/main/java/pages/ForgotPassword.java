package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassword {

    private final WebDriver driver;

    public ForgotPassword(WebDriver driver) {
        this.driver = driver;
    }

    private final By signInButton = By.linkText("Войти");

    private final By textOnPage = By.xpath(".//h2[text() = 'Восстановление пароля']");

    @Step("клик по кнопке Войти")
    public void clickForSignInButton() {
        driver.findElement(signInButton).click();
    }

    @Step("ожидание загрузки заголовка Восстановление пароля")
    public void waitLoad() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(textOnPage).getText() != null
                && !driver.findElement(textOnPage).getText().isEmpty()
        ));
    }
}
