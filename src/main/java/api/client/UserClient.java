package api.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import api.model.User;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {

    @Step("Send POST request to /api/auth/register")
    public ValidatableResponse create(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .when()
                .post("/api/auth/register")
                .then();
    }

    @Step("Send POST request to /api/auth/login")
    public ValidatableResponse login(User userLogin) {
        return given().log().all()
                .spec(getSpec())
                .body(userLogin)
                .when()
                .post("/api/auth/login")
                .then();

    }

    @Step("Send DELETE request to /api/auth/user")
    public ValidatableResponse delete(String accessToken) {
        return given().log().all()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then();
    }

    @Step("получение токена юзера")
    public String getUserAccessToken(ValidatableResponse validatableResponse) {
        return validatableResponse.extract().path("accessToken");
    }
}
