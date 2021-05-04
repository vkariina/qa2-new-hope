import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.css.CSSStyleDeclaration;

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

    // 1. Перейти на http://tvnet.lv, открыть первую статью и перейти на страницу комментариев.
    @Test
    public void firstArticleComments() {
        WebElement firstArticle = browserWindow.findElement(FIND_ARTICLE);
        firstArticle.findElement(FIND_A_TAG).click();

        //Sometimes no comments(throw error)
        browserWindow.findElement(FIND_COMMENTS_BTN).click();
    }

    // 2. Перейти на http://tvnet.lv и распечатать в консоль заголовок первой статьи.
    @Test
    public void printFirstArticleTitle() {
        WebElement articleTitle = browserWindow.findElement(FIND_ARTICLE_TITLE);

        System.out.println(articleTitle.getText());

    }

    // 3. Создать и расписать локаторы для :
    // 3.1. Всех заголовков статей
    @Test
    public void articleTitles() {
        List<WebElement> allArticles = browserWindow.findElements(FIND_ARTICLE_TITLE);

        System.out.println(allArticles);
    }

    // 3.2. Всех элементов с количеством комментариев.
    @Test
    public void getElementsWithComments() {
        List<WebElement> elementsWithComments = browserWindow.findElements(FIND_ELEMENT_WITH_COMMENTS);

        System.out.println(elementsWithComments);
    }

    // 3.3. Для логотипа tvnet.lv
    @Test
    public void getLogo() {
        WebElement tvnetLogo = browserWindow.findElement(FIND_TVNET_LOGO);

        System.out.println(tvnetLogo);
    }

    // 3.4. Для ссылки переключения на русский язык (RUS).
    @Test
    public void switchRusBtnLink() {
        WebElement topMenu = browserWindow.findElement(FIND_TOP_MENU);
        WebElement rusBtnLink = topMenu.findElement(FIND_RUS_BTN_LINK);
        rusBtnLink.click();

//        System.out.println(rusBtnLink.getText());

    }

    // 4. Распечатать в консоль заголовки всех статей на главной странице.
    @Test
    public void printArticleTitles() {
        List<WebElement> allArticles = browserWindow.findElements(FIND_ARTICLE);
        for (int i = 0; i < allArticles.size(); i++) {
            WebElement article = allArticles.get(i);
            WebElement title = article.findElement(FIND_ARTICLE_TITLE);
            System.out.println(title.getText());
        }
    }

    // 5. Распечатать в консоль заголовки всех статей на главной странице,
    // но и рядом отдельно проставить колличество комментариев.
    @Test
    public void printArticleInfo() {
        List<WebElement> allArticles = browserWindow.findElements(FIND_ARTICLE);
        for (WebElement article : allArticles) {
            WebElement title = article.findElement(FIND_ARTICLE_TITLE);
            boolean withoutComments = article.findElements(FIND_ARTICLE_COMMENTS).isEmpty();
            String articleInfo;

            //Short Version IF/ELSE
            //String articleInfo = withoutComments ? title.getText() + " (0)" : title.getText() ;


            if (withoutComments) {
                articleInfo = title.getText() + " (0)";
            } else {
                articleInfo = title.getText();
            }

            System.out.println(articleInfo);
        }
    }

}
