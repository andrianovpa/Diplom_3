import api.CreateUserApi;
import api.DeleteUserApi;
import api.LoginUserApi;
import model.CreateUser;
import model.LoginUser;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageproject.LoginPage;
import pageproject.MainPage;
import pageproject.PersonalAccountPage;
import resources.SwitchBrowserClass;

@RunWith(Parameterized.class)
public class TransitionTests {
    public TransitionTests(String browser) {
        this.browser = browser;
    }

    private static String browser;
    private WebDriver driver;
    private static String name = "Pavel";
    private static String email = "andrainovpa@gmail.com";
    private static String password = "1234567";
    private static String accessToken;

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
        createUserApi.createUser(createUser);
    }

    @Before
    public void startUp() {
        driver = SwitchBrowserClass.createDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.login(email, password);
    }

    @After
    public void teardown() {
        driver.manage().deleteAllCookies();
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
    public void testTransitionToPersonalAccount() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickPersonalAccount();
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.waitForVisibilityOfProfileTab();
    }

    @Test
    public void testTransitionToConstructor() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickPersonalAccount();
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.clickConstructorTab();
        objMainPage.waitForVisibilityOfTitle();
    }

    @Test
    public void testTransitionToConstructorWithLogo() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickPersonalAccount();
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.clickLogo();
        objMainPage.waitForVisibilityOfTitle();
    }
}
