package pages;



import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


/**
 * Abstract base page which stores page references<br/>
 */
public abstract class BasePage extends DriverFactory implements PageObject{

    public WebDriver driver;

    // Constructor
    public BasePage(WebDriver driver){
        this.driver = getDriver();
        PageFactory.initElements(driver,this);
    }

    /**
     * <p>
     *     Wait to load Login page
     *    Determine Login page is loaded
     * </p>
     * @return LoginPage instance if loaded otherwise null
     */
    public static LoginPage navigateToLoginPage(){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.waitForPageLoad();
        if(loginPage.isPageLoaded()) return loginPage;
        return null;
    }

    /**
     * <p>
     *     Wait to load Home page
     *    Determine Home page is loaded
     * </p>
     * @return HomePage instance if loaded otherwise null
     */
    public static HomePage navigateToHomePage(){
        HomePage homePage = new HomePage(getDriver());
        homePage.waitForPageLoad();
        if(homePage.isPageLoaded()) return homePage;
        return null;
    }
}
