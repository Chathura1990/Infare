package applicationFiles.app_manager.modelData;

public class ResultsData {

    private String headingIntro;
    private String originAirportCode;
    private String destinationAirportCode;

    public ResultsData setHeadingIntro(String headingIntro) {
        this.headingIntro = headingIntro;
        return this;
    }

    public ResultsData setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
        return this;
    }

    public ResultsData setOriginAirportCode(String originAirportCode) {
        this.originAirportCode = originAirportCode;
        return this;
    }

    public String getOriginAirportCode() {
        return originAirportCode;
    }

    public String getHeadingIntro() {
        return headingIntro;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }


}
