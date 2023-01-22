package pages;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final By personalAccountLink = By.xpath("//p[text()='Личный Кабинет']");
    private final By logOutButton = By.xpath("//button[text()='Выход']");
    private final By informAboutPersonalAccountLabel = By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Войдём в личный кабинет")
    public void clickPersonalAccountLink() {
        driver.findElement(personalAccountLink).click();
    }

    @Step("Нажмём на кнопку Выход")
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }

    @Step("Отображается ли лейбл о смене персональных данных")
    public void isDisplayInformAboutPersonalAccountLabel() {
        Assert.assertTrue(driver.findElement(informAboutPersonalAccountLabel).isDisplayed());
    }
}