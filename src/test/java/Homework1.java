import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.css.CSSStyleDeclaration;

import java.util.List;

public class Homework1 {
    private final By LOCATOR_BY_ID = By.id("elementID");
    private final By LOCATOR_BY_NAME = By.name("elementName");
    private final By LOCATOR_BY_TAGNAME = By.tagName("nameOfTag");
    private final By LOCATOR_BY_CLASS = By.className("elementClass");
    private final By LOCATOR_BY_XPATH = By.xpath(".//*[contains(@class, 'list-article__meta-info flex')]");

    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By FIND_ARTICLE = By.tagName("article");
    private final By FIND_A_TAG = By.tagName("a");
    private final By FIND_COMMENTS_BTN = By.className("article-share__item--comments");
    private final By FIND_ARTICLE_TITLE = By.className("list-article__headline");
    private final By FIND_ELEMENT_WITH_COMMENTS = By.className("list-article__comment");
    private final By FIND_TVNET_LOGO = By.className("header-logo");
    private final By FIND_TOP_MENU = By.className("menu-items--top");
    private final By FIND_RUS_BTN_LINK = By.xpath(".//a[@href = 'https://rus.tvnet.lv/']");


//    @Test
//    public void firstArticleComments() {
//        //WebDriver = browser window
//        System.setProperty("webdriver.chrome.driver", "/users/karina/Documents/chromedriver");
//        WebDriver browserWindow = new ChromeDriver();
//        browserWindow.manage().window().maximize();
//        browserWindow.get("http://tvnet.lv");
//
//        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
//        WebElement firstArticle = browserWindow.findElement(FIND_FIRST_ARTICLE);
//        firstArticle.findElement(FIND_A_TAG).click();
//        browserWindow.findElement(FIND_COMMENTS_BTN).click();
//    }
//
//    @Test
//    public void printFirstArticleTitle() {
//        //WebDriver = browser window
//        System.setProperty("webdriver.chrome.driver", "/users/karina/Documents/chromedriver");
//        WebDriver browserWindow = new ChromeDriver();
//        browserWindow.manage().window().maximize();
//        browserWindow.get("http://tvnet.lv");
//
//        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
//        WebElement articleTitle = browserWindow.findElement(FIND_ARTICLE_TITLE);
//
//        System.out.println(articleTitle.getText());
//
//    }


    //////////

    public ChromeDriver browserWindow;

    @BeforeEach
    public void setUP() {
        //WebDriver = browser window
        System.setProperty("webdriver.chrome.driver", "/users/karina/Documents/chromedriver");
        browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
    }

    @Test
    public void firstArticleComments() {
        WebElement firstArticle = browserWindow.findElement(FIND_ARTICLE);
        firstArticle.findElement(FIND_A_TAG).click();

        //Sometimes no comments(throw error)
        browserWindow.findElement(FIND_COMMENTS_BTN).click();
    }

    @Test
    public void printFirstArticleTitle() {
        WebElement articleTitle = browserWindow.findElement(FIND_ARTICLE_TITLE);

        System.out.println(articleTitle.getText());

    }

    @Test
    public void articleTitles() {
        List<WebElement> allArticles = browserWindow.findElements(FIND_ARTICLE_TITLE);

        System.out.println(allArticles);
    }

    @Test
    public void getElementsWithComments() {
        List<WebElement> elementsWithComments = browserWindow.findElements(FIND_ELEMENT_WITH_COMMENTS);

        System.out.println(elementsWithComments);
    }

    @Test
    public void getLogo() {
        WebElement tvnetLogo = browserWindow.findElement(FIND_TVNET_LOGO);

        System.out.println(tvnetLogo);
    }

    @Test
    public void switchRusBtnLink() {
        WebElement topMenu = browserWindow.findElement(FIND_TOP_MENU);
        WebElement rusBtnLink = topMenu.findElement(FIND_RUS_BTN_LINK);
        rusBtnLink.click();

//        System.out.println(rusBtnLink.getText());

    }


    @Test
    public void printArticleTitles() {
        List<WebElement> allArticles = browserWindow.findElements(FIND_ARTICLE);
        for (int i = 0; i < allArticles.size(); i++) {
            WebElement article = allArticles.get(i);
            WebElement title = article.findElement(FIND_ARTICLE_TITLE);

            System.out.println(title.getText());
        }
    }

}
