package IntermediateExercises;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DemoLoginTestNG {

    private WebDriver driver;
    private String url;
    private DemoLoginTest loginTest;

    @BeforeClass
    public void firstSetup() {
        System.out.println("first setup");
    }

    @BeforeTest
    public void setup() {

        SetupDriver setupDriver = new SetupDriver();
        driver = setupDriver.SelectBrowser("firefox");
        url = "http://thedemosite.co.uk/";

        loginTest = new DemoLoginTest();
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test (groups = {"pass"}, dependsOnGroups = {"fail"})
    public void successfulLogin() throws InterruptedException {

        String username = "newuser";
        String password = "123password";

        loginTest.testCreateUser(driver, url, username, password);
        loginTest.testLogin(driver, url, username, password);
        assertTrue(loginTest.checkLoginSuccess(driver));

    }

    @Test (groups = {"fail"})
    public void invalidLogin() throws InterruptedException {

        loginTest.testCreateUser(driver, url, "newuser", "123password");
        loginTest.testLogin(driver, url, "username", "password");
        assertFalse(loginTest.checkLoginSuccess(driver));

    }

}
