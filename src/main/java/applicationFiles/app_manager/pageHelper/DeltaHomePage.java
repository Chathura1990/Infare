package applicationFiles.app_manager.pageHelper;

import applicationFiles.app_manager.modelData.LocationData;
import applicationFiles.app_manager.selectorHelper.SelectorService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static applicationFiles.framework.globalParameters.GlobalParameters.*;
import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.*;
import static applicationFiles.app_manager.ApplicationManager.*;

public class DeltaHomePage extends SelectorService {

    public DeltaHomePage(WebDriver driver) {
        super(driver);
    }

    //Flight search options
    private By origin = id("fromAirportName");
    private By originSearchInput = id("search_input");
    private By destination = id("toAirportName");
    //Dropdown Fields
    private By tripTypeValue = id("selectTripType-val");
    private By departureDate = id("input_departureDate_1");
    private By DateTableFrom = xpath("//tbody[@class='dl-datepicker-tbody-0']");
    private By passengerSelector = xpath("//span[@aria-owns='passengers-desc']");
    //Buttons
    private By travelInfoButton = xpath("//a[contains(text(),'Travel Info')][1]");
    private By subElementMainCabin = xpath("//a[contains(text(),'Main Cabin')]");
    private By submitButton = id("btn-book-submit");
    private By doneButton = xpath("//button[@class='donebutton']");
    //Images
    private By MainCabinImage = xpath("//img[@src='//content.delta.com/content/www/us/en.damAssetRender.20180411T2106211960400.html/content/dam/delta_homepage_redesign/travelinfo/travel-info_360_mainCabin.jpg']");

    public boolean hoverToButtonAndCheckImageAvailability(){
        hoverOnElement(travelInfoButton,subElementMainCabin,"Sub element [Main cabin]");
        return checkImageIsAvailableOrNot(MainCabinImage,"[Main Cabin]");
    }

    public void enterLocations(LocationData locData){
        click(origin,"[Departure loc]");
        type(originSearchInput,locData.getLocationFrom());
        visibilityOfElementLocatedBylocator(xpath("//span[contains(text(),'"+locData.getLocationDesc1()+"')]"),PAGE_LOAD_TIMEOUT).click();
        click(destination,"[Departure loc]");
        type(originSearchInput,locData.getLocationTo());
        visibilityOfElementLocatedBylocator(xpath("//span[contains(text(),'"+locData.getLocationDesc2()+"')]"),PAGE_LOAD_TIMEOUT).click();
    }

    private void selectTripType(int val){
        click(tripTypeValue," on trip type value");
        click(id("ui-list-selectTripType"+val),"trip type: "+val);
    }

    private void selectTodayDate() throws InterruptedException {
        String today = getCurrentDay();
        clickOnDatePickerHomePage();
        getColumnDetailsAndSelectDate(today);

        try{
            sleep(SLEEP_SEC_4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(doneButton,"[DONE] button");
    }

    private void selectPassengers(int val){
        click(passengerSelector,"passenger dropdown");
        click(id("ui-list-passengers"+val),"passengers: "+val);
    }

    private void clickOnDatePickerHomePage(){
        visibilityOfElementLocatedBylocator(departureDate,PAGE_LOAD_TIMEOUT).click();
    }

    public void selectADateIfThereIsNoResults(int val) throws InterruptedException {
        String today = getCurrentDay();
        if(val <= 31){
        int futureDay = Integer.valueOf(today) + val;
        reportLog("Date is: "+futureDay);
        getColumnDetailsAndSelectDate(String.valueOf(futureDay));
        click(doneButton,"[DONE] button");
        }else{
            reportLog("There are no flights for the date: "+val);
        }
    }

    private void getColumnDetailsAndSelectDate(String date) throws InterruptedException {
        WebElement dateDropdownWidget = driver.findElement(DateTableFrom);
        List<WebElement> columns = dateDropdownWidget.findElements(By.tagName("td"));
        for (WebElement cell : columns) {
            if (cell.getText().equals(date)) {
                cell.click();
                break;
            }
        }
    }

    public void searchOneWayFlight(LocationData locationData,int tripType,int passengerAmt) throws InterruptedException {
        enterLocations(locationData);
        selectTripType(tripType);
        selectTodayDate();
        selectPassengers(passengerAmt);
        clickSubmitButton();
    }

    public void clickSubmitButton(){
        click(submitButton,"[SUBMIT] button");
    }
}
