import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginTestSettings {

    private WebDriver driver;
    private String url;
    private DemoLoginTest loginTest;
    private SetupDriver setupDriver;

    private ExtentReports report;
    private ExtentTest test;
    private HashMap<String, BrowserSettings> settings;

    @DataProvider (name = "test1")
    public Object[][] setBrowser() {
        return new Object[][] {
                {"chrome"},
                {"firefox"}
        };
    }

    @BeforeClass
    public void setup() {
        report = new ExtentReports("C:\\Users\\Admin\\Documents\\5AutomatedTesting\\automation_report.html", true);

        String settingsFile = "C:\\Users\\Admin\\Documents\\5AutomatedTesting\\settings.xlsx";
        ReadSettings readSettings = new ReadSettings();

        ArrayList<String> settingsList = readSettings.readExcelDoc(settingsFile);
        settings = new HashMap<String, BrowserSettings>();

        setupDriver = new SetupDriver();
        url = "http://thedemosite.co.uk/";

        String browser = "";
        String username = "";
        String password = "";

        for (String s: settingsList) {
            if (s.toLowerCase().contains("browser")) {
                browser = s.substring(s.indexOf(":") + 2);
            } else if (s.toLowerCase().contains("username")) {
                username = s.substring(s.indexOf(":") + 2);
            } else if (s.toLowerCase().contains("password")) {
                password = s.substring(s.indexOf(":") + 2);
            }
            if (!"".equals(browser) && !"".equals(username) && !"".equals(password)) {
                BrowserSettings newSettings = new BrowserSettings(browser, username, password);
                settings.put(browser, newSettings);
                browser = "";
                username = "";
                password = "";
            }
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
    }

    @Test(dataProvider =  "test1")
    public void validUsernameTest(String browser) throws InterruptedException {

        driver = setupDriver.SelectBrowser(browser);

        test = report.startTest("Verify application test");

        if (driver != null) {
            loginTest = new DemoLoginTest();

            String username = settings.get(browser).getUsername();
            String password = settings.get(browser).getPassword();

            loginTest.testCreateUser(driver, url, username, password);
            test.log(LogStatus.INFO, "User created");

            loginTest.testLogin(driver, url, username, password);

            if (loginTest.checkLoginSuccess(driver)) {
                test.log(LogStatus.PASS, "Login valid for " + browser);
            } else {
                test.log(LogStatus.FAIL, "Login failed for " + browser);
            }

        } else {
            test.log(LogStatus.FAIL, "Driver failed for " + browser);
        }

        closeBrowser(driver);

        report.endTest(test);
        report.flush();

    }

    @Test(dataProvider =  "test1")
    public void invalidUsernameTest(String browser) throws InterruptedException {

        driver = setupDriver.SelectBrowser(browser);

        test = report.startTest("Verify application test");

        loginTest = new DemoLoginTest();

        String username = settings.get(browser).getUsername();
        String password = settings.get(browser).getPassword();

        loginTest.testCreateUser(driver, url, username, password);
        test.log(LogStatus.INFO, "User created");

        loginTest.testLogin(driver, url, "username", "password");
        if (loginTest.checkLoginSuccess(driver)) {
            test.log(LogStatus.PASS, "Login valid for " + browser);
        } else {
            test.log(LogStatus.FAIL, "Login failed for " + browser);
        }

        closeBrowser(driver);

        report.endTest(test);
        report.flush();
    }

    public void closeBrowser(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

}
