package pageobject.pages.TvNetPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

import java.util.List;

public class HomePage {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By ARTICLE = By.tagName("article");
    private final By TITLE = By.className("list-article__headline");
    private final By COMMENTS = By.className("list-article__comment");


    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void acceptCokkies() {
        LOGGER.info("Accepting cookies");
        baseFunc.click(ACCEPT_COOKIES_BTN);
    }

    public WebElement getArticleById(int id) {
        LOGGER.info("Getting article Nr. " + (id + 1));
        List<WebElement> articles = baseFunc.findElements(ARTICLE);
        Assertions.assertFalse(articles.isEmpty(), "There are no articles");
        Assertions.assertTrue(articles.size() > id, "Article amount is less than id");

        return articles.get(id);
    }

    public String getTitle(int id) {
        LOGGER.info("Getting title for article with id: " + (id + 1));
        List<WebElement> articles = baseFunc.findElements(TITLE);
        WebElement article = articles.get(id);
        String articleTitle = article.getText();
        boolean withComments = !article.findElements(COMMENTS).isEmpty();

        if (withComments) {
            String commentsBtnText = article.findElement(COMMENTS).getText();
            articleTitle = articleTitle.replace(commentsBtnText, "");
        }

        return articleTitle;
    }

    public int getCommentsCount(int id) {
        LOGGER.info("Getting comments count for article with id: " + (id + 1));

        if (baseFunc.findElements(getArticleById(id), COMMENTS).isEmpty()) {
            return 0;
        } else {
            String commentsCountToParse = baseFunc.getText(getArticleById(id), COMMENTS);
            commentsCountToParse = commentsCountToParse.substring(1, commentsCountToParse.length() - 1);
            return Integer.parseInt(commentsCountToParse);
        }
    }
}


