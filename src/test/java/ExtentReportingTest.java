
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExtentReportingTest {

    private WebDriver driver;
    private String url;
    private DemoLoginTest loginTest;

    ExtentReports report;
    ExtentTest test;

    @BeforeClass
    public void setup() {

        report = new ExtentReports("C:\\Users\\Admin\\Documents\\5AutomatedTesting\\automation_report.html", true);

        SetupDriver setupDriver = new SetupDriver();
        driver = setupDriver.SelectBrowser("firefox");

        url = "http://thedemosite.co.uk/";

        loginTest = new DemoLoginTest();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void validUsernameTest() throws InterruptedException {

        test = report.startTest("Verify application test");

        String username = "newuser";
        String password = "123password";


        loginTest.testCreateUser(driver, url, username, password);
        test.log(LogStatus.INFO, "User created");

        loginTest.testLogin(driver, url, username, password);
        if (loginTest.checkLoginSuccess(driver)) {
            test.log(LogStatus.PASS, "Login valid");
        } else {
            test.log(LogStatus.FAIL, "Login failed");
        }

        report.endTest(test);
        report.flush();
    }

    @Test
    public void invalidUsernameTest() throws InterruptedException {
        test = report.startTest("Verify application test");

        String username = "newuser";
        String password = "123password";


        loginTest.testCreateUser(driver, url, username, password);
        test.log(LogStatus.INFO, "User created");

        loginTest.testLogin(driver, url, "username", "password");
        if (loginTest.checkLoginSuccess(driver)) {
            test.log(LogStatus.PASS, "Login valid");
        } else {
            test.log(LogStatus.FAIL, "Login failed");
        }

        report.endTest(test);
        report.flush();
    }

}
