package pageobject.pages.TvNetPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

import java.util.List;

public class ArticlePage {
    private final By TITLE = By.className("article-headline");
    private final By COMMENTS = By.className("article-share__item--comments");


    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return baseFunc.getText(TITLE);
    }

    public int emptyNumberCheck(String text) {
        if(text.isEmpty()) {
            return  0;
        } else {
            return Integer.parseInt(text);
        }
    }

    public int getCommentsCount() {
        LOGGER.info("Getting article comments count");
        List<WebElement> articleComments = baseFunc.findElements(COMMENTS);

        if (articleComments.isEmpty()) {
            return 0;
        } else {
            String commentsCount = articleComments.get(0).getText();
            return this.emptyNumberCheck(commentsCount);
        }
    }

}

