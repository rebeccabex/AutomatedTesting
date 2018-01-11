package MouseActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SortableTest {

    private DemoQA demoQA;

    @BeforeClass
    public void setup() {

        demoQA = new DemoQA("chrome");
        demoQA.begin("sortable/");

    }

    @AfterClass
    public void teardown() {
        demoQA.end(3000);
    }

    @Test
    public void testDefaultFunctionality() {

        demoQA.clickById("ui-id-1");

        demoQA.wait(1000);


        Actions actions = new Actions(demoQA.getDriver());

        actions.clickAndHold(demoQA.getDriver().findElement(By.cssSelector("#sortable > li:nth-child(1)"))).
                moveByOffset(0, 100).release().build().perform();

        demoQA.wait(1000);

        actions.clickAndHold(demoQA.getDriver().findElement(By.cssSelector("#sortable > li:nth-child(6)"))).
                moveByOffset(0, -30).release().build().perform();

        demoQA.wait(1000);

    }


}
