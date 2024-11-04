import api.DeleteUserApi;
import api.LoginUserApi;
import model.LoginUser;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageproject.LoginPage;
import pageproject.RegistrationPage;
import resources.SwitchBrowserClass;

@RunWith(Parameterized.class)
public class TestRegistration {
    private static String browser;
    private static String accessToken;
    private static String name;
    private static String email;
    private static String password;
    private WebDriver driver;
    private boolean expectError;

    public TestRegistration(String name, String email, String password, boolean expectError, String browser) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.expectError = expectError;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Pavel", "andrianovpa@gmail.com", "1234", true, "chrome"},
                {"Pavel", "andrianovpa@gmail.com", "1234567", false, "chrome"},
                {"Pavel", "andrianovpa@gmail.com", "1234", true, "firefox"},
                {"Pavel", "andrianovpa@gmail.com", "1234567", false, "firefox"}
        };
    }

    @Before
    public void startUp() {
        driver = SwitchBrowserClass.createDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/register");

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

    @AfterClass
    public static void deleteUser() {
        LoginUserApi loginUserApi = new LoginUserApi();
        LoginUser loginUser = new LoginUser(email, password);
        accessToken = loginUserApi.loginUser(loginUser).then().extract().path("accessToken");
        DeleteUserApi deleteUserApi = new DeleteUserApi();
        deleteUserApi.deleteUser(accessToken);
    }

    @Test
    public void testRegistrationUser() {
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.waitElementToBeClickable();
        objRegistrationPage.setNameField(name);
        objRegistrationPage.setEmailField(email);
        objRegistrationPage.setPasswordField(password);
        objRegistrationPage.clickRegButton();
        if (expectError) {
            Assert.assertTrue(objRegistrationPage.isErrorMessagePresent());
        } else {
            Assert.assertFalse(objRegistrationPage.isErrorMessagePresent());
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
}
