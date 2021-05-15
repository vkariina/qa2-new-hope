import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DelfiArticleCommentsTest {
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By HOME_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By HOME_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By HOME_PAGE_ARTICLE = By.tagName("article");

    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");

    private WebDriver driver;

    @Test
    public void titleAndCommentsCountCheck() {
        System.setProperty("webdriver.chrome.driver", "/users/karina/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://delfi.lv");

        //-----------------------HOME PAGE----------------------------------
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIE_BTN));

        driver.findElement(ACCEPT_COOKIE_BTN).click();

        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE);
        WebElement article = articles.get(4);

        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText();
        int homePageCommentsCount = getCommentsCount(article, HOME_PAGE_COMMENTS);

        article.findElement(HOME_PAGE_TITLE).click();

        //-------------------ARTICLE PAGE--------------------
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();
        int articlePageCommentsCount = getCommentsCount(ARTICLE_PAGE_COMMENTS);

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");
    }

    private int getCommentsCount(By locator) {
        int commentsCount = 0;

        if (!driver.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(driver.findElement(locator));
        }

        return commentsCount;
    }

    private  int getCommentsCount(WebElement we, By locator) {
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

