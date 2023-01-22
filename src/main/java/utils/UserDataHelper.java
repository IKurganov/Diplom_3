package utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.LoginRequest;
import pojo.UserRequest;

import static io.restassured.RestAssured.given;

public class UserDataHelper {
    private static final String BASE_URI = "http://stellarburgers.nomoreparties.site/api/";

    @Step("Регистрация пользователя")
    public void create(UserRequest userRequest) {
        given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(userRequest)
                .post("auth/register");
    }

    @Step("Авторизация пользователя")
    public Response login(LoginRequest loginRequest) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(loginRequest)
                .post("auth/login");
    }

    @Step("Удаление пользователя")
    public void delete(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .delete("auth/user");
    }
}
