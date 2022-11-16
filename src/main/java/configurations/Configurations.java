package configurations;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;


public class Configurations {
    public static final String APP_BASE_URL;
    public static final String API_BASE_URL;
    public static final String USERNAME;
    public static final String PASSWORD;
    private static final String PROPERTIES_FILE_NAME = "applications.properties";
    private static final String CREDENTIALS_PROPERTIES_FILE_NAME = "credentials.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties(PROPERTIES_FILE_NAME);
        loadProperties(CREDENTIALS_PROPERTIES_FILE_NAME);
        APP_BASE_URL = PROPERTIES.getProperty("app.base.url");
        API_BASE_URL = PROPERTIES.getProperty("api.base.url");
        USERNAME = PROPERTIES.getProperty("app.username");
        PASSWORD = PROPERTIES.getProperty("app.password");
    }

    private static void loadProperties(String propFileName) {
        try (InputStream inputStream = Configurations.class.getClassLoader().getResourceAsStream(propFileName)) {
            if (inputStream != null) {
                PROPERTIES.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }
}
