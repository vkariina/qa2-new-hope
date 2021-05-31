import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class Homework1 {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By FIND_ARTICLE = By.tagName("article");
    private final By FIND_A_TAG = By.tagName("a");
    private final By FIND_COMMENTS_BTN = By.className("article-share__item--comments");
    private final By FIND_ARTICLE_TITLE = By.className("list-article__headline");
    private final By FIND_ELEMENT_WITH_COMMENTS = By.className("list-article__comment");
    private final By FIND_TVNET_LOGO = By.className("header-logo");
    private final By FIND_TOP_MENU = By.className("menu-items--top");
    private final By FIND_RUS_BTN_LINK = By.xpath(".//a[@href = 'https://rus.tvnet.lv/']");
    private final By FIND_ARTICLE_COMMENTS = By.className("list-article__comment");

    private final Logger LOGGER = LogManager.getLogger(Homework1.class);

    private ChromeDriver driver;

    @BeforeEach
    public void setUP() {
        LOGGER.info("Setting driver location");
        System.setProperty("webdriver.chrome.driver", "/users/karina/Documents/chromedriver");

        LOGGER.info("Opening browser window");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        LOGGER.info("Opening Home page");
        driver.get("http://tvnet.lv");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        LOGGER.info("Waiting for accept cookies btn");
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BTN));

        LOGGER.info("Accepting cookies");
        driver.findElement(ACCEPT_COOKIES_BTN).click();
    }

    // 1. Перейти на http://tvnet.lv, открыть первую статью и перейти на страницу комментариев.
    @Test
    public void firstArticleComments() {
        LOGGER.info("Opening first article page and comments page");
        WebElement firstArticle = driver.findElement(FIND_ARTICLE);
        LOGGER.info("Opening first article page");
        firstArticle.findElement(FIND_A_TAG).click();

        LOGGER.info("Opening article comments page");
        driver.findElement(FIND_COMMENTS_BTN).click();
    }

    // 2. Перейти на http://tvnet.lv и распечатать в консоль заголовок первой статьи.
    @Test
    public void printFirstArticleTitle() {
        LOGGER.info("Getting first article's title text");
        WebElement articleTitle = driver.findElement(FIND_ARTICLE_TITLE);
        String articleTitleText = articleTitle.getText();
        boolean withComments = !articleTitle.findElements(FIND_ARTICLE_COMMENTS).isEmpty();

        if (withComments) {
            String commentsBtnText = articleTitle.findElement(FIND_ARTICLE_COMMENTS).getText();
            articleTitleText = articleTitle.getText().replace(commentsBtnText, "");
        }

        LOGGER.info("First article title is: " + articleTitleText);
    }

    // 3. Создать и расписать локаторы для :
    // 3.1. Всех заголовков статей
    @Test
    public void articleTitles() {
        List<WebElement> allArticles = driver.findElements(FIND_ARTICLE_TITLE);

        System.out.println(allArticles);
    }

    // 3.2. Всех элементов с количеством комментариев.
    @Test
    public void getElementsWithComments() {
        List<WebElement> elementsWithComments = driver.findElements(FIND_ELEMENT_WITH_COMMENTS);

        System.out.println(elementsWithComments);
    }

    // 3.3. Для логотипа tvnet.lv
    @Test
    public void getLogo() {
        WebElement tvnetLogo = driver.findElement(FIND_TVNET_LOGO);

        System.out.println(tvnetLogo);
    }

    // 3.4. Для ссылки переключения на русский язык (RUS).
    @Test
    public void switchRusBtnLink() {
        LOGGER.info("Finding RUS button link");
        WebElement topMenu = driver.findElement(FIND_TOP_MENU);
        WebElement rusBtnLink = topMenu.findElement(FIND_RUS_BTN_LINK);

        LOGGER.info("Change page language to russian");
        rusBtnLink.click();
    }

    // 4. Распечатать в консоль заголовки всех статей на главной странице.
    @Test
    public void printArticleTitles() {
        LOGGER.info("Getting all main page articles title list");
        List<WebElement> allArticles = driver.findElements(FIND_ARTICLE);
        for (int i = 0; i < allArticles.size(); i++) {
            WebElement article = allArticles.get(i);
            String title = article.findElement(FIND_ARTICLE_TITLE).getText();
            boolean withComments = !article.findElements(FIND_ARTICLE_COMMENTS).isEmpty();
            int articleCounter = i + 1;

            if (withComments) {
                String commentsBtnText = article.findElement(FIND_ARTICLE_COMMENTS).getText();
                title = title.replace(commentsBtnText, "");
            }

            LOGGER.info("Article title " + articleCounter + ": " + title);
        }
    }

    // 5. Распечатать в консоль заголовки всех статей на главной странице,
    // но и рядом отдельно проставить колличество комментариев.
    @Test
    public void printArticleInfo() {
        LOGGER.info("Getting all main page articles title list and comments count");
        List<WebElement> allArticles = driver.findElements(FIND_ARTICLE);
//        for (WebElement article : allArticles) {
        for (int i = 0; i < allArticles.size(); i++) {
            WebElement article = allArticles.get(i);
            WebElement title = article.findElement(FIND_ARTICLE_TITLE);
            boolean withComments = !article.findElements(FIND_ARTICLE_COMMENTS).isEmpty();
            String commentsBtnText;
            String finalResult;
            int articleCounter = i + 1;

            if (withComments) {
                commentsBtnText = article.findElement(FIND_ARTICLE_COMMENTS).getText();
                String withoutBrackets = commentsBtnText.substring(1, commentsBtnText.length() - 1);
                finalResult = title.getText().replace(commentsBtnText, " : " + withoutBrackets);
            } else {
                finalResult = title.getText() + " : 0";
            }

            LOGGER.info("Article title " + articleCounter + ": " + finalResult);
        }
    }

    @AfterEach
    public void finished() {
        LOGGER.info("Closing browser window");
        driver.close();
    }
}
