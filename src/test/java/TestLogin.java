import api.CreateUserApi;
import api.DeleteUserApi;
import model.CreateUser;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageproject.LoginPage;
import pageproject.MainPage;
import pageproject.RecoveryPage;
import pageproject.RegistrationPage;
import resources.SwitchBrowserClass;

@RunWith(Parameterized.class)
public class TestLogin {
    public TestLogin(String browser) {
        this.browser = browser;
    }

    private static String browser;
    private static String accessToken;
    private static String name = "Pavel";
    private static String email = "andrainovpa@gmail.com";
    private static String password = "1234567";
    private WebDriver driver;

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"chrome"},
                {"firefox"}
        };
    }

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
    public void testLoginUserFromMainPageWithLoginButton() {
        driver = SwitchBrowserClass.createDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/");
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
        Assert.assertNotEquals(null, accessToken);

    }

    @Test
    public void testLoginUserFromMainPageWithPersonalAcc() {
        driver = SwitchBrowserClass.createDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/");
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
        Assert.assertNotEquals(null, accessToken);
    }

    @Test
    public void testLoginUserFromRegistrationPage() {
        driver = SwitchBrowserClass.createDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/register");
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
        Assert.assertNotEquals(null, accessToken);
    }

    @Test
    public void testLoginUserFromRecoveryPage() {
        driver = SwitchBrowserClass.createDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
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
        Assert.assertNotEquals(null, accessToken);
    }
}
