package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By registerForm = By.xpath("//input[@class='text input__textfield text_type_main-default']");
    private final By passwordErrorField = By.xpath("//p[text()='Некорректный пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполним форму регистрации")
    public void setDataRegisterForm(String name, String email, String password) {
        driver.findElements(registerForm).get(0).sendKeys(name);
        driver.findElements(registerForm).get(1).sendKeys(email);
        driver.findElements(registerForm).get(2).sendKeys(password);
    }

    @Step("Нажмём на кнопку регистрации")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Проверим отображение кнопки Войти - логина")
    public void isDisplayLoginButton() {
        Assert.assertTrue(driver.findElement(loginButton).isDisplayed());
    }

    @Step("Получим текст с предупреждения об ошибке")
    public String getTextPasswordErrorField() {
        return driver.findElement(passwordErrorField).getText();
    }
}
