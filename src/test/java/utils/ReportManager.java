package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports createInstance(String folderPath) {
        String filePath = folderPath + "/ExecutionReport.html";

        File reportFile = new File(filePath);
        if (!reportFile.getParentFile().exists()) {
            reportFile.getParentFile().mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Reporte de Automatización");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName("Reporte de Ejecución");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Sistema Operativo", System.getProperty("os.name"));
        extent.setSystemInfo("Usuario", System.getProperty("user.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void setTest(ExtentTest testInstance) {
        test = testInstance;
    }

    public static void addScreenshot(String screenshotPath, String description) {
        if (test != null) {
            try {
                File screenshotFile = new File(screenshotPath);
                if (screenshotFile.exists() && screenshotFile.length() > 0) {
                    test.info(description, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else {
                    test.warning("La captura de pantalla no se pudo agregar: archivo no válido.");
                }
            } catch (Exception e) {
                test.warning("No se pudo agregar la captura al reporte: " + e.getMessage());
            }
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
