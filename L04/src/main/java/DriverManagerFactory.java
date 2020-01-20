public class DriverManagerFactory {

    public static DriverManager getManager(BrowserDriver browserDriver) {

        DriverManager driverManager = null;

        //TODO add firefox and edge driver manager
        switch (browserDriver) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
//                driverManager = new FirefoxDriverManager();
                break;
            case EDGE:
//                driverManager = new EdgeDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;

    }
}