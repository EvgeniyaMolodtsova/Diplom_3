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
public class TestPersonalArea {

    private final WebDriver driver;

    private final String page = "https://stellarburgers.nomoreparties.site";

    private String token;

    UserClient userClient = new UserClient();
    User user;

    public TestPersonalArea(WebDriver driver){
        this.driver = driver;
    }

    @Parameterized.Parameters
    public static Object[][] getDriver() {
        return new Object[][] {
                {Drivers.getChromeDriver()},
                {Drivers.getYandexDriver()},
        };
    }

    public void signIn(HomePageStellaBurger homePageStellaBurger, PersonalArea personalArea){
        SignIn signIn = new SignIn(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToSignIn();
        signIn.waitForSignInIsClickable();
        signIn.fillSignInForm(user.getEmail(), user.getPassword());
        homePageStellaBurger.waitForButtonMakeOrder();
        homePageStellaBurger.clickToPersonalAreaButton();
        personalArea.waitLoad();
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
    @DisplayName("переход в личный кабинет с главной страницы для Chrome и Яндекса")
    public void checkClickPersonalAreaButton(){
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        Assert.assertTrue(personalArea.exitButtonIsDisplayed());
    }

    @Test
    @DisplayName("переход из личного кабинета в конструктор для Chrome и Яндекса")
    public void checkClickConstructorButton(){
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToConstructor();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    @DisplayName("переход из личного кабинета в конструктор по клику на логотип для Chrome и Яндекса")
    public void checkClickLogo(){
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToLogo();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    @DisplayName("выход из аккаунта")
    public void checkClickExitButton(){
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);
        SignIn login = new SignIn(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToExitButton();
        login.waitForSignInIsClickable();

        Assert.assertTrue(login.buttonSignInIsDisplayed());
    }
}
