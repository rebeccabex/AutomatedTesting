package MouseActions.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DraggablePage {

    @FindBy(css = "#ui-id-1")
    private WebElement defaultTab;

    @FindBy(id = "draggable")
    private WebElement draggableBox;

    public void clickDefaultTab() {
        defaultTab.click();
    }

    public void moveDraggableBox(Actions myMouse, int x, int y) {
        myMouse.dragAndDropBy(draggableBox, x, y);
    }
}
