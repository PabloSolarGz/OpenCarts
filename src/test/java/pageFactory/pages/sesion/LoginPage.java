package pageFactory.pages.sesion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PageFunctions;
import utils.Screenshot;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/form/input")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"column-right\"]/div/a[13]")
    private WebElement logoutButton;

    public void ingresarUsuario(String usuario) {
        PageFunctions.setText(emailInput, usuario);
        Screenshot.takeScreenshot(driver, "IngresarUsuario");
    }

    public void ingresarContrasena(String contrasena) {
        PageFunctions.setText(passwordInput, contrasena);
        Screenshot.takeScreenshot(driver, "IngresarContrasena");
    }

    public void clickLogin() {
        PageFunctions.click(loginButton);
        Screenshot.takeScreenshot(driver, "ClickLogin");
    }

    public void clickLogout() {
        PageFunctions.click(logoutButton);
        Screenshot.takeScreenshot(driver, "ClickLogout");
    }

    public boolean validarMyAccount() {
        Screenshot.takeScreenshot(driver, "ValidarMyAccount");
        return driver.getTitle().contains("My Account");
    }
}
