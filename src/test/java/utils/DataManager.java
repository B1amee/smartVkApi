package utils;

import org.testng.log4testng.Logger;

import java.nio.file.Path;
import java.util.Map;

public class DataManager {

    private static final Logger log = Logger.getLogger(DataManager.class);
    private static Map<String, String> testData;
    private static String dataFile = "src/test/resources/test_data.json";

    public static String getValue(String key) {
        log.info("Get test data by key: " + key);
        if (testData == null) {
            testData = JsonPathUtil.getMap(Path.of(dataFile).toFile(), "");
        }
        return testData.get(key);
    }

    public static String getDataFile() {
        return dataFile;
    }

    public static void setDataFile(String dataFile) {
        DataManager.dataFile = dataFile;
        testData = null;

    }
}
