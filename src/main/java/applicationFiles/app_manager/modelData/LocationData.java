package applicationFiles.app_manager.modelData;

public class LocationData {

    private String locationFrom;
    private String locationTo;
    private String locationDesc1;
    private String locationDesc2;

    public LocationData setLocationFrom(String locationFrom){
        this.locationFrom = locationFrom;
        return this;
    }

    public LocationData setLocationTo(String locationTo){
        this.locationTo = locationTo;
        return this;
    }

    public LocationData setLocationDesc1(String locationDesc1){
        this.locationDesc1 = locationDesc1;
        return this;
    }

    public LocationData setLocationDesc2(String locationDesc2){
        this.locationDesc2 = locationDesc2;
        return this;
    }

    public String getLocationFrom(){
        return locationFrom;
    }

    public String getLocationTo(){
        return locationTo;
    }

    public String getLocationDesc1(){
        return locationDesc1;
    }

    public String getLocationDesc2(){
        return locationDesc2;
    }
}
