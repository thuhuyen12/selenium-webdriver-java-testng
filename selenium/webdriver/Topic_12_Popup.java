package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Fixed_Popup_In_DOM() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//div[@id='button-login-dialog']/button[text()='Đăng nhập']")).click();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div>div")).isDisplayed());
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input[id='account-input']"))
                .sendKeys("automationfc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input[id='password-input']"))
                .sendKeys("automationfc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div>div")).isDisplayed());
    }

    @Test
    public void TC_02_Fixed_Popup_Not_In_DOM() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.styles__Root-sc-2hr4xa-0")).isDisplayed());
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());
        driver.findElement(By.cssSelector("button.btn-close")).click();

//        Verify popup không còn hiển thị nữa (Not in DOM) => Lấy số lượng = 0
//        Tức là tìm trên màn hình 0 có element đó nữa => Chú ý dùng "findElements"
        Assert.assertEquals(driver.findElements(By.cssSelector("div.styles__Root-sc-2hr4xa-0")).size(), 0);

    }

    @Test
    public void TC_03_Random_Popup_In_DOM() {
        driver.get("https://vnk.edu.vn/");
        sleepInSecond(5);
        WebElement random_popup = driver.findElement(By.cssSelector("div.thrv_wrapper.thrv-columns"));
        if (random_popup.isDisplayed()){
            driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
        } else {
            driver.findElement(By.cssSelector("button.btn.btn-danger")).click();
        }
    }


    @Test
    public void TC_04_Random_Popup_Not_In_DOM() {
        driver.get("https://dehieu.vn/");
        sleepInSecond(10);
        List<WebElement> random_popup = driver.findElements(By.cssSelector("div.modal-content"));
//        Vì ko có trong DOM nên ko thể check isDisplayed
//        Nếu >0 thì chắc chắn có trong DOM nên thêm isDisplayed được
        if (random_popup.size() > 0 && random_popup.get(0).isDisplayed()){
            driver.findElement(By.cssSelector("button.close")).click();
        } else {
            driver.findElement(By.xpath("//a[text()=' Về VNK Edu']")).click();
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public void sleepInSecond (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
