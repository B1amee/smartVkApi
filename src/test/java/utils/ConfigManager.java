package utils;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigManager {

    private static String filePath = "./src/test/resources/config.properties";

    public static String getProperties(String key) {

        LoggerUtil.info(ConfigManager.class, "Get properties from \"" + filePath + "\" and key is \"" + key + "\"");
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(Path.of(filePath).toFile())) {
            properties.load(reader);
        } catch (IOException e) {
            LoggerUtil.error(ConfigManager.class, "Config file not found", e);
        }
        return properties.getProperty(key);
    }

    public static void setFilePath(String filePath) {
        ConfigManager.filePath = filePath;
    }

}
