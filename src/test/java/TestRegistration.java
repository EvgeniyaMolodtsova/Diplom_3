import api.client.UserClient;
import api.model.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.SignUp;
import utils.Drivers;
import utils.Random;

public class TestRegistration {

    private final UserClient userClient = new UserClient();
    private WebDriver driver;
    private String token;

    public void registration() {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        SignUp signUp = new SignUp(driver);
        signUp.waitForLoad();
        User user = User.generate();
        signUp.fillRegistrationForm(user);

        ValidatableResponse login = userClient.login(User.getLoginFrom(user));
        token = userClient.getUserAccessToken(login);
        Assert.assertEquals(200, login.extract().statusCode());
    }

    public void registrationWithShortPassword() {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        SignUp signUp = new SignUp(driver);
        signUp.waitForLoad();
        User user = User.generate();
        user.setPassword(Random.getRandom(5));
        signUp.fillRegistrationForm(user);
        Assert.assertEquals("Некорректный пароль", signUp.checkErrorMessage());
    }

    @After
    public void cleanUp() {
        if (token != null) {
            userClient.delete(token);
        }
        driver.quit();
    }

    @Test
    @DisplayName("регистрация Chrome")
    public void registrationChrome() {
        driver = Drivers.getChromeDriver();

        registration();
    }

    @Test
    @DisplayName("регистрация Яндекс")
    public void registrationYandex() {
        driver = Drivers.getYandexDriver();

        registration();
    }

    @Test
    @DisplayName("невозможно зарегистрироваться с паролем короче 6 символов Chrome")
    public void registrationChromeWithShortPasswordChrome() {
        driver = Drivers.getChromeDriver();

        registrationWithShortPassword();
    }

    @Test
    @DisplayName("невозможно зарегистрироваться с паролем короче 6 символов Яндекс")
    public void registrationChromeWithShortPasswordYandex() {
        driver = Drivers.getYandexDriver();

        registrationWithShortPassword();
    }
}
