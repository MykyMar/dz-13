import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
    public static Properties getProperties(String pathToProperties) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(pathToProperties)) {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading properties file: " + pathToProperties, ex);
        }
        return properties;
    }
}

