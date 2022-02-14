package framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.utils.VkJsonPathUtil;

import java.nio.file.Path;
import java.util.Map;

public class ConfigManager{

    private static final Logger log = LoggerFactory.getLogger(ConfigManager.class);
    private static Map<String, String> config;
    private static String dataFile = "src/main/resources/config.json";

    public static String getValue(String key) {
        log.info("Get test data by key: " + key);
        if (config == null || config.isEmpty()) {
            config = JsonPathUtil.getMap(Path.of(dataFile).toFile(), "");
        }
        return config.get(key);
    }

    public static String getDataFile() {
        return dataFile;
    }

    public static void setDataFile(String dataFile) {
        ConfigManager.dataFile = dataFile;
        config.clear();
    }
}
