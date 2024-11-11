package pageproject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecoveryPage {
    private WebDriver driver;
    private By loginButton = By.xpath("//a[text()='Войти']");

    public RecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Ожидание, пока кнопка 'Войти' станет кликабельной")
    public void waitElementToBeClickable() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(loginButton));
    }
}
