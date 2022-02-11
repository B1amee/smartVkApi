package framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.api.VkJsonPathUtil;

import java.nio.file.Path;
import java.util.Map;

public class CredentialsManager {
    private static final Logger log = LoggerFactory.getLogger(CredentialsManager.class);
    private static Map<String, String> creds;
    private static String dataFile = "src/main/resources/creds.json";

    public static String getValue(String key) {
        log.info("Get test data by key: " + key);
        if (creds == null) {
            creds = VkJsonPathUtil.getMap(Path.of(dataFile).toFile(), "");
        }
        return creds.get(key);
    }

    public static String getDataFile() {
        return dataFile;
    }

    public static void setDataFile(String dataFile) {
        CredentialsManager.dataFile = dataFile;
        creds = null;
    }
}
