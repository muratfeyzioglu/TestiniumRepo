package Utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileandEnv {

    private static final Map<String, String> FILE_AND_ENV = new HashMap<>();

    public static Map<String, String> loadFileAndEnv() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/inputs/config.properties")) {
            properties.load(fis);
            FILE_AND_ENV.put("BaseUrl", properties.getProperty("BaseUrl"));
            FILE_AND_ENV.put("key", properties.getProperty("key"));
            FILE_AND_ENV.put("token", properties.getProperty("token"));
        } catch (Exception e) {
            System.err.println("Error occurred while loading file and environment: " + e.getMessage());
        }
        return FILE_AND_ENV;
    }
}
