package utility;

import constants.Constants;
import exception.CustomException;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

/**
 * Class contains common utility methods of Selenium<br/>
 * Customizable based upon the requirement<br/>
 */
public class SeleniumUtils {

    static Logger logger = Logger.getLogger(SeleniumUtils.class);

    /**
     * Set texts into elements<br/>
     * @param element WebElement
     * @param valueTobeEntered Text to be entered
     * @throws CustomException
     */
    public static void enterText(@NotNull WebElement element, @NotNull String valueTobeEntered) throws CustomException {
        try{
            element.sendKeys(valueTobeEntered);
        }catch (NoSuchElementException | ElementNotInteractableException e){
            logger.error("Unable to enter "+valueTobeEntered+" into: "+element);
            throw new CustomException("Failed to enter "+valueTobeEntered+" due to "+e.getMessage());
        }
    }

    /**
     * Click on element<br/>
     * @param element WebElement
     */
   public static void click(@NotNull WebElement element){
        try{
            element.click();
        }catch (NoSuchElementException | ElementClickInterceptedException e){
            e.printStackTrace();
        }
   }

    /**
     * Check element is displayed<br/>
     * @param driver WebDriver
     * @param elementToBeVisible WebElement
     * @return true if displayed otherwise false
     */
   public static Boolean elementDisplayed(WebDriver driver, @NotNull WebElement elementToBeVisible){
        try{
            return elementToBeVisible.isDisplayed();
        }catch (WebDriverException e){
            logger.error("Failed to display "+elementToBeVisible+" on the page due to "+e.getMessage());
            return false;
        }
   }

    /**
     * Get text from element<br/>
     * @param element WebElement
     * @return Text on the element
     */
   public static String getText(@NotNull WebElement element){
        try{
            return element.getText().trim();
        }catch (WebDriverException e){
            logger.error("Failed to fetch text from "+element+" due to "+e.getMessage());
            return null;
        }
   }

    /**
     * Select value from drop-down<br/>
     * @param element WebElement
     * @param value Value to be selected
     */
   public static void selectByValueFromDropDown(@NotNull WebElement element, @NotNull String value){
        try {
            Select select = new Select(element);
            select.selectByValue(value);
        }catch (WebDriverException e){
            logger.error("Failed to select value from "+element+" due to "+e.getMessage());
        }
   }

    /**
     * Select visible text from the drop-down<br/>
     * @param element WebElement
     * @param visibleText Visible text on the drop-down list
     */
    public static void selectByVisibleTextFromDropDown(@NotNull WebElement element, @NotNull String visibleText){
        try {
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
        }catch (WebDriverException e){
            logger.error("Failed to select value from "+element+" due to "+e.getMessage());
        }
    }

    /**
     * Select index from drop-down<br/>
     * @param element WebElement
     * @param index Value Index
     */
    public static void selectByIndexFromDropDown(@NotNull WebElement element, @NotNull int index){
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
        }catch (WebDriverException e){
            logger.error("Failed to select value from "+element+" due to "+e.getMessage());
        }
    }

    /**
     * Scroll to the element<br/>
     * @param driver WebDriver
     * @param element WebElement
     */
    public static void scrollIntoView(WebDriver driver, @NotNull WebElement element){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",element);
    }

    /**
     * Switch to pop up window<br/>
     * @param driver WebDriver
     */
    public static void switchToPopUpWindow(WebDriver driver) {
        try{
            String window = driver.getWindowHandle();
            driver.switchTo().window(window);
            logger.info("Switch to: "+window);
        }catch (WebDriverException e){
            logger.error("Unable to switch to the pop-up due to "+e.getMessage());
        }
    }

    /**
     * Handle browser alerts<br/>
     * Either accept or dismiss <br/>
     * @param driver WebDriver
     * @param isAlertAccepted Action on alert
     * @throws CustomException
     */
    public static void handleAlert(WebDriver driver, @NotNull boolean isAlertAccepted) throws CustomException {
        Alert alert = new WebDriverWait(driver,Long.parseLong(Constants.PAGE_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
        if(alert == null) throw new CustomException("No such alert found");
        if(isAlertAccepted) alert.accept();
        else alert.dismiss();
    }

    /**
     * Handle multiple browser tabs and switch<br/>
     * @param driver WebDriver
     * @param windowType Type of window, can be Parent or Child
     */
    public static void handleMultipleWindows(WebDriver driver, @NotNull String windowType) {
        Set<String> handler = driver.getWindowHandles();
        Iterator<String> it = handler.iterator();
        String parentWindow = it.next();

        if(windowType.equalsIgnoreCase("Parent")) {
            logger.info("Parent Window ID: "+parentWindow);
           driver.switchTo().window(parentWindow);
        }else if(windowType.equalsIgnoreCase("Child")) {
            String childWindow = it.next();
            logger.info("Chile Window ID: "+childWindow);
            driver.switchTo().window(childWindow);
        }

    }

    /**
     * Take screenshot<br/>
     * @param driver WebDriver
     * @param screenshotName Name of the screenshot
     * @return Screenshot destination path
     */
   public static String takeScreenshot(WebDriver driver, @NotNull String screenshotName) {
        try{
            TakesScreenshot takesScreenshot = ((TakesScreenshot)driver);
            File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(Constants.TARGET_FOLDER_PATH+File.separator+"failures" +File.separator+screenshotName);
            FileUtils.copyDirectory(source,destination);
            return destination.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Wait for page to be loaded<br/>
     * Explicit wait mechanism wih JS script<br/>
     * @param driver WebDriver
     */
    public static void waitForPageLoad(WebDriver driver){
        ExpectedCondition<Boolean> pageLoadCondition = webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(Constants.PAGE_TIMEOUT));
        wait.until(pageLoadCondition);
    }

    /**
     * Wait for element to be visible<br/>
     * Explicit wait mechanism<br/>
     * @param driver WebDriver
     * @param element WebElement
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver,@NotNull WebElement element){
        return new WebDriverWait(driver,Long.parseLong(Constants.PAGE_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for element to be invisible<br/>
     * Explicit wait mechanism<br/>
     * @param driver WebDriver
     */
    public static Boolean waitForElementToBeInvisible(WebDriver driver,@NotNull WebElement element){
        return new WebDriverWait(driver,Long.parseLong(Constants.PAGE_TIMEOUT))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Get file path from `resources` folder
     * @param module Module name (Ex: main or test)
     * @param subFolder Sub folder name
     * @return Folder path
     */
    public static String getResourceFilePath(String module, String subFolder){
        Path resourceDirectory = Paths.get("src",module,"resources",subFolder);
        return resourceDirectory.toFile().getAbsolutePath();
    }

}
