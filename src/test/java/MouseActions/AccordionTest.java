package MouseActions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccordionTest {

    private DemoQA demoQA;

    @BeforeClass
    public void setup() {

        demoQA = new DemoQA("chrome");
        demoQA.begin("accordion/");

    }

    @AfterClass
    public void teardown() {
        demoQA.end(3000);
    }

    @Test
    public void testDefaultFunctionality() {

        demoQA.clickById("ui-id-1");

        for (int i = 10; i >= 4; i -= 2) {
            demoQA.clickById("ui-id-" + i);
            demoQA.wait(1000);
        }

    }

}
