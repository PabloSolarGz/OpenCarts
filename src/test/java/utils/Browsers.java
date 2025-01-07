package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Browsers {
    private static WebDriver driver;
    private static String chromeDriverPath;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/test/resources/config.properties"));
            chromeDriverPath = System.getProperty("os.name").toLowerCase().contains("mac") ?
                    properties.getProperty("chromedriver.mac") : properties.getProperty("chromedriver.win");
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized"); // Abrir navegador maximizado
            options.addArguments("--disable-notifications"); // Desactivar notificaciones del navegador
            options.addArguments("--disable-popup-blocking"); // Desactivar bloqueador de pop-ups
            options.addArguments("--disable-web-security"); // Desactivar restricciones de seguridad del navegador
            options.addArguments("--disable-dev-shm-usage"); // Optimizar uso de memoria compartida
            options.addArguments("--no-sandbox"); // Evitar restricciones de sandboxing
            options.addArguments("--ignore-certificate-errors"); // Ignorar errores de certificados
            options.addArguments("--remote-allow-origins=*"); // Evitar restricciones remotas
            options.addArguments("--allow-file-access-from-files"); // Evitar restricciones remotas
            options.addArguments("--disable-websockets");
            // options.addArguments("--incognito"); // Habilitar modo incógnito (opcional)
            // options.addArguments("--headless=new"); // Ejecutar en modo headless (opcional para CI)
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
