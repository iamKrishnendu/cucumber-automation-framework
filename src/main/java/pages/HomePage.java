package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.SeleniumUtils;

/**
 * <p>
 *    Login page for reference only
 * </p>
 */
public class HomePage extends BasePage {

    @FindBy(css = "[id='welcome']")
    private WebElement welcomeTitle;


    private final static String dashboardEndpoint = "/index.php/dashboard";
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitForPageLoad() {
        SeleniumUtils.waitForPageLoad(driver);
    }

    @Override
    public Boolean isPageLoaded() {
        return SeleniumUtils.elementDisplayed(driver,
                SeleniumUtils.waitForElementToBeVisible(driver,welcomeTitle));
    }

    public Boolean checkHomepageContainsDashboardEndpoint() {
        return driver.getCurrentUrl().contains(dashboardEndpoint) ? true : false;

    }
}
