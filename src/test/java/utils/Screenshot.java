package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Screenshot {

    private static final String BASE_FOLDER = "reports/executions/";
    private static String executionFolder;

    public static void initializeExecutionFolder() {
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear());
        String month = now.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        executionFolder = BASE_FOLDER + year + "/" + capitalize(month) + "/" + timestamp;

        File folder = new File(executionFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static String takeScreenshot(WebDriver driver, String stepName) {
        try {
            if (driver == null) {
                throw new IllegalArgumentException("El WebDriver es nulo.");
            }

            if (stepName == null || stepName.isEmpty()) {
                stepName = "UnnamedStep";
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String screenshotPath = executionFolder + "/" + stepName + "_" + timestamp + ".png";

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(srcFile, destFile);

            return new File(screenshotPath).getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la captura de pantalla: " + e.getMessage());
        }
    }
    
    public static String getExecutionFolder() {

        return Paths.get(executionFolder).toAbsolutePath().toString();
    }

    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
