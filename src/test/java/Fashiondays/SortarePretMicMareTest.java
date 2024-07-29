package Fashiondays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SortarePretMicMareTest {

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
    public void sortarePretMicMareTest(){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html//a[@id='accept-cookie-policy']")));

        WebElement allowCookies =driver.findElement(By.xpath("/html//a[@id='accept-cookie-policy']"));
        allowCookies.click();

        //Cautare dupa nume
        WebElement butonCautare = driver.findElement(By.xpath("//a[@id='mobile-search']/span[@class='text-placeholder']"));
        butonCautare.click();
        WebElement activareCautare = driver.findElement(By.xpath("/html//input[@id='search-input']"));
        activareCautare.sendKeys("genti");

        WebElement apasaButon = driver.findElement(By.xpath("//button[@id='search-submit']/span[@class='icon-fdux_search']"));
        apasaButon.click();

        //sortare dupa pret mic
        WebElement sortD= driver.findElement(By.xpath("//div[@id='paginationContainerHeader']/div[@class='filter-sort products-toolbar__btn-container']//button[@role='button']"));
        Select sortS =new Select(sortD);
        sortS.selectByValue("lowest_price");

      /*  List<WebElement> pretEl =driver.findElement(By.id(""));//nu gasesc de unde sa copiex lista pentru xparh, id, css pentru cel mai mic pret
        List<Double> pretProduse = new ArrayList<>();
        for(WebElement pret:pretEl){
            pretProduse.add(Double.parseDouble(pret.getText().substring(1)));
        }
        List<Double>asteptPret = new ArrayList<>(pretProduse);
        Collections.sort(asteptPret);
        Assert.assertEquals(asteptPret, pretProduse);
        System.out.println("Test passed - cautare dupa pret reusita");*/


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
