package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunc {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    WebDriver driver;
    WebDriverWait wait;

    public BaseFunc() {
        LOGGER.info("Starting web browser");
        System.setProperty("webdriver.chrome.driver", "/users/karina/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    public void openPage(String url) {
        LOGGER.info("Opening page by URL: " + url);

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        driver.get(url);
    }

    public void click(By locator) {
        LOGGER.info("Clicking on element by: " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void click(WebElement element) {
        LOGGER.info("Clicking on web elemenet");
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public List<WebElement> findElements(By locator) {
        LOGGER.info("Gettitng list of elements by: " + locator);
        return driver.findElements(locator);
    }

    public List<WebElement> findElements(WebElement parrent, By child) {
        LOGGER.info("Getting all child elements");
        return parrent.findElements(child);
    }

    public WebElement findElement(By locator) {
        LOGGER.info("Getting element by: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getText(WebElement parent, By child) {
        LOGGER.info("Getting text for child elements by locator");
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child)).getText();
        return parent.findElement(child).getText();
    }

    public String getText(By locator) {
        LOGGER.info("Getting text from element");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public String getText(WebElement we) {

        return wait.until(ExpectedConditions.visibilityOf(we)).getText();
    }

    public String getText(By parent, By child) {
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child)).getText();
    }


    public void closeBrowser() {
        LOGGER.info("Closing browser window");
        if (driver != null) {
            driver.close();
        }
    }

    public void select(By dropdown, String text) {
        LOGGER.info("Selecting " + text + " from dropdown by locator: " + dropdown);
        Select select = new Select(findElement(dropdown));
        select.selectByVisibleText(text);
    }

    public void type(By locator, String text) {
        LOGGER.info("Typing " + text + " into " + locator);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        input.clear();
        input.sendKeys(text);
    }

    public void type(By locator, int text) {
        type(locator, String.valueOf(text));
    }

    public void waitForElementsCountToBeMoreThen(By locator, int count) {
        LOGGER.info("Waiting for elements count to be " + count);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, count));
    }
}
