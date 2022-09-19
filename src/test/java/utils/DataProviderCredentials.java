package utils;

import base.Base;
import org.testng.annotations.DataProvider;

public class DataProviderCredentials extends Base {
    private final static String DEVICE_NAME = "Test Device";
    private final static String LABEL = "Test";
    private final static String DESCRIPTION = "This is test device";
    private final static String CUSTOMER = "Customer A";
    private final static String PROFILE_NAME = "Test Profile";
    private final static String ROLE = "Air Quality Sensors";
    private final static String DASHBOARD = "Air Quality Sensor (For Mobile App)";
    private final static String QUEUE = "HighPriority";

    @DataProvider
    public static Object[][] deviceDetails() {
        return new Object[][]{
                {DEVICE_NAME + getRandomNumber(), LABEL, DESCRIPTION, CUSTOMER}};
    }

    @DataProvider
    public static Object[][] deviceProfileDetails() {
        return new Object[][]{
                {PROFILE_NAME + getRandomNumber(), ROLE, DASHBOARD, QUEUE}};
    }
}