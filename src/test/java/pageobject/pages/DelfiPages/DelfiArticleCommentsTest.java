package pageobject.pages.DelfiPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pageobject.BaseFunc;


public class DelfiArticleCommentsTest {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final int ARTICLE_ID = 4;

    private BaseFunc baseFunc;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count for http://delfi.lv on home/article/comments pages");

        baseFunc = new BaseFunc();
        baseFunc.openPage("http://delfi.lv");

        //--------------------HOME PAGE----------------------------------
        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCokkies();

        String homePageTitle = homePage.getTitle(ARTICLE_ID);
        int homePageCommentsCount = homePage.getCommentsCount(ARTICLE_ID);

        ArticlePage articlePage = homePage.openArticle(ARTICLE_ID);

        //-------------------ARTICLE PAGE-----------------------------------
        String articlePageTitle = articlePage.getTitle();
        int articlePageCommentsCount = articlePage.getCommentsCount();

        LOGGER.info("Checking equality of homepage title and article title");
        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        LOGGER.info("Checking equality of homepage title comments count and article title's comment count");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        LOGGER.info("Title is: " + articlePageTitle + " and comments count is: " + articlePageCommentsCount);

        CommentsPage commentsPage = articlePage.openCommentsPage();


        //--------------------COMMENTS PAGE-------------------------------
        commentsPage.getCommentsCount();

        int commentsPageCommentsCount = commentsPage.getCommentsCount();

        LOGGER.info("Checking equality of homepage title comments count and comments page comment count summary");
        Assertions.assertEquals(homePageCommentsCount, commentsPageCommentsCount, "Wrong comments count!");
    }

    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }
}
