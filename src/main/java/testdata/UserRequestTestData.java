package testdata;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.UserRequest;

public class UserRequestTestData {
    @Step("Запрос на регистрацию пользователя со всеми корректными данными")
    public static UserRequest getUserRequestWithCorrectData() {
        String name = RandomStringUtils.randomAlphabetic(11);
//        String email = "ilyaK123456789@gmail.com";
        String email = RandomStringUtils.randomAlphanumeric(9) + "@gmail.com";
        String password = RandomStringUtils.randomAlphabetic(7);
        return new UserRequest(name, email, password);
    }

    @Step("Запрос на регистрацию пользователя с некорректным паролем")
    public static UserRequest getUserRequestWithIncorrectPassword() {
        String name = RandomStringUtils.randomAlphabetic(13);
        String email = RandomStringUtils.randomAlphanumeric(9) + "@gmail.com";
        String password = RandomStringUtils.randomAlphanumeric(5);
        return new UserRequest(name, email, password);
    }
}