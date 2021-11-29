import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;


import java.util.ArrayList;

public class Example {

    protected WebDriver driver;
    public static String webUrl = "https://ssilistre.github.io/seleniumtraning/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openToGoogle() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait bekleme = new WebDriverWait(driver,20);
        driver.get(webUrl);
        //mail
        bekleme.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"email\"]")));
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("deneme@gmail.com");
        //ulkelistesi.selectByIndex(1);

        //adres
        bekleme.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"address\"]")));
        driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("1234 Main Street, 4321 Main Avenue");

        //ülke
        Select ulkelistesi = new Select(driver.findElement(By.xpath("//*[@id=\"country\"]")));
        ulkelistesi.selectByVisibleText("United States");

        //state
        Select statelistesi = new Select(driver.findElement(By.xpath("//*[@id=\"state\"]")));
        statelistesi.selectByVisibleText("California");

        //zip kodu
        bekleme.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"zip\"]")));
        driver.findElement(By.xpath("//*[@id=\"zip\"]")).sendKeys("123456");

        //labels
        WebElement scrollDown = driver.findElement(By.xpath("//label[@for='same-address']"));
        js.executeScript("arguments[0].scrollIntoView();", scrollDown);
        scrollDown.click();
        driver.findElement(By.xpath("//label[@for='save-info']")).click();

        //payment
        //name on card
        bekleme.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cc-name\"]")));
        driver.findElement(By.xpath("//*[@id=\"cc-name\"]")).sendKeys("Deneme deneme");

        //credit card number
        bekleme.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cc-number\"]")));
        driver.findElement(By.xpath("//*[@id=\"cc-number\"]")).sendKeys("123456789123456789");

        //expiration
        bekleme.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cc-expiration\"]")));
        driver.findElement(By.xpath("//*[@id=\"cc-expiration\"]")).sendKeys("11/24");

        //cvv
        bekleme.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cc-cvv\"]")));
        driver.findElement(By.xpath("//*[@id=\"cc-cvv\"]")).sendKeys("123");

        //button click
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg btn-block']")).click();

        //pop ekranı
        Alert alert = driver.switchTo().alert();
        alert.accept();

        //tekrar butona tıkla
        WebElement scrolltekrar = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg btn-block']"));
        js.executeScript("arguments[0].scrollIntoView();", scrolltekrar);
        scrolltekrar.click();
        driver.switchTo().alert().accept();

        //respond check
        String expectedmessage="Your payment has been successful!";
        String webpagemessage = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();

        String denemeWebPageMsg = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        if(expectedmessage.equalsIgnoreCase(denemeWebPageMsg)){
            System.out.println("İşleminiz tamamlanmıştır!");
        }
        else{
            System.out.println("Yanlış mesaj!");
        }

        /*driver.switchTo().alert().accept();
        driver.switchTo().alert().getText();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));*/
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}