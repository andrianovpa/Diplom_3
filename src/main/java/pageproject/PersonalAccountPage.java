package pageproject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    private WebDriver driver;
    private By profileTab = By.linkText("Профиль");
    private By constructorTab = By.xpath("//p[text()='Конструктор']");
    private By logo = By.xpath("//div[contains(@class, 'header__logo')]");
    private By logOut = By.xpath("//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание видимости вкладки 'Профиль'")
    public void waitForVisibilityOfProfileTab() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(profileTab));
    }

    @Step("Кликнуть на вкладку 'Конструктор'")
    public void clickConstructorTab() {

        driver.findElement(constructorTab).click();
    }

    @Step("Кликнуть на логотип")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Кликнуть на кнопку 'Выход'")
    public void clickLogOut() {
        driver.findElement(logOut).click();
    }
}

