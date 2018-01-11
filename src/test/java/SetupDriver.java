import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SetupDriver {

    public WebDriver SelectBrowser(String browser) {

        WebDriver driver = null;

        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Documents/webdriver/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:/Users/Admin/Documents/webdriver/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        return driver;
    }
}
