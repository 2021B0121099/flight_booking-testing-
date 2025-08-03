package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A utility class to read properties from the config.properties file.
 * This version is compatible with older versions of Java.
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    public static void loadProperties() throws IOException {
        if (properties == null) {
            properties = new Properties();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(CONFIG_FILE_PATH);
                properties.load(fis);
            } finally {
                // Ensure the stream is closed, even if an exception occurs
                if (fis != null) {
                    fis.close();
                }
            }
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            try {
                loadProperties();
            } catch (IOException e) {
                System.err.println("Failed to load config.properties: " + e.getMessage());
                return null;
            }
        }
        return properties.getProperty(key);
    }
}
