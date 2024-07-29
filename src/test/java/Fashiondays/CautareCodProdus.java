package Fashiondays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class CautareCodProdus {

    WebDriver driver;

    @Parameters({"browserParam"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional("edge") String browser) {
        //open page
        String url = "https://www.fashiondays.ro";
        //driver = new ChromeDriver();

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new EdgeDriver();
        }

        driver.get(url);
        driver.manage().window().maximize();
    }


@Test
public void cautareCodProdusTest(){
    Wait<WebDriver>wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html//a[@id='accept-cookie-policy']")));

    WebElement allowCookies =driver.findElement(By.xpath("/html//a[@id='accept-cookie-policy']"));
    allowCookies.click();

    //Cautare dupa cod
    WebElement butonCautare = driver.findElement(By.xpath("//a[@id='mobile-search']/span[@class='text-placeholder']"));
    butonCautare.click();
    WebElement activareCautare = driver.findElement(By.xpath("/html//input[@id='search-input']"));
    activareCautare.sendKeys("BQ3498");

    WebElement apasaButon = driver.findElement(By.xpath("//button[@id='search-submit']/span[@class='icon-fdux_search']"));
    apasaButon.click();
     //Rezultat cautare 1 produs

    WebElement succesAlert = driver.findElement(By.xpath("//div[@id='productCount']/div[@class='absolute']"));
    String astepmesaj = "1 produs";
    String mesajactual = succesAlert.getText();
    Assert.assertTrue(mesajactual.contains(astepmesaj));
    }


   @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public void wait(int milliseconds) {
        try {
            driver.wait(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
