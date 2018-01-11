package MouseActions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DraggableTest {

    private DemoQA demoQA;

    @BeforeClass
    public void setup() {

        demoQA = new DemoQA("chrome");
        demoQA.begin("draggable/");

    }

    @AfterClass
    public void teardown() {
        demoQA.end(3000);
    }

    @Test(priority = 1, enabled = false)
    public void testDefaultFunctionality() {
        demoQA.clickById("ui-id-1");

        demoQA.moveByIdOffset("draggable", 100, 100);
        demoQA.moveByIdOffset("draggable", 400, -50);
        demoQA.moveByIdOffset("draggable", -200, 100);
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

    @Test(priority = 3, enabled = true)
    public void testCursorStyle() {
        demoQA.clickById("ui-id-3");

        demoQA.moveByIdOffset("drag", 100, 100);
        demoQA.moveByIdOffset("drag2", 200, 250);
        demoQA.moveByIdOffset("drag3", 300, 300);
    }

}
