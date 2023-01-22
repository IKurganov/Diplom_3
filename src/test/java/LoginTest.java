import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.PersonalAccountPage;
import pojo.LoginRequest;
import pojo.UserRequest;
import utils.UserDataHelper;

import java.util.concurrent.TimeUnit;

import static testdata.UserRequestTestData.getUserRequestWithCorrectData;

public class LoginTest {
    private WebDriver driver;
    UserRequest userRequest;
    UserDataHelper userDataHelper;
    LoginPage loginPage;
    PersonalAccountPage personalAccountPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://stellarburgers.nomoreparties.site/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        userDataHelper = new UserDataHelper();
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        userRequest = getUserRequestWithCorrectData();
        userDataHelper.create(userRequest);
    }

    @After
    public void leave() {
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
    @DisplayName("Вход в аккаунт при помощи соответствующей кнопки на главной странице")
    @Description("Проверка входа в аккаунт с главной страницы")
    public void checkLogInToAccountWithButtonOnMainPage() {
        loginPage.clickLogInAccountButton();
        loginPage.setEmailInField(userRequest.getEmail());
        loginPage.setPasswordInField(userRequest.getPassword());
        loginPage.clickLoginButton();
        loginPage.isDisplayCollectBurgerLabel();
    }

    @Test
    @DisplayName("Вход в аккаунт через Личный Кабинет")
    @Description("Проверка входа через Личный Кабинет")
    public void checkLogInThroughPersonalAccount() {
        personalAccountPage.clickPersonalAccountLink();
        loginPage.setEmailInField(userRequest.getEmail());
        loginPage.setPasswordInField(userRequest.getPassword());
        loginPage.clickLoginButton();
        loginPage.isDisplayCollectBurgerLabel();
    }

    @Test
    @DisplayName("Вход в аккаунт через форму регистрации")
    @Description("Проверка входа в аккаунт через форму регистрации")
    public void checkLogInThroughRegistrationForm() {
        loginPage.clickLogInAccountButton();
        loginPage.clickRegisterLink();
        loginPage.clickLogInThroughRegistrationLink();
        loginPage.setEmailInField(userRequest.getEmail());
        loginPage.setPasswordInField(userRequest.getPassword());
        loginPage.clickLoginButton();
        loginPage.isDisplayCollectBurgerLabel();
    }

    @Test
    @DisplayName("Вход в аккаунт через форму восстановления пароля")
    @Description("Проверка входа в аккаунт через форму восстановления пароля")
    public void checkLogInThroughRecoverPasswordForm() {
        personalAccountPage.clickPersonalAccountLink();
        loginPage.clickRecoverPasswordLink();
        loginPage.clickLogInThroughRegistrationLink();
        loginPage.setEmailInField(userRequest.getEmail());
        loginPage.setPasswordInField(userRequest.getPassword());
        loginPage.clickLoginButton();
        loginPage.isDisplayCollectBurgerLabel();
    }
}