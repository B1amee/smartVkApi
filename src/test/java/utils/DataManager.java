package utils;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class DataManager {

    private static String filePath = "./src/test/resources/data.properties";

    public static String getProperties(String key) {
        LoggerUtil.info(DataManager.class, "Get properties from \"" + filePath + "\" and key is \"" + key + "\"");
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(Path.of(filePath).toFile())) {
            properties.load(reader);
        } catch (IOException e) {
            LoggerUtil.error(DataManager.class, "Data file not found", e);
        }
        String value = null;
        for (Object obj : properties.keySet()) {
            if (obj.equals(key)) {
                value = ((String) properties.get(obj));
            }
        }
        return value;
    }

    public static void setFilePath(String filePath) {
        DataManager.filePath = filePath;
    }

}
