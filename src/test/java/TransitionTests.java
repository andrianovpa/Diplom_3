import api.CreateUserApi;
import api.DeleteUserApi;
import api.LoginUserApi;
import base.URL;
import io.qameta.allure.junit4.DisplayName;
import model.CreateUser;
import model.LoginUser;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pageproject.LoginPage;
import pageproject.MainPage;
import pageproject.PersonalAccountPage;
import resources.SwitchBrowserClass;


public class TransitionTests {


    private static String browser;
    private WebDriver driver;
    private static String name = "Pavel";
    private static String email = "andrainovpa@gmail.com";
    private static String password = "1234567";
    private static String accessToken;


    @BeforeClass
    public static void CreateUser() {

        CreateUserApi createUserApi = new CreateUserApi();
        CreateUser createUser = new CreateUser(name, email, password);
        createUserApi.createUser(createUser);
    }

    @Before
    public void startUp() {
        driver = SwitchBrowserClass.getDriver();
        driver.get(URL.LOGIN_HOST);
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
    @DisplayName("Тест Перехода в личный кабинет по кнопке 'Личный кабинет'")
    public void testTransitionToPersonalAccount() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickPersonalAccount();
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.waitForVisibilityOfProfileTab();
    }

    @Test
    @DisplayName("Тест Перехода к конструктору по кнопке 'Конструктор'")
    public void testTransitionToConstructor() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickPersonalAccount();
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.clickConstructorTab();
        objMainPage.waitForVisibilityOfTitle();
    }

    @Test
    @DisplayName("Тест Перехода к конструктору по логотипу")
    public void testTransitionToConstructorWithLogo() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickPersonalAccount();
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.clickLogo();
        objMainPage.waitForVisibilityOfTitle();
    }
}
