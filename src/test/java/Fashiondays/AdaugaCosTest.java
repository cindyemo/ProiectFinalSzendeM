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

public class AdaugaCosTest {

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
    public void adaugaCosTest (){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html//a[@id='accept-cookie-policy']")));

        WebElement allowCookies =driver.findElement(By.xpath("/html//a[@id='accept-cookie-policy']"));
        allowCookies.click();
        WebElement butonCautare = driver.findElement(By.xpath("//a[@id='mobile-search']/span[@class='text-placeholder']"));
        butonCautare.click();
        WebElement activareCautare = driver.findElement(By.xpath("/html//input[@id='search-input']"));
        activareCautare.sendKeys("S49797Z4-LSC");

        WebElement apasaButon = driver.findElement(By.xpath("//button[@id='search-submit']/span[@class='icon-fdux_search']"));
        apasaButon.click();
        WebElement adaugaCos = driver.findElement(By.xpath("//ul[@id='products-listing-list']/li//span[@class='campaign-photo']/img[2]"));
        adaugaCos.click();
        WebElement butonAdauga = driver.findElement(By.xpath("//button[@id='buy-box']/span[.='Adauga in cos']"));
        butonAdauga.click();
// MERGI IN COS
        WebElement mergiCos = driver.findElement(By.id("customer-basket"));
        mergiCos.click();

        WebElement succesAlertCos = driver.findElement(By.xpath("/html//div[@id='group-list-availability_id_3']//div[@class='cart-product-description']/a[@href='/p/rucsac-cu-model-grafic-fete-lc-waikiki-p7354423-7/']/div[@class='product-name']"));
        String astepmesajcos = "Rucsac cu model grafic";
        String mesajactualcos = succesAlertCos.getText();
        Assert.assertTrue(mesajactualcos.contains(astepmesajcos));





    }

    /*@AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public void wait(int milliseconds) {
        try {
            driver.wait(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }*/

}
