package framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.utils.VkJsonPathUtil;

import java.nio.file.Path;
import java.util.Map;

public class DataManager {

    private static final Logger log = LoggerFactory.getLogger(DataManager.class);
    private static Map<String, String> data;
    private static String dataFile = "src/main/resources/data.json";

    public static String getValue(String key) {
        log.info("Get test data by key: " + key);
        if (data == null || data.isEmpty()) {
            data = VkJsonPathUtil.getMap(Path.of(dataFile).toFile(), "");
        }
        return data.get(key);
    }

    public static String getDataFile() {
        return dataFile;
    }

    public static void setDataFile(String dataFile) {
        DataManager.dataFile = dataFile;
        data.clear();
    }
}
