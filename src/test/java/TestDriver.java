import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class TestDriver {

    WebDriver driver;

    @Before
    public void setup(){

        SetupDriver setupDriver = new SetupDriver();
        driver = setupDriver.SelectBrowser("chrome");

    }

    @After
    public void tearDown() throws InterruptedException {

        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        driver.get("http://www.google.com/xhtml");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Hello World");
        searchBox.submit();
    }

    @Test
    public void testQA() throws InterruptedException {

        String url = "https://www.qa.com/";

        driver.get(url);
        Thread.sleep(5000);  // Let the user actually see something!

        assertEquals(url, driver.getCurrentUrl());

    }

}