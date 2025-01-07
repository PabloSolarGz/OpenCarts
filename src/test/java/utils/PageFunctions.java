package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageFunctions {

    public static void initElements(WebDriver driver, Object page) {
        PageFactory.initElements(driver, page);
    }

    public static void click(WebElement element) {
        try {
            WebDriver driver = Browsers.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Error al hacer clic en el elemento: " + e.getMessage(), e);
        }
    }

    public static void setText(WebElement element, String text) {
        if (element.isDisplayed() && element.isEnabled()) {
            element.clear();
            element.sendKeys(text);
        } else {
            throw new RuntimeException("Elemento no está visible o habilitado: " + element);
        }
    }
}
