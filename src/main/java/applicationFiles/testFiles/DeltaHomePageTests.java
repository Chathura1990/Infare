package applicationFiles.testFiles;

import applicationFiles.app_manager.modelData.LocationData;
import applicationFiles.app_manager.modelData.ResultsData;
import applicationFiles.app_manager.testBase.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static applicationFiles.app_manager.ApplicationManager.*;
import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.*;

public class DeltaHomePageTests extends TestBase {

    private By resultContainer = xpath("//div[@class='col-12 flightcardContainer ng-star-inserted']");

    @Priority(1)
    @Test(priority = 1)
    public void secondLevelNavigationImageTest() {
        log.info("");
        reportLog("***** Check short-term parking cost when time is incorrect *****");
        boolean availability = app.getDeltaHomePage().hoverToButtonAndCheckImageAvailability();
        assertTrue(availability);
    }

    @Priority(2)
    @Test(priority = 2)
    public void oneWayFlightSearchWithResultsTest() throws InterruptedException {
        log.info("");
        reportLog("***** One way flight search with results *****");
        String Origin = "NYC"; //Departure location New York
        String Description1 = "New York City Area Airports, NY";
        String Destination = "LGW"; //Arrival Location Gatwick
        String Description2 = "London-Gatwick, United Kingdom";
        int OneWay = 1; //1 is the value for one ways trip type element
        int OnePassenger = 0; //0 is the value for '1 Passenger' element

        app.getDeltaHomePage().searchOneWayFlight(new LocationData()
                .setLocationFrom(Origin)
                .setLocationTo(Destination)
                .setLocationDesc1(Description1)
                .setLocationDesc2(Description2),OneWay,OnePassenger);
        int resultContSize = driver.findElements(resultContainer).size();
        if(resultContSize>0) {
            for (int i = 1; resultContSize >= 0; i++) {
                app.getResultPage().clickOnDatePickerResultPage();
                app.getDeltaHomePage().selectADateIfThereIsNoResults(i);
                app.getResultPage().clickSubmitButton();
            }
        }
        ResultsData resultsDataForAssert = app.getResultPage().getResultsText();

        assertEquals(resultsDataForAssert.getHeadingIntro().replaceAll("\\s",""),"OneWayflight");
        assertEquals(resultsDataForAssert.getOriginAirportCode(),Origin);
        assertEquals(resultsDataForAssert.getDestinationAirportCode(),Destination);
    }

    /**
     * Task 3 also same as task 2. But need to do some little changes.
     */

    /**
     * Task 4 is time consuming task. I'm sorry I really don't have that much time to write test case for this hence
     * I have many projects works to do in the current work place.
     */
}
