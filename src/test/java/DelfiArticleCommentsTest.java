import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DelfiArticleCommentsTest {
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By HOME_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By HOME_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By HOME_PAGE_ARTICLE = By.tagName("article");

    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");

    private final Logger LOGGER = LogManager.getLogger(DelfiArticleCommentsTest.class);

    private final By ARTICLE_COMMENTS = By.className("type-cnt");

    private WebDriver driver;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        LOGGER.info("Setting driver location");
        System.setProperty("webdriver.chrome.driver", "/users/karina/Documents/chromedriver");

        LOGGER.info("Opening browser window");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        LOGGER.info("Opening Home page");
        driver.get("http://delfi.lv");

        //--------------------HOME PAGE----------------------------------
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LOGGER.info("Waiting for accept cookies btn");
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIE_BTN));

        LOGGER.info("Accepting cookies");
        driver.findElement(ACCEPT_COOKIE_BTN).click();

        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE);
        WebElement article = articles.get(3);

        LOGGER.info("Getting article title and comments count");
        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText();
        int homePageCommentsCount = getCommentsCount(article, HOME_PAGE_COMMENTS);
        LOGGER.info("Title is: " + homePageTitle + " and comments count is: " + homePageCommentsCount);

        LOGGER.info("Opening article page");
        article.findElement(HOME_PAGE_TITLE).click();

        //-------------------ARTICLE PAGE-----------------------------------
        LOGGER.info("Getting article title and comments count");
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();
        int articlePageCommentsCount = getCommentsCount(ARTICLE_PAGE_COMMENTS);
        LOGGER.info("Title is: " + articlePageTitle + " and comments count is: " + articlePageCommentsCount);

        LOGGER.warn("Checking equality of homepage title and article title");
        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        LOGGER.warn("Checking equality of homepage title's comments count and article title's comment count");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        LOGGER.info("Opening article comments page");
        driver.findElement(ARTICLE_PAGE_COMMENTS).click();

        //--------------------COMMENTS PAGE-------------------------------
        LOGGER.info("Getting article comments count summary");
        List<WebElement> articleComments = driver.findElements(ARTICLE_COMMENTS);
        int allCommentsCount = 0;

        for (WebElement comments : articleComments) {
            int commentsQty = removeBrackets(comments);
            allCommentsCount += commentsQty;
        }
        LOGGER.info("Article comments summary Anonymous + Registered: " + allCommentsCount);

        LOGGER.warn("Checking equality of home page title's comment count and article's comments count summary");
        Assertions.assertEquals(homePageCommentsCount, allCommentsCount, "Wrong comments count in article!");
    }

    private int getCommentsCount(By locator) {
        int commentsCount = 0;

        if (!driver.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(driver.findElement(locator));
        }

        return commentsCount;
    }

    private int getCommentsCount(WebElement we, By locator) {
        int commentsCount = 0;

        if (!we.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(we.findElement(locator));
        }

        return commentsCount;
    }

    private int removeBrackets(WebElement we) {
        String commentsCountText = we.getText();
        commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) -> 36 (String)
        return Integer.parseInt(commentsCountText); // 36 (String) -> 36 (int)
    }

    @AfterEach
    public void closeBrowser() {
        LOGGER.info("Closing browser window");
        driver.close();
    }
}


//        //_________________FOR_______________________________________________________
//        for (int i = 0; i < titles.size(); i++) { //i = i + 1
//            if (!titles.get(i).getText().isEmpty()) { // !true = false !false = true
//                System.out.println(i + ": " + titles.get(i).getText());
//            }
//        }
//
//        //________________FOREACH____________________________________________________
//        for (WebElement we : titles) {
////            if (!we.getText().isEmpty()) {
////                System.out.println(we.getText());
////            } else {
////                System.out.println("----------");
////            }
//        System.out.println(we.getText().isEmpty() ? "--------" : we.getText());
//        }

