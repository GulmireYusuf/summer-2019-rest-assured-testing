package utilities;

public class DriverUtilityEnum {

    public static String getDriver(Browser browser){
        //create a new Webdriver based on given browser type
        switch (browser){
            case CHROME :
                return "chrome";
            case CHROME_HEADLESS:
                return "chrome_headless";
            case FIREFOX:
                return "firefox";
        }

        return "";

    }
}
