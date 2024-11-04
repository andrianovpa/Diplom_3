package pageproject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private By emailField = By.xpath("(//input[@name='name'])");
    private By passwordField = By.xpath("//input[@name='Пароль']");
    private By loginButton = By.xpath("//button[text()='Войти']");
    private By titleText = By.xpath("//h1[text()='Соберите бургер']");
    private By headerText = By.xpath("//h2[text()='Вход']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email")
    public void setEmailField(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
    }

    @Step("Ввести пароль")
    public void setPasswordField(String userPassword) {
        driver.findElement(passwordField).sendKeys(userPassword);
    }

    @Step("Нажать на кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Ожидание кликабельности кнопки Войти")
    public void waitElementToBeClickable() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    @Step("Получить accessToken из LocalStorage")
    public String getAccessToken() {
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        return localStorage.getItem("accessToken");
    }

    @Step("Ожидание видимости заголовка 'Соберите бургер'")
    public void waitForVisibilityOfTitle() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(titleText));
    }

    @Step("Авторизация с email и паролем")
    public void login(String userEmail, String userPassword) {
        waitElementToBeClickable();
        setEmailField(userEmail);
        setPasswordField(userPassword);
        clickLoginButton();
    }

    @Step("Ожидание видимости заголовка 'Соберите бургер'")
    public void waitForVisibilityOfHeader() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(headerText));
    }


}
