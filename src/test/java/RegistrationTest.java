import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.RegistrationPage;
import pojo.LoginRequest;
import pojo.UserRequest;
import utils.UserDataHelper;

import java.util.concurrent.TimeUnit;

import static testdata.UserRequestTestData.getUserRequestWithCorrectData;
import static testdata.UserRequestTestData.getUserRequestWithIncorrectPassword;

public class RegistrationTest {
    private WebDriver driver;
    UserRequest userRequest;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://stellarburgers.nomoreparties.site/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void leave() {
        UserDataHelper userDataHelper = new UserDataHelper();
        LoginRequest loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword());
        Response response = userDataHelper.login(loginRequest);
        String accessToken = response
                .then()
                .extract()
                .path("accessToken");

        if (accessToken != null) {
            userDataHelper.delete(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации")
    public void checkRegistrationWithCorrectPassword() {
        userRequest = getUserRequestWithCorrectData();
        loginPage.clickLogInAccountButton();
        loginPage.clickRegisterLink();
        registrationPage.setDataRegisterForm(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        registrationPage.clickRegisterButton();
        registrationPage.isDisplayLoginButton();
    }

    @Test
    @DisplayName("Запрет регистрации с некорректными данными")
    @Description("Проверка ошибки регистрации при указании слишком короткого пароля")
    public void checkRegistrationWithIncorrectPassword() {
        userRequest = getUserRequestWithIncorrectPassword();
        loginPage.clickLogInAccountButton();
        loginPage.clickRegisterLink();
        registrationPage.setDataRegisterForm(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        registrationPage.clickRegisterButton();
        Assert.assertEquals("Некорректный пароль", registrationPage.getTextPasswordErrorField());
    }
}