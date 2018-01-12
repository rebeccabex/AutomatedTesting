package Cookies;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import IntermediateExercises.SetupDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Homework {

    WebDriver driver = null;

    Actions action = null;

    String url;

    File f = null;

    @Before

    public void setUp() throws InterruptedException {

        SetupDriver setupDriver = new SetupDriver();

        driver = setupDriver.SelectBrowser("chrome");

        action = new Actions(driver);

        url = "https://www.bbc.co.uk/";

        driver.get(url);

        driver.manage().window().maximize();

        f = new File("browser.data");

        f.delete();
        try {
            f.createNewFile();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test

    public void homeworkCookiesBBC() throws IOException, ParseException {

        WebElement loginButton = driver.findElement(By.id("idcta-link"));
        loginButton.click();

        WebElement emailButton = driver.findElement(By.cssSelector("#user-identifier-input"));
        emailButton.click();
        emailButton.sendKeys("rebeccabexb@gmail.com");
        action.sendKeys(Keys.TAB).perform();
        action.sendKeys("temporary1101").perform();
        action.sendKeys(Keys.ENTER).perform();

        BufferedWriter bos = new BufferedWriter(new FileWriter(f));

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line;

        for (Cookie ck : driver.manage().getCookies()) {
            bos.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";"
                    + ck.getExpiry() + ";" + ck.isSecure()));
            bos.newLine();
        }

        bos.close();

        WebElement button = driver.findElement(By.cssSelector("#idcta-username"));

        button.click();

        WebElement signoutButton = driver.findElement(By.cssSelector(
                "#orb-modules > div.___iso-html___ > div > div > div.background.background--attenborough > div.u-background-transparent-black > div > div > div > div > nav > ul > li:nth-child(3) > a > span"));

        signoutButton.click();

// WebElement continueButton = driver
// .findElement(By.cssSelector("#app-container > div > div > div > div > div >
// a"));
//
// continueButton.click();

        driver.get("https://www.google.co.uk/?gws_rd=ssl");

        DateFormat fromDateString = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
        try {
            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String dt;

                    if (!(dt = str.nextToken()).equals("null")) {
                        expiry = fromDateString.parse(dt);
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


// action.sendKeys(Keys.F5).perform();

// HelperMethods.sleepHelper();

        driver.get(url);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement hutton = driver.findElement(By.id("idcta-link"));
        hutton.click();

// loginButton.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @After

    public void tearDown() {

// driver.quit();

    }

}