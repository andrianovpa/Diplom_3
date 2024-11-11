import api.CreateUserApi;
import api.DeleteUserApi;
import base.URL;
import io.qameta.allure.junit4.DisplayName;
import model.CreateUser;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pageproject.LoginPage;
import pageproject.MainPage;
import pageproject.RecoveryPage;
import pageproject.RegistrationPage;
import resources.SwitchBrowserClass;


public class TestLogin {

    private static String accessToken;
    private static String name = "Pavel";
    private static String email = "andrainovpa@gmail.com";
    private static String password = "1234567";
    private WebDriver driver;


    @BeforeClass
    public static void CreateUser() {
        CreateUserApi createUserApi = new CreateUserApi();
        CreateUser createUser = new CreateUser(name, email, password);
        accessToken = createUserApi.createUser(createUser).then().extract().path("accessToken");
    }


    @After
    public void teardown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }


    @AfterClass
    public static void deleteUser() {
        DeleteUserApi deleteUserApi = new DeleteUserApi();
        deleteUserApi.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка авторизации пользователя при переходе со стартовой страницыпо кнопке 'Войти в аккаунт'")
    public void testLoginUserFromMainPageWithLoginButton() {
        driver = SwitchBrowserClass.getDriver();
        driver.get(URL.MAIN_HOST);
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitLoginButtonToBeClickable();
        objMainPage.clickLoginButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitElementToBeClickable();
        objLoginPage.setEmailField(email);
        objLoginPage.setPasswordField(password);
        objLoginPage.clickLoginButton();
        objLoginPage.waitForVisibilityOfTitle();
        accessToken = objLoginPage.getAccessToken();
        Assert.assertNotNull(accessToken);

    }

    @Test
    @DisplayName("Проверка авторизации пользователя при переходе со стартовой страницыпо кнопке 'Личный кабинет'")
    public void testLoginUserFromMainPageWithPersonalAcc() {
        driver = SwitchBrowserClass.getDriver();
        driver.get(URL.MAIN_HOST);
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitPersonalAccountToBeClickable();
        objMainPage.clickPersonalAccount();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitElementToBeClickable();
        objLoginPage.setEmailField(email);
        objLoginPage.setPasswordField(password);
        objLoginPage.clickLoginButton();
        objLoginPage.waitForVisibilityOfTitle();
        accessToken = objLoginPage.getAccessToken();
        Assert.assertNotNull(accessToken);
    }

    @Test
    @DisplayName("Проверка авторизации пользователя при переходе со страницы регистрации")
    public void testLoginUserFromRegistrationPage() {
        driver = SwitchBrowserClass.getDriver();
        driver.get(URL.REGISTER_HOST);
        RegistrationPage objregistrationPage = new RegistrationPage(driver);
        objregistrationPage.waitLoginButtonToBeClickable();
        objregistrationPage.clickLoginButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitElementToBeClickable();
        objLoginPage.setEmailField(email);
        objLoginPage.setPasswordField(password);
        objLoginPage.clickLoginButton();
        objLoginPage.waitForVisibilityOfTitle();
        accessToken = objLoginPage.getAccessToken();
        Assert.assertNotNull(accessToken);
    }

    @Test
    @DisplayName("Проверка авторизации пользователя при переходе с формы восстановления пароля")
    public void testLoginUserFromRecoveryPage() {
        driver = SwitchBrowserClass.getDriver();
        driver.get(URL.FORGOT_HOST);
        RecoveryPage objRecoveryPage = new RecoveryPage(driver);
        objRecoveryPage.waitElementToBeClickable();
        objRecoveryPage.clickLoginButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitElementToBeClickable();
        objLoginPage.setEmailField(email);
        objLoginPage.setPasswordField(password);
        objLoginPage.clickLoginButton();
        objLoginPage.waitForVisibilityOfTitle();
        accessToken = objLoginPage.getAccessToken();
        Assert.assertNotNull(accessToken);
    }
}
