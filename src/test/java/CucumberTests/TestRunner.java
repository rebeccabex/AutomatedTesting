package CucumberTests;

import Cookies.CookieTest;
import IntermediateExercises.SetupDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//@RunWith(Cucumber.class)
public class TestRunner {

    String url;
    WebDriver driver;

    ExtentReports report;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        report = new ExtentReports("C:\\Users\\Admin\\Documents\\5AutomatedTesting\\cucumber_test.html", true);

        SetupDriver setupDriver = new SetupDriver();

        driver = setupDriver.SelectBrowser("chrome");
        driver.manage().window().maximize();

        url = "https://www.bbc.co.uk/";
    }

    @AfterClass
    public void teardown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void loginTest() {
        driver.get(url);

        driver.findElement(By.id("idcta-link")).click();

        CookieTest cookieTest = new CookieTest();
        cookieTest.login();
    }

}
