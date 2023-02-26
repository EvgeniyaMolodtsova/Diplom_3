package pages;

import api.model.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {

    private final WebDriver driver;

    public SignUp(WebDriver driver) {
        this.driver = driver;
    }

    //поле ввода имени
    private final By name = By.xpath(".//label[text() = 'Имя']/following-sibling::input");

    //поле ввода Email
    private final By email = By.xpath(".//label[text() = 'Email']/following-sibling::input");

    //поле ввода пароля
    private final By password = By.xpath(".//label[text() = 'Пароль']/following-sibling::input");

    //кнопка зарегистрироваться
    private final By signUp = By.xpath(".//button[text() = 'Зарегистрироваться']");

    //кнопка "Войти"
    private final By signIn = By.linkText("Войти");

    //сообщение о слишком коротком пароле
    private final By errorMessage = By.xpath(".//p[text() = 'Некорректный пароль']");

    //заголовок "Регистрация"
    private final By text = By.xpath(".//h2[text() = 'Регистрация']");

    @Step("ожидание загрузки страницы")
    public void waitForLoad() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(text).getText() != null
                && !driver.findElement(text).getText().isEmpty()
        ));
    }

    @Step("ввод имени в поле")
    public void fillName(String userName) {
        driver.findElement(name).sendKeys(userName);
    }

    @Step("ввод Email в поле")
    public void fillEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    @Step("ввод пароля в поле")
    public void fillPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }

    @Step("клик по кнопке Зарегистрироваться")
    public void clickToSignUp() {
        driver.findElement(signUp).click();
    }

    @Step("клик по кнопке Войти")
    public void clickToSignIn() {
        driver.findElement(signIn).click();
    }

    public void fillRegistrationForm(String userName, String userEmail, String userPassword) {
        fillName(userName);
        fillEmail(userEmail);
        fillPassword(userPassword);
        clickToSignUp();
    }

    @Step("регистрация пользователя")
    public void fillRegistrationForm(User user) {
        fillName(user.getName());
        fillEmail(user.getEmail());
        fillPassword(user.getPassword());
        clickToSignUp();
    }

    @Step("возвращение текста сообщения об ошибке")
    public String checkErrorMessage() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(errorMessage).getText() != null
                && !driver.findElement(errorMessage).getText().isEmpty()
        ));

        return driver.findElement(errorMessage).getText();
    }
}
