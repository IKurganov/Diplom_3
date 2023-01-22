package pages;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By logInAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By emailField = By.xpath("//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By collectBurgerLabel = By.xpath("//h1[text()='Соберите бургер']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By logInThroughRegistrationLink = By.xpath("//a[text()='Войти']");
    private final By recoverPasswordLink = By.xpath("//a[text()='Восстановить пароль']");
    private final By loginLabel = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажмём на кнопку Войти в аккаунт")
    public void clickLogInAccountButton() {
        driver.findElement(logInAccountButton).click();
    }

    @Step("Заполним поле емейла значением - {name}")
    public void setEmailInField(String name) {
        driver.findElement(emailField).sendKeys(name);
    }

    @Step("Заполним пароль")
    public void setPasswordInField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажмём на кнопку логина")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void isDisplayCollectBurgerLabel() {
        Assert.assertTrue(driver.findElement(collectBurgerLabel).isDisplayed());
    }

    @Step("Нажмём на кнопку регистрации")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Нажмём на кнопку входа регистрации")
    public void clickLogInThroughRegistrationLink() {
        driver.findElement(logInThroughRegistrationLink).click();
    }

    @Step("Нажмём на Восстановить пароль")
    public void clickRecoverPasswordLink() {
        driver.findElement(recoverPasswordLink).click();
    }

    @Step("Display of the \"Login\" field")
    public void isDisplayLoginLabel() {
        Assert.assertTrue(driver.findElement(loginLabel).isDisplayed());
    }
}
