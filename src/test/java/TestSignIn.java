import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestSignIn {
    private WebDriver driver;

    private String token;

    UserClient userClient = new UserClient();
    User user;

    public void checkSignIn(HomePageStellaBurger homePageStellaBurger){
        SignIn signIn = new SignIn(driver);

        signIn.waitForSignInIsClickable();
        signIn.fillSignInForm(user.getEmail(), user.getPassword());
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Before
    public void createUser(){
        user = User.generate();
        ValidatableResponse create = userClient.create(user);
        token = create.extract().path("accessToken");
    }

    @After
    public void cleanUp(){
        if (token != null) {
            userClient.delete(token);
        }
        driver.quit();
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной Chrome")
    public void loginThroughButtonSingInChrome(){
        driver = Drivers.getChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToSignIn();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет» Chrome")
    public void loginInThroughPersonalAreaChrome(){
        driver = Drivers.getChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToPersonalAreaButton();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации Chrome")
    public void loginInThroughSignUpFormChrome(){
        driver = Drivers.getChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");

        SignUp signUp = new SignUp(driver);
        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        signUp.waitForLoad();
        signUp.clickToSignIn();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля Chrome")
    public void loginThroughForgotPasswordChrome(){
        driver = Drivers.getChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        ForgotPassword forgotPassword = new ForgotPassword(driver);
        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        forgotPassword.waitLoad();
        forgotPassword.clickForSignInButton();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной Яндекс")
    public void loginThroughButtonSingInYandex(){
        driver = Drivers.getYandexDriver();
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToSignIn();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет» Яндекс")
    public void loginInThroughPersonalAreaYandex(){
        driver = Drivers.getYandexDriver();
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToPersonalAreaButton();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации Яндекс")
    public void loginInThroughSignUpFormYandex(){
        driver = Drivers.getYandexDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");

        SignUp signUp = new SignUp(driver);
        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        signUp.waitForLoad();
        signUp.clickToSignIn();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля Яндекс")
    public void loginThroughForgotPasswordYandex(){
        driver = Drivers.getYandexDriver();
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        ForgotPassword forgotPassword = new ForgotPassword(driver);
        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        forgotPassword.waitLoad();
        forgotPassword.clickForSignInButton();

        checkSignIn(homePageStellaBurger);
    }
}
