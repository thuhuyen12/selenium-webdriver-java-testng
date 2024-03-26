package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_02_Browser_Commands {
//  Tương tác với browser: khai báo biến driver - Tương tác với element: dùng biến element
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
//  Khởi tạo browser, nếu ko khởi tạo sẽ gặp lỗi: NullPointerException
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    driver.manage().window().maximize();
    }

    @Test
    public void TC_01(){
        // Mở ra URl bất kì
        driver.get("https://www.facebook.com/");

        // Close: đóng window đang tương tác. Quit: đóng tất cả window
        driver.close();
        driver.quit();

        // Trả về kiểu dữ liệu Web Element nếu được tìm thấy
        // Nếu không tìm thấy: fail tại step này - throw exception: NoSuchElementException
        driver.findElement(By.xpath(""));
        // Nếu muốn tạo biến => phải gán kiểu dữ liệu là Web Element
        WebElement emailAddress = driver.findElement(By.id(""));

        // Trả về list Web Elements
        // Nếu ko tìm thấy thì cũng ko bị fail mà trả về List rỗng ( 0 elements)
        driver.findElements(By.xpath(""));
        //Khai báo biến
        List<WebElement> email = driver.findElements(By.id(""));
        // Lấy element trong list, dùng index
        email.get(2).click();

        //Trả về URL của page hiện tại
        driver.getCurrentUrl();

        //Trả về Source Code HTML của page hiện tại: dùng để verify tương đối (chứa cái gì đó: contains)
        driver.getPageSource();
        String sourceCode = driver.getPageSource(); //khai báo biến
        Assert.assertTrue(sourceCode.contains("trong... sống"));

        //Trả về title của page hiện tại
        driver.getTitle();
        // Kiểm tra title của page => vào tag Console: document.title

        //Lấy ra được ID của tab mà driver đang đứng
        driver.getWindowHandle();
        //Lấy ra ID của tất cả tab/cửa sổ
        driver.getWindowHandles();

        //Cookie/ cache - Framework
        driver.manage().getCookies();
        // Get ra những log ở devtool
//        driver.manage().logs(LogType.BROWSER);
        // Quản lí timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // Chờ cho 1 page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        //Set trước khi dùng với thư viện Javascript Excutor
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        // Mở window full màn hình/ maximize
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();

        // Test responsive của UI (Resolution)
        driver.manage().window().setSize(new Dimension(1920,1080));
        // Sau khi set thì get ra sẽ trả về dữ liệu Dimension đã set
        driver.manage().window().getSize();

        // Set tọa độ cho browser tại 1 vị trí trên màn hình - tương tự cũng có 2 bước set và get
        driver.manage().window().setPosition(new Point(5,6)); // Khi window đc mở lên thì sẽ mở bắt đầu từ tọa độ (5,6) này
        driver.manage().window().getPosition();

        // Điều hướng trang web: navigate
        driver.navigate().back(); //quay lại page trước
        driver.navigate().refresh(); //load lại trang
        driver.navigate().forward(); //chuyển tiếp page sau
        driver.navigate().to("url"); //chuyển tới link nào, thường sử dụng get nhiều hơn


        // Dùng với Alert/ window (tab)/ frame
        driver.switchTo().alert().accept();
        driver.switchTo().alert().sendKeys("");
        // Window, tab
        String windowID =driver.getWindowHandle();
        driver.switchTo().window(windowID);
        // Frame/iframe (bằng index/ ID, name/  element)
        driver.switchTo().frame("");

        // Switch về HTML chứa frame trước đó
        driver.switchTo().defaultContent();
        // Từ frame trong đi ra frame ngoài chứa nó
        driver.switchTo().parentFrame();
    }













    @AfterClass
    public void afterClass(){

    }
}
