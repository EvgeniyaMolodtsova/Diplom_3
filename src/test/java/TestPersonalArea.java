import io.restassured.response.ValidatableResponse;
import org.example.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestPersonalArea {

    private WebDriver driver;

    private String token;

    UserClient userClient = new UserClient();
    User user;

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
    public void checkClickPersonalAreaButtonChrome(){
        driver = Drivers.getChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        Assert.assertTrue(personalArea.exitButtonIsDisplayed());
    }

    @Test
    public void checkClickConstructorButtonChrome(){
        driver = Drivers.getChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToConstructor();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    public void checkClickLogoChrome(){
        driver = Drivers.getChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToLogo();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    public void checkClickExitButtonChrome(){
        driver = Drivers.getChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);
        SignIn login = new SignIn(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToExitButton();
        login.waitForSignInIsClickable();

        Assert.assertTrue(login.buttonSignInIsDisplayed());
    }
}
