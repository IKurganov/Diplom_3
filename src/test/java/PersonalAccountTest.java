import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ConstructorPage;
import pages.LoginPage;
import pages.PersonalAccountPage;
import pages.RegistrationPage;
import pojo.LoginRequest;
import pojo.UserRequest;
import utils.UserDataHelper;

import java.util.concurrent.TimeUnit;

import static testdata.UserRequestTestData.getUserRequestWithCorrectData;

public class PersonalAccountTest {
    private WebDriver driver;
    private UserRequest userRequest;
    private LoginRequest loginRequest;
    private UserDataHelper userDataHelper;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;
    private RegistrationPage registrationPage;
    private ConstructorPage constructorPage;

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
        registrationPage = new RegistrationPage(driver);
        constructorPage = new ConstructorPage(driver);
        userRequest = getUserRequestWithCorrectData();
        loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword());
        userDataHelper.create(userRequest);
        loginPage.clickLogInAccountButton();
        loginPage.setEmailInField(userRequest.getEmail());
        loginPage.setPasswordInField(userRequest.getPassword());
        loginPage.clickLoginButton();
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
    @DisplayName("Переход в Личный Кабинет")
    @Description("Проверка успешного перехода в Личный Кабинет")
    public void checkSwitchingToPersonalAccount() {
        personalAccountPage.clickPersonalAccountLink();
        personalAccountPage.isDisplayInformAboutPersonalAccountLabel();
    }

    @Test
    @DisplayName("Переход из ЛК к конструктору через кнопку Конструктор")
    @Description("Проверка перехода из Личного кабинета к форме конструктора кликом по кнопке")
    public void checkSwitchConstructorClickOnConstructorLink() {
        personalAccountPage.clickPersonalAccountLink();
        constructorPage.clickConstructorLink();
        constructorPage.isDisplayActionInConstructorLabel();
    }

    @Test
    @DisplayName("Переход из ЛК к конструктору по нажатию на лого Stellar Burgers")
    @Description("Проверка перехода из Личного кабинета к форме конструктора кликом по лого Stellar Burgers")
    public void checkSwitchConstructorClickOnLogoLink() {
        personalAccountPage.clickPersonalAccountLink();
        constructorPage.clickLogoLink();
        constructorPage.isDisplayActionInConstructorLabel();
    }

    @Test
    @DisplayName("Выход из Личного Кабинета через кнопку Выход")
    @Description("Проверка выхода из ЛК по нажатию на кнопку Выход")
    public void checkLogOutPersonalAccount() {
        personalAccountPage.clickPersonalAccountLink();
        personalAccountPage.clickLogOutButton();
        loginPage.isDisplayLoginLabel();
    }
}