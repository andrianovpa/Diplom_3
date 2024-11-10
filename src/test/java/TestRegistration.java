import api.DeleteUserApi;
import api.LoginUserApi;
import base.URL;
import io.qameta.allure.junit4.DisplayName;
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
    private static String accessToken;
    private static String name;
    private static String email;
    private static String password;
    private WebDriver driver;
    private boolean expectError;

    public TestRegistration(String name, String email, String password, boolean expectError) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.expectError = expectError;
    }

    @Parameterized.Parameters
    public static Object[][] testParameters() {
        return new Object[][]{
                {"Pavel", "andrianovpa@gmail.com", "1234", true},
                {"Pavel", "andrianovpa@gmail.com", "1234567", false},
                {"Pavel", "andrianovpa@gmail.com", "1234", true},
                {"Pavel", "andrianovpa@gmail.com", "1234567", true}
        };
    }

    @Before
    public void startUp() {
        driver = SwitchBrowserClass.getDriver();
        driver.get(URL.REGISTER_HOST);

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
    @DisplayName("Тест регистрации пользователя")
    public void testRegistrationUser() {
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.waitElementToBeClickable();
        objRegistrationPage.setNameField(name);
        objRegistrationPage.setEmailField(email);
        objRegistrationPage.setPasswordField(password);
        objRegistrationPage.clickRegButton();
        if (expectError) {
            if (objRegistrationPage.isErrorMessagePresentPassword()) {
                Assert.assertTrue(objRegistrationPage.isErrorMessagePresentPassword());
            }
            if (objRegistrationPage.isErrorMessagePresentUser()) {
                Assert.assertTrue(objRegistrationPage.isErrorMessagePresentUser());
            }
        } else {
            Assert.assertFalse(objRegistrationPage.isErrorMessagePresentPassword());
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
}
