package MouseActions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DroppableTest {

    private DemoQA demoQA;

    @BeforeClass
    public void setup() {

        demoQA = new DemoQA("chrome");
        demoQA.begin("droppable/");

    }

    @AfterClass
    public void teardown() {
        demoQA.end(3000);
    }

    @Test
    public void testDefaultFunctionality() {

        demoQA.wait(1000);
        demoQA.moveByIdToElementById("draggableview", "droppableview");

    }

}
