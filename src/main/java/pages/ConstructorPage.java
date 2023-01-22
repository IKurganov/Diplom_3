package pages;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private final WebDriver driver;
    private final By constructorLink = By.xpath("//p[text()='Конструктор']");
    private final By logoLink = By.className("AppHeader_header__logo__2D0X2");
    private final By actionInConstructorLabel = By.xpath("//h1[text()='Соберите бургер']");
    private final By burgerIngredients = By.className("BurgerIngredients_ingredients__list__2A-mT");
    private final By activeTab = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default']");
    private final By sauceTab = By.xpath("//span[text()='Соусы']");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажмём на конструктор")
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    @Step("Нажмём на кнопку логина")
    public void clickLogoLink() {
        driver.findElement(logoLink).click();
    }

    @Step("Отображается ли лейбл действия")
    public void isDisplayActionInConstructorLabel() {
        Assert.assertTrue(driver.findElement(actionInConstructorLabel).isDisplayed());
    }

    @Step("Нажмём на булки")
    public void clickBun() {
        driver.findElements(burgerIngredients).get(0).click();
    }

    @Step("Нажмём на соусы")
    public void clickSauce() {
        driver.findElement(sauceTab).click();
    }

    @Step("Нажмём на начинки")
    public void clickFilling() {
        driver.findElements(burgerIngredients).get(2).click();
    }

    @Step("Получим текст активной вкладки")
    public String getTextActiveTab() {
        return driver.findElement(activeTab).getText();
    }
}
