package SeleniumTraning;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class App {

    protected WebDriver driver;
    public static String loginUrl = "http://localhost:8383/DemoLogin/index.html";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void correctLogin() {
        driver.get(loginUrl);
        driver.findElement(By.id("kullaniciAdi")).sendKeys("admin");
        driver.findElement(By.id("parola")).sendKeys("123456");
        driver.findElement(By.id("btnGirisYap")).click();
        Assert.assertEquals(driver.getTitle(), "Home Page");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}