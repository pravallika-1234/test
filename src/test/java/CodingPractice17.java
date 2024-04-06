import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CodingPractice17 {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\user\\Desktop\\selenium\\chromedriver-win64 (4)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaregform.ccbp.tech/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testRegisterWithEmptyInputs() {
        WebElement submitButton = driver.findElement(By.className("submit-button"));
        submitButton.click();

        WebElement firstNameError = driver.findElement(By.cssSelector("form.form-container > div:first-of-type + p"));
        String actualText = firstNameError.getText();
        Assert.assertEquals(actualText, "Required", "Error text with empty input field do not match");

        WebElement lastNameError = driver.findElement(By.cssSelector("form.form-container > div:last-of-type + p"));
        actualText = lastNameError.getText();
        Assert.assertEquals(actualText, "Required", "Error text with empty input field do not match");
    }

    @Test(priority = 2)
    public void testRegisterWithEmptyFirstname() {
        WebElement lastNameEle = driver.findElement(By.id("lastName"));
        lastNameEle.sendKeys("Doe");

        WebElement submitButton = driver.findElement(By.className("submit-button"));
        submitButton.click();

        WebElement firstNameError = driver.findElement(By.cssSelector("form.form-container > div:first-of-type + p"));
        String actualText = firstNameError.getText();
        Assert.assertEquals(actualText, "Required", "Error text at first name do not match");
    }

    @Test(priority = 3)
    public void testRegisterWithEmptyLastname() {
        WebElement firstNameEle = driver.findElement(By.id("firstName"));
        firstNameEle.sendKeys("John");

        WebElement submitButton = driver.findElement(By.className("submit-button"));
        submitButton.click();

        WebElement lastNameError = driver.findElement(By.cssSelector("form.form-container > div:last-of-type + p"));
        String actualText = lastNameError.getText();
        Assert.assertEquals(actualText, "Required", "Error text at last name do not match");

    }

    @Test(priority = 4)
    public void testRegisterWithValidCreds() {
        WebElement firstNameEle = driver.findElement(By.id("firstName"));
        firstNameEle.sendKeys("John");

        WebElement lastNameEle = driver.findElement(By.id("lastName"));
        lastNameEle.sendKeys("Doe");

        WebElement submitButton = driver.findElement(By.className("submit-button"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success-image")));

        WebElement successMessage = driver.findElement(By.cssSelector(".success-image + p"));
        String actualText = successMessage.getText();
        Assert.assertEquals(actualText, "Submitted Successfully", "Success message do not match");
    }
}
