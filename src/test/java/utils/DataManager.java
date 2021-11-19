package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class DataManager {

    private final static ISettingsFile jsonDataFile = new JsonSettingsFile("test_data.json");

    public static String getValue(String key) {
        return jsonDataFile.getValue(key).toString();
    }
}
