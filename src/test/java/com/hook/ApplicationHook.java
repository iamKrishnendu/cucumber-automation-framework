package com.hook;


import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import scenario.ScenarioFactory;

public class ApplicationHook {

    private WebDriver driver;
    private final String browser = System.getProperty("browserName");
    private final String environment = System.getProperty("environment");

    @Before
    public void setup(Scenario scenario){
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.initializeBrowser(browser,environment);
        ScenarioFactory.getInstance().setScenarioThreadLocal(scenario);

    }

    @After(order = 0)
    public void closeBrowser(){
        driver.close();
        driver.quit();
    }

    @After(order = 1)
    public void attachFailedStepScreenshot(){
        Scenario sc = ScenarioFactory.getInstance().getScenarioThreadLocal();
        if(sc.isFailed()){
            try{
                sc.log("Current page URL is: "+driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                sc.attach(screenshot,"image/png","failedStep");
            }catch (WebDriverException webDriverException){
                System.err.println(webDriverException.getMessage());
            }
        }

        ScenarioFactory.getInstance().closeScenario();
    }
}
