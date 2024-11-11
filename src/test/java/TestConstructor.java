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
import resources.SwitchBrowserClass;


public class TestConstructor {
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
    @DisplayName("Проверка перехода к разделу 'Начинки'")
    public void testTransitionToFilling() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForVisibilityOfTitle();
        objMainPage.clickFillings();
        objMainPage.waitForVisibilityOfFillingsHeader();
        objMainPage.assertTitleTabFillings();
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Соусы'")
    public void testTransitionToSauces() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForVisibilityOfTitle();
        objMainPage.clickSauces();
        objMainPage.waitForVisibilityOfSaucesHeader();
        objMainPage.assertTitleTabSauces();
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Булки'")
    public void testTransitionToBuns() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForVisibilityOfTitle();
        objMainPage.clickFillings();
        objMainPage.waitForVisibilityOfFillingsHeader();
        objMainPage.clickBun();
        objMainPage.waitForVisibilityOfBunHeader();
        objMainPage.assertTitleTabBuns();
        objMainPage.waitForVisibilityOfBunHeader();
    }
}