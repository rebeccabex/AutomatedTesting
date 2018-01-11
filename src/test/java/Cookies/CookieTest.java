package Cookies;

import IntermediateExercises.SetupDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class CookieTest {

    String url;
    WebDriver driver;

    ExtentReports report;
    ExtentTest test;

    @BeforeClass
    public void setup() {

        report = new ExtentReports("C:\\Users\\Admin\\Documents\\5AutomatedTesting\\cookie_test.html", true);

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
    public void cookieTest() {
//        login();
//        createCookie();
//        logout();

        driver.get("https://www.google.co.uk/");
        loadCookie();
    }


    public void login() {
        test = report.startTest("Verify login");

        driver.get(url);

        driver.findElement(By.id("idcta-link")).click();
        driver.findElement(By.id("user-identifier-input")).sendKeys("rebeccabexb@gmail.com");

        // need to set temp password if using
        driver.findElement(By.id("password-input")).sendKeys("");
        driver.findElement(By.id("submit-button")).click();

        test.log(LogStatus.INFO, "Attempted to login");

        checkLogin();

        report.endTest(test);
        report.flush();
    }

    public void createCookie() {

        File f;
        BufferedWriter buf = null;

        try {
            f = new File("browser.data");
            f.delete();
            f.createNewFile();
            buf = new BufferedWriter(new FileWriter(f));

            for (Cookie ck: driver.manage().getCookies()) {
                buf.write(ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() +
                ";" + ck.getPath() + ";" + ck.getExpiry() + ";" + ck.isSecure());
                buf.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buf != null) {
                    buf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void logout() {
        test = report.startTest("Verify logout");

        driver.findElement(By.id("idcta-link")).click();
        driver.findElement(By.cssSelector("#orb-modules > div.___iso-html___ > div > div > " +
                "div.background.background--attenborough > div.u-background-transparent-black > " +
                "div > div > div > div > nav > ul > li:nth-child(3) > a")).click();

        test.log(LogStatus.INFO, "Attempted to logout");

        checkLogout();

        report.endTest(test);
        report.flush();
    }

    public void loadCookie() {

        test = report.startTest("Cookie login");

        try {
            File f = new File("browser.data");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String dt;

                    if (!(dt=str.nextToken()).equals("null")) {
                        expiry = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(dt);
                    }
                    boolean isSecure = new Boolean(str.nextToken()).booleanValue();
                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                    driver.manage().addCookie(ck);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        test.log(LogStatus.INFO, "Attempted to login with cookie");

        driver.get(url);
        checkLogin();

        report.endTest(test);
        report.flush();
    }

    public void checkLogin() {

        driver.findElement(By.id("idcta-link")).click();
        driver.findElement(By.id("idcta-link")).click();

        String bodyText = driver.findElement(By.tagName("body")).getText();

        if (bodyText.contains("Your account")) {
            test.log(LogStatus.PASS, "Login valid");
        } else {
            test.log(LogStatus.FAIL, "Login failed");
        }
    }

    public void checkLogout() {

        String check = driver.findElement(By.id("idcta-username")).getText();

        if (check.equalsIgnoreCase("Sign in")) {
            test.log(LogStatus.PASS, "Login valid");
        } else {
            test.log(LogStatus.FAIL, "Login failed");
        }
    }

}
