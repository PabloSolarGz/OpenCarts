package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ReportManager;
import utils.Screenshot;

public class Hooks {
    @Before(order = 0)
    public void initialize() {
        Screenshot.initializeExecutionFolder();
        String reportPath = Screenshot.getExecutionFolder();
        ReportManager.createInstance(reportPath);
    }

    @Before(order = 1)
    public void setup() {
        ReportManager.setTest(
                ReportManager.getExtent().createTest("Iniciar sesión de manera satisfactoria"));
    }

    @After
    public void tearDown() {
        ReportManager.flushReport();
    }
}
