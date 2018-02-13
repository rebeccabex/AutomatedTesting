package MouseActions.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoQAPage {

    @FindBy(css = "#menu-item-140 > a")
    private WebElement draggableLink;

    @FindBy(css = "#menu-item-141 > a")
    private WebElement droppableLink;

    @FindBy(css = "#menu-item-143 > a")
    private WebElement resizableLink;

    @FindBy(css = "#menu-item-142 > a")
    private WebElement selectableLink;

    @FindBy(css = "#menu-item-151 > a")
    private WebElement sortableLink;

    @FindBy(css = "#menu-item-144 > a")
    private WebElement accordionLink;

    public void clickDraggableLink() {
        draggableLink.click();
    }

    public void clickDroppableLink() {
        droppableLink.click();
    }

    public void clickResizabableLink() {
        resizableLink.click();
    }

    public void clickselectableLink() {
        selectableLink.click();
    }

    public void clickSortableLink() {
        sortableLink.click();
    }

    public void clickAccordionLink() {
        accordionLink.click();
    }

}
