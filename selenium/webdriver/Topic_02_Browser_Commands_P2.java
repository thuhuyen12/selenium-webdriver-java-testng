package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Browser_Commands_P2 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01_Verify_Url(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

    }

    @Test
    public void TC_02_Verify_Title(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

    }

    @Test
    public void TC_03_Navigate_function(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

    }

    @Test
    public void TC_04_Get_Page_Source_Code(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[@title='My Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
