package MouseActions;

import MouseActions.Pages.DemoQAPage;
import MouseActions.Pages.DraggablePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DemoQA {

    private String url;
    private WebDriver driver;

    public DemoQA(String browser) {
        this.url = "http://demoqa.com/";

        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Documents/webdriver/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:/Users/Admin/Documents/webdriver/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.navigate().to(url);
    }

    public WebDriver begin(String page) {
        driver.manage().window().maximize();
        driver.navigate().to(url + "/" + page);
        return driver;
    }

    public WebDriver beginAndGetDriver() {
        driver.manage().window().maximize();
        driver.navigate().to(url);
        return driver;
    }

    public void end(int delay) {
        wait(delay);
        driver.quit();
    }

    public DemoQAPage getHomePage() {
        return PageFactory.initElements(driver, DemoQAPage.class);
    }

    public void typeTextById(String elementId, String text) {
        WebElement el = driver.findElement(By.id(elementId));
        el.sendKeys(text);
    }

    public void selectOptionByName(String elementName, String value) {

        List<WebElement> options = driver.findElements(By.name(elementName));

        for (int i = 0; i <options.size(); i++) {
            if (options.get(i).getAttribute("value").equalsIgnoreCase(value)) {
                options.get(i).click();
                break;
            }
        }
    }

    public void selectOptionByCSS(String selector) {
        driver.findElement(By.cssSelector(selector)).click();
    }

    public void selectFromDropDownById(String elementId, String value) {
        Select dropdown = new Select(driver.findElement(By.id(elementId)));
        dropdown.selectByValue(value);
    }

    public void moveByIdOffset(String id, int xOffset, int yOffset) {
        Actions myMouse = new Actions(driver);
        myMouse.dragAndDropBy(driver.findElement(By.id(id)), xOffset, yOffset).perform();
        wait(1000);
    }

    public void moveByIdToElementById(String moveId, String targetId) {
        Actions myMouse = new Actions(driver);
        myMouse.dragAndDrop(driver.findElement(By.id(moveId)), driver.findElement(By.id(targetId))).perform();
        wait(1000);
    }

    public void resizeByCSS(String cssSelector, int xOffset, int yOffste) {
        Actions myMouse = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        myMouse.clickAndHold(element).moveByOffset(xOffset,yOffste).release().build().perform();
        wait(1000);
    }

    public void clickByName(String elementName) {
        driver.findElement(By.name(elementName)).click();
    }

    public void clickById(String elementId) {
        driver.findElement(By.id(elementId)).click();
    }

    public void clickByCSS(String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void clickByXpath(String cssSelector) {
        driver.findElement(By.xpath(cssSelector)).click();
    }

    public WebElement getElementById(String id) {
        return driver.findElement(By.id(id));
    }

    public void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
