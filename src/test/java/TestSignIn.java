import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class TestSignIn {
    private final WebDriver driver;

    private String token;

    UserClient userClient = new UserClient();
    User user;

    public TestSignIn(WebDriver driver) {
        this.driver = driver;
    }

    @Parameterized.Parameters
    public static Object[][] getDriver() {
        return new Object[][] {
                {Drivers.getChromeDriver()},
                {Drivers.getYandexDriver()},
        };
    }

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
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной Chrome и Яндекс")
    public void loginThroughButtonSingIn(){
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToSignIn();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет» Chrome и Яндекс")
    public void loginInThroughPersonalArea(){
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToPersonalAreaButton();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации Chrome и Яндекс")
    public void loginInThroughSignUpForm(){
        driver.get("https://stellarburgers.nomoreparties.site/register");

        SignUp signUp = new SignUp(driver);
        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        signUp.waitForLoad();
        signUp.clickToSignIn();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля Chrome и Яндекс")
    public void loginThroughForgotPassword(){
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        ForgotPassword forgotPassword = new ForgotPassword(driver);
        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        forgotPassword.waitLoad();
        forgotPassword.clickForSignInButton();

        checkSignIn(homePageStellaBurger);
    }
}
