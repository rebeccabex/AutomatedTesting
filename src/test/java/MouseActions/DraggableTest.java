package MouseActions;

import MouseActions.Pages.DemoQAPage;
import MouseActions.Pages.DraggablePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

public class DraggableTest {

    static private DemoQA demoQA;
    static private WebDriver driver;
    static private DemoQAPage homePage;
    static private DraggablePage draggable;

    @BeforeClass
    public static void setup() {
        demoQA = new DemoQA("firefox");
        driver = demoQA.beginAndGetDriver();
        homePage = demoQA.getHomePage();
        draggable = PageFactory.initElements(driver, DraggablePage.class);

    }

    @AfterClass
    public void teardown() {
        demoQA.end(3000);
    }

    @BeforeMethod
    public void openDraggablePage() {
        homePage.clickDraggableLink();
    }

    @Test(priority = 1, enabled = true)
    public void testDefaultFunctionality() {
        draggable.clickDefaultTab();
        demoQA.wait(1000);
        draggable.moveDraggableBox(new Actions(driver),100, 100);
        demoQA.wait(1000);
        draggable.moveDraggableBox(new Actions(driver),-50, 200);
        demoQA.wait(1000);
    }

    @Test(priority = 2, enabled = false)
    public void testConstrainMovement() {
        demoQA.clickById("ui-id-2");

        demoQA.moveByIdOffset("draggabl", 0, 100);
        demoQA.moveByIdOffset("draggabl", 0, -200);

        demoQA.moveByIdOffset("draggabl2", 100, 0);
        demoQA.moveByIdOffset("draggabl2", -200, 0);

        demoQA.moveByIdOffset("draggabl3", 50, -50);
        demoQA.moveByIdOffset("draggabl3", -100, 100);

        demoQA.moveByIdOffset("draggabl5", 100, 0);
        demoQA.moveByIdOffset("draggabl5", -200, 0);

    }

    @Test(priority = 3, enabled = false)
    public void testCursorStyle() {
        demoQA.clickById("ui-id-3");

        demoQA.moveByIdOffset("drag", 100, 100);
        demoQA.moveByIdOffset("drag2", 200, 250);
        demoQA.moveByIdOffset("drag3", 300, 300);
    }

}
