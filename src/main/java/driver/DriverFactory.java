package driver;

import constants.Constants;
import constants.SharedEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver driver;
    public final static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    // Base Constructor
    public DriverFactory(){}

    /**
     * Initialize browser based upon environment and browser name</br>
     * @param browserName Name of the browser
     * @param environment Environment
     */
    public WebDriver initializeBrowser(String browserName, String environment){
        switch (browserName){
            case"CHROME":
                WebDriverManager.chromedriver().setup();
                webDriver.set(new ChromeDriver());
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver.set(new FirefoxDriver());
                break;
        }
        webDriver.get().manage().window().maximize();
        webDriver.get().get(selectURL(environment));
        webDriver.get().manage().timeouts().pageLoadTimeout(Long.parseLong(Constants.PAGE_TIMEOUT),TimeUnit.SECONDS);
        return getDriver();
    }

    /**
     * Selects web application URL based upon the environment</br>
     * @param environment Environment
     * @return Application URL
     */
    public String selectURL(String environment){
        if(environment.equalsIgnoreCase("DEMO")) return SharedEnum.ENVIRONMENT.DEMO.getURL();
        if(environment.equalsIgnoreCase("QA")) return SharedEnum.ENVIRONMENT.QA.toString();
        if(environment.equalsIgnoreCase("DEV")) return SharedEnum.ENVIRONMENT.DEV.toString();
        return null;
    }

    /**
     * Get static WebDriver instance
     * @return WebDriver
     */
    public static WebDriver getDriver(){
        return webDriver.get();
    }

}
