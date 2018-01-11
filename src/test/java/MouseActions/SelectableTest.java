package MouseActions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectableTest {

    private DemoQA demoQA;

    @BeforeClass
    public void setup() {

        demoQA = new DemoQA("chrome");
        demoQA.begin("selectable/");

    }

    @AfterClass
    public void teardown() {
        demoQA.end(3000);
    }

    @Test(priority = 1, enabled = false)
    public void testDefaultFunctionality() {
        demoQA.clickById("ui-id-1");

        demoQA.wait(1000);

        for (int i = 1; i <= 7; i++) {
            demoQA.clickByCSS("#selectable > li:nth-child(" + i + ")");
            demoQA.wait(1000);
        }

    }

    @Test(priority = 2)
    public void testSerialize() {
        demoQA.clickById("ui-id-3");

        for (int i = 1; i <= 6; i++) {
            demoQA.clickByCSS("#selectable-serialize > li:nth-child(" + i + ")");
            demoQA.wait(1000);
        }

        Actions actions = new Actions(demoQA.getDriver());

        WebDriver driver = demoQA.getDriver();

        actions.keyDown(Keys.LEFT_CONTROL).
                moveToElement(driver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(1)"))).click().
                moveToElement(driver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(4)"))).click().
                moveToElement(driver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(6)"))).click().
                keyUp(Keys.LEFT_CONTROL).build().perform();

        demoQA.wait(1000);

        actions.moveToElement(driver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(4)"))).clickAndHold().
                moveToElement(driver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(6)"))).click().
                build().perform();
    }
}
