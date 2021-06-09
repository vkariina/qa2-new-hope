package pageobject.pages.DelfiPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

import java.util.List;

public class CommentsPage {
    private final By COMMENTS = By.className("type-cnt");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc baseFunc;

    public CommentsPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public int getCommentsCount() {
        LOGGER.info("Getting Registered + Anonymous all comments count summary");
        List<WebElement> articleComments = baseFunc.findElements(COMMENTS);
        int allCommentsCount = 0;

        for (WebElement comments : articleComments) {
            int parsedCommentsCount;
            String commentsCountToParse = baseFunc.getText(comments);
            parsedCommentsCount = Integer.parseInt(commentsCountToParse.substring(1, commentsCountToParse.length() - 1));
            allCommentsCount += parsedCommentsCount;
        }

        return allCommentsCount;
    }
}
