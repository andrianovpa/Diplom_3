package pageproject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By personalAccount = By.xpath("//p[text()='Личный Кабинет']");
    private By titleText = By.xpath("//h1[text()='Соберите бургер']");
    private By bunSections = By.xpath("//span[text()='Булки']");
    private By saucesSections = By.xpath("//span[text()='Соусы']");
    private By fillingsSections = By.xpath("//span[text()='Начинки']");
    private By bunHeader = By.xpath("//h2[text()='Булки']");
    private By saucesHeader = By.xpath("//h2[text()='Соусы']");
    private By fillingsHeader = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Кликнуть на 'Личный Кабинет'")
    public void clickPersonalAccount() {
        driver.findElement(personalAccount).click();
    }

    @Step("Ожидание видимости заголовка 'Соберите бургер'")
    public void waitForVisibilityOfTitle() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(titleText));
    }

    @Step("Кликнуть на раздел 'Булки'")
    public void clickBun() {
        driver.findElement(bunSections).click();
    }

    @Step("Кликнуть на раздел 'Соусы'")
    public void clickSauces() {
        driver.findElement(saucesSections).click();
    }

    @Step("Кликнуть на раздел 'Начинки'")
    public void clickFillings() {
        driver.findElement(fillingsSections).click();
    }

    @Step("Ожидание видимости заголовка 'Булки'")
    public void waitForVisibilityOfBunHeader() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(bunHeader));
    }

    @Step("Ожидание видимости заголовка 'Соусы'")
    public void waitForVisibilityOfSaucesHeader() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(saucesHeader));
    }

    @Step("Ожидание видимости заголовка 'Начинки'")
    public void waitForVisibilityOfFillingsHeader() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader));
    }
    @Step("Ожидание, пока кнопка 'Личный кабинет' станет кликабельной")
    public void waitPersonalAccountToBeClickable() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(personalAccount));
    }
    @Step("Ожидание, пока кнопка 'Войти в аккаунт' станет кликабельной")
    public void waitLoginButtonToBeClickable() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(loginButton));
    }
}
