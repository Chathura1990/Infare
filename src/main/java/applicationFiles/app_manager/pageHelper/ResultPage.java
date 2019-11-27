package applicationFiles.app_manager.pageHelper;

import applicationFiles.app_manager.modelData.ResultsData;
import applicationFiles.app_manager.selectorHelper.SelectorService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static applicationFiles.app_manager.ApplicationManager.driver;
import static applicationFiles.framework.globalParameters.GlobalParameters.PAGE_LOAD_TIMEOUT;
import static applicationFiles.framework.globalParameters.GlobalParameters.SLEEP_SEC_4000;
import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.*;

public class ResultPage extends SelectorService {

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    private By intro = xpath("//span[@class='headingintro']");
    private By originIntro = xpath("//span[@class='originairportcode']");
    private By destIntro = xpath("//span[@class='destinationairportcode']");
    private By departureDate = id("input_returnDate_1");

    private By recorrectDate = id("calDepartLabelCont");
    private By DateTableFrom = xpath("//tbody[@class='dl-datepicker-tbody-0']");
    private By doneButton = xpath("//button[@class='donebutton']");

    private By submitButton = id("btnSubmit");

    public ResultsData getResultsText(){
        ResultsData resultsData = new ResultsData();
        resultsData.setHeadingIntro(getText(intro));
        resultsData.setOriginAirportCode(getText(originIntro));
        resultsData.setDestinationAirportCode(getText(destIntro));
        return resultsData;
    }

    public void clickOnDatePickerResultPage() throws InterruptedException {
        sleep(4000);
        visibilityOfElementLocatedBylocator(departureDate,PAGE_LOAD_TIMEOUT).click();
    }

    public void clickSubmitButton() {
        visibilityOfElementLocatedBylocator(submitButton,PAGE_LOAD_TIMEOUT).click();
    }
}
