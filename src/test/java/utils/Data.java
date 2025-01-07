package utils;

import java.io.FileReader;
import java.io.IOException;

public class Data {

    public static String getUrl() {
        try {
            java.util.Properties properties = new java.util.Properties();
            properties.load(new FileReader("src/test/resources/config.properties"));
            return properties.getProperty("url");
        } catch (IOException e) {
            throw new RuntimeException("Error al leer la URL del archivo config.properties", e);
        }
    }
}
