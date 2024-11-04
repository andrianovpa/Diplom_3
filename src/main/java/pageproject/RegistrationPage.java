package pageproject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;
    private By nameField = By.xpath("//input[@name='name'][1]");
    private By emailField = By.xpath("(//input[@name='name'])[2]");
    private By passwordField = By.xpath("//input[@name='Пароль']");
    private By passwordErrorField = By.xpath("//p[@class='input__error text_type_main-default' and text()='Некорректный пароль']");
    private By regButton = By.xpath("//button[text()='Зарегистрироваться']");
    private By loginButton = By.xpath("//a[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести имя пользователя")
    public void setNameField(String username) {
        driver.findElement(nameField).sendKeys(username);
    }

    @Step("Ввести адрес электронной почты")
    public void setEmailField(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
    }

    @Step("Ввести пароль")
    public void setPasswordField(String userPassword) {
        driver.findElement(passwordField).sendKeys(userPassword);
    }

    @Step("Кликнуть на кнопку 'Зарегистрироваться'")
    public void clickRegButton() {
        driver.findElement(regButton).click();
    }

    @Step("Ожидание, пока кнопка 'Зарегистрироваться' станет кликабельной")
    public void waitElementToBeClickable() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(regButton));
    }
    @Step("Ожидание, пока кнопка 'Войти' станет кликабельной")
    public void waitLoginButtonToBeClickable() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    @Step("Проверить наличие сообщения об ошибке пароля")
    public boolean isErrorMessagePresent() {
        try {
            driver.findElement(passwordErrorField);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            return false;
        }
    }

    @Step("Кликнуть на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
