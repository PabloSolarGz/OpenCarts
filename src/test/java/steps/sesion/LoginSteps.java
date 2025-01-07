package steps.sesion;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import pageFactory.pages.sesion.LoginPage;
import utils.Browsers;
import utils.Data;
import utils.ReportManager;
import utils.Screenshot;
import utils.GestorGlobal;
import io.cucumber.java.en.*;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ExtentTest execution;

    @Given("Ingresar al sitio web Opencart")
    public void ingresarSitioWeb() {
        try {
            Screenshot.initializeExecutionFolder();
            ReportManager.createInstance(Screenshot.getExecutionFolder());

            driver = Browsers.getDriver();
            driver.get(Data.getUrl());
            loginPage = new LoginPage(driver);

            GestorGlobal.inicializarVariables();

            String screenshotPath = Screenshot.takeScreenshot(driver, "IngresarSitioWeb");
            execution = ReportManager.getExtent().createTest("Ingresar al sitio web Opencart");
            execution.pass("Se ingresó al sitio web Opencart correctamente.").addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            String screenshotPath = Screenshot.takeScreenshot(driver, "IngresarSitioWebError");
            execution = ReportManager.getExtent().createTest("Error en Ingresar al sitio web Opencart");
            execution.fail("Error al ingresar al sitio web: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
            throw new RuntimeException(e);
        }
    }

    @When("Iniciar sesión con un usuario registrado")
    public void ingresarUsuarioRegistrado() {
        try {
            String usuario = GestorGlobal.getUsuario();
            String contrasena = GestorGlobal.getContrasena();
            loginPage.ingresarUsuario(usuario);
            loginPage.ingresarContrasena(contrasena);
            loginPage.clickLogin();
            String screenshotPath = Screenshot.takeScreenshot(driver, "IniciarSesion");
            execution = ReportManager.getExtent().createTest("Iniciar sesión");
            execution.pass("Inicio de sesión exitoso.").addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            String screenshotPath = Screenshot.takeScreenshot(driver, "IniciarSesionError");
            execution = ReportManager.getExtent().createTest("Error en Iniciar sesión");
            execution.fail("Error al iniciar sesión: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
            throw new RuntimeException(e);
        }
    }

    @When("Validar My Account cuando se inicie sesion de manera satisfactoria")
    public void validarSesionSatisfactoria() {
        try {
            if (!loginPage.validarMyAccount()) {
                throw new AssertionError("El título de la página no contiene 'My Account'.");
            }
            String screenshotPath = Screenshot.takeScreenshot(driver, "ValidarMyAccount");
            execution = ReportManager.getExtent().createTest("Validar My Account");
            execution.pass("Validación de My Account exitosa.").addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            String screenshotPath = Screenshot.takeScreenshot(driver, "ValidarMyAccountError");
            execution = ReportManager.getExtent().createTest("Error en Validar My Account");
            execution.fail("Error al validar My Account: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
            throw new RuntimeException(e);
        }
    }

    @When("Cerrar Sesión Activa")
    public void cerrarSesionActiva() {
        try {
            loginPage.clickLogout();

            String screenshotPath = Screenshot.takeScreenshot(driver, "CerrarSesion");
            execution = ReportManager.getExtent().createTest("Cerrar Sesión");
            execution.pass("Sesión cerrada con éxito.").addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            String screenshotPath = Screenshot.takeScreenshot(driver, "CerrarSesionError");
            execution = ReportManager.getExtent().createTest("Error en Cerrar Sesión");
            execution.fail("Error al cerrar sesión: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
            throw new RuntimeException(e);
        }
    }

    @Then("Cerrar el navegador")
    public void cerrarNavegador() {
        try {
            String screenshotPath = Screenshot.takeScreenshot(driver, "CerrarNavegador");
            execution = ReportManager.getExtent().createTest("Cerrar navegador");
            execution.pass("Navegador cerrado con éxito.").addScreenCaptureFromPath(screenshotPath);
            Browsers.closeDriver();
        } catch (Exception e) {
            String screenshotPath = Screenshot.takeScreenshot(driver, "ErrorCerrarNavegador");
            execution = ReportManager.getExtent().createTest("Error en Cerrar navegador");
            execution.fail("Error al cerrar el navegador: " + e.getMessage())
                    .addScreenCaptureFromPath(screenshotPath);
            throw new RuntimeException(e);
        } finally {
            ReportManager.flushReport();
        }
    }
}
