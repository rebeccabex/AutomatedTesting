package IntermediateExercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DemoLoginTest {

    private WebDriver driver;
    private String url;

    @Before
    public void setup() {

        SetupDriver setupDriver = new SetupDriver();
        driver = setupDriver.SelectBrowser("chrome");
        url = "http://thedemosite.co.uk/";

    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
//    public void testCreateUserAndLogin() throws InterruptedException {
//
//        String username = "newuser";
//        String password = "123password";
//
//        testCreateUser(driver, url, username, password);
//        testLogin(driver, url, username, password);
//
//        assertTrue(checkLoginSuccess(driver));
//
//    }

    public void testCreateUser(WebDriver driver, String url, String username, String password) throws InterruptedException {
        String addUserUrl = url + "addauser.php";
        driver.get(addUserUrl);

        Thread.sleep(1000);

        WebElement usernameBox = driver.findElement(By.name("username"));
        usernameBox.sendKeys(username);

        Thread.sleep(1000);

        WebElement passwordBox = driver.findElement(By.name("password"));
        passwordBox.sendKeys(password);

        Thread.sleep(1000);

        WebElement saveButton = driver.findElement(By.name("FormsButton2"));
        saveButton.click();
    }

    public void testLogin(WebDriver driver, String url, String username, String password) throws InterruptedException {

        String loginUrl = url + "login.php";
        driver.get(loginUrl);

        Thread.sleep(1000);

        WebElement usernameBox = driver.findElement(By.name("username"));
        usernameBox.sendKeys(username);

        Thread.sleep(1000);

        WebElement passwordBox = driver.findElement(By.name("password"));
        passwordBox.sendKeys(password);

        Thread.sleep(1000);

        WebElement saveButton = driver.findElement(By.name("FormsButton2"));
        saveButton.click();
    }

    public boolean checkLoginSuccess(WebDriver driver) {
        WebElement confirmLogin = driver.findElement(By.cssSelector("body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));

        return confirmLogin.getText().equalsIgnoreCase("**Successful Login**");
    }


}
