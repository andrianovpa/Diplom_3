package resources;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SwitchBrowserClass {

    public static WebDriver getDriver() {
        Properties properties = new Properties();

        try (InputStream input = SwitchBrowserClass.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Не удалось найти файл config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = properties.getProperty("browser");

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else {
            throw new RuntimeException("Браузер не поддерживается: " + browser);
        }
    }
}
