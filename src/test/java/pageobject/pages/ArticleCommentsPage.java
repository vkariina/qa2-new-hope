package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ArticleCommentsPage {
    private final By COMMENTS = By.className("type-cnt");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

}
