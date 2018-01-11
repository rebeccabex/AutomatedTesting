package MouseActions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResizableTest {

    private DemoQA demoQA;

    @BeforeClass
    public void setup() {

        demoQA = new DemoQA("chrome");
        demoQA.begin("resizable/");

    }

    @AfterClass
    public void teardown() {
        demoQA.end(3000);
    }

    @Test
    public void testDefaultFunctionality() {

        demoQA.wait(1000);
        demoQA.resizeByCSS("#resizable > div.ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se", 100, 100);
        demoQA.resizeByCSS("#resizable > div.ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se", -50, 50);

    }

}
