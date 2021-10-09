package pages;

import exception.CustomException;
import utility.SeleniumUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * <p>
 *    Login page for reference only
 * </p>
 */
public class LoginPage extends BasePage  {

    //=================================Login Page
    @FindBy(id = "txtUsername")
    private WebElement userNameInput;
    @FindBy(id = "txtPassword")
    private WebElement passWordInput;
    @FindBy(id = "btnLogin")
    private WebElement loginButton;
    @FindBy(css = "#forgotPasswordLink>a")
    private WebElement forgotPasswordLink;
    @FindBy(css = "#divLogo>img")
    private WebElement pageLogoImg;
    private final static String loginPageEndpoint = "/auth/login";

    //Initiate Logger
    Logger log = Logger.getLogger(LoginPage.class.getName());

    // Base Constructor
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public void waitForPageLoad() {
        SeleniumUtils.waitForPageLoad(driver);
    }

    @Override
    public Boolean isPageLoaded() {
        return SeleniumUtils.elementDisplayed(driver,
                SeleniumUtils.waitForElementToBeVisible(driver,userNameInput));
    }

    public Boolean pageComponentsAreVisible(){
        boolean isComponentVisible = false;
        isComponentVisible = SeleniumUtils.elementDisplayed(driver, userNameInput) &&
                SeleniumUtils.elementDisplayed(driver, passWordInput);
        return isComponentVisible;
    }
    /**
     * Enter login details and hit on Login
     * @param userName valid Username
     * @param password valid Password
     */
    public void enterLoginDetails(String userName, String password) throws CustomException {
        SeleniumUtils.enterText(userNameInput,userName);
        SeleniumUtils.enterText(passWordInput,password);
        SeleniumUtils.click(loginButton);
    }

}
