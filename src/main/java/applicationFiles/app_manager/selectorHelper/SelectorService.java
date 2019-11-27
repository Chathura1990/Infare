package applicationFiles.app_manager.selectorHelper;

import applicationFiles.app_manager.ApplicationManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static applicationFiles.app_manager.ApplicationManager.reportLog;
import static applicationFiles.framework.globalParameters.GlobalParameters.*;
import static org.testng.Assert.*;

public class SelectorService {

    private WebDriver driver;
    private WebDriverWait wait;

    public SelectorService(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * this method makes WebDriver poll the DOM for a certain amount of time when trying to locate an element.
     *
     * @param units time in seconds
     */
    private void implicit_Wait(int units) {
        driver.manage().timeouts().implicitlyWait(units, TimeUnit.SECONDS);
    }

    /**
     * this method will wait until completely load the page
     *
     * @param units time in seconds
     */
    public void pageLoad_Timeout(int units) {
        driver.manage().timeouts().pageLoadTimeout(units, TimeUnit.SECONDS);
    }

    /**
     * this method will wait until the element to be visible by the locator
     *
     * @param units time in seconds
     */
    protected WebElement visibilityOfElementLocatedBylocator(By locator, int units) { //Visibility Of Element Located By Xpath
        wait = new WebDriverWait(driver, units);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * this method will wait until the element to be clickable
     *
     * @param locator this could be an attribute of an element
     */
    private void waitElementToBeClickable(By locator) {
        wait = new WebDriverWait(driver, SLEEP_SEC_2000);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        reportLog("Wait element to be clickable: (" + locator + ")");
    }

    /**
     * this method will wait until the element to be visible by the locator
     *
     * @param locator this could be an attribute of an element
     * @param text    this field is for
     */
    protected void click(By locator, String text) {
        visibilityOfElementLocatedBylocator(locator, SLEEP_SEC_2000);
        waitElementToBeClickable(locator);
        if (isELementPresent(locator)) {
            driver.findElement(locator).click();
        }
        reportLog("Clicked '" + text + "' using element: (" + locator + ")");
    }

    public String getCurrentDay(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        String todayStr = Integer.toString(todayInt);
        reportLog("Today Str: " + todayStr + "\n");
        return todayStr;
    }

    protected void hoverOnElement(By locator, By locator2, String text){
        WebElement element = driver.findElement(locator);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        WebElement subElement = visibilityOfElementLocatedBylocator(locator2,10);
        reportLog("Wait for the Element "+locator);
        action.moveToElement(subElement);
        if(subElement.isDisplayed()) {
            action.perform();
            reportLog(text + " is appeared");
        }else{
            reportLog("Unable to locate " + text);
        }
    }

    protected void type(By locator, String text) {//Click on field,clear field and enter the text
        String inputFieldText =  visibilityOfElementLocatedBylocator(locator, SLEEP_SEC_2000).getText();
        driver.findElement(locator).click();
        if(inputFieldText != null) {
            driver.findElement(locator).clear();
        }
        driver.findElement(locator).sendKeys(text);
        reportLog("Typed: '" + text + "' in the field: (" + locator + ")");
    }

    public String getText(By locator) {
        implicit_Wait(PAGE_LOAD_TIMEOUT);
        return visibilityOfElementLocatedBylocator(locator, PAGE_LOAD_TIMEOUT).getText();
    }

    protected void selectAnOptionFromDropdown(By locator, int option){
        Select select = new Select(driver.findElement(locator));
        List<WebElement> options = select.getOptions();
        options.get(option).click();
    }

    public String getWebsiteTitle() {
        return driver.getTitle();
    }

    protected boolean isELementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            fail(ex.getMessage());
            return false;
        }
    }

    public boolean checkImageIsAvailableOrNot(By locator1, String text1) {
        WebElement image = visibilityOfElementLocatedBylocator(locator1, PAGE_LOAD_TIMEOUT);
        reportLog("wait for the element (" + text1 + ")");

        Object result = ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && " + "typeof arguments[0].naturalWidth != 'undefined' && " + "arguments[0].naturalWidth > 0", image);

        boolean loaded = false;
        if (result instanceof Boolean) {
            loaded = (Boolean) result;
            reportLog("'" + text1 + "' image is available -->" + " " + loaded);
        }
        return loaded;
    }
}
