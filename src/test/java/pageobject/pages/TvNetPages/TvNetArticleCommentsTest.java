package pageobject.pages.TvNetPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import pageobject.BaseFunc;

public class TvNetArticleCommentsTest {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final int ARTICLE_ID = 4;

    private BaseFunc baseFunc;

    @Test
    public void TitleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count for http://tvnet.lv on home/article/comments pages");

        baseFunc = new BaseFunc();
        baseFunc.openPage("http://tvnet.lv");

        //--------------------HOME PAGE----------------------------------
        pageobject.pages.TvNetPages.HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCokkies();

        String homePageTitle = homePage.getTitle(ARTICLE_ID);

        System.out.println(homePageTitle);
    }
}
