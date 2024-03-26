package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Element_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01(){
        // Tìm và trả về 1 element
        WebElement element = driver.findElement(By.xpath(""));

        // Tương tác với element:
        element.click();
        element.clear();
        element.getText();
        element.sendKeys("");

        // Lấy ra value của thuộc tính (Attribute) trong thẻ HTML
        // tagname[@attribute_name = 'value']
        element.getAttribute("");

        // Lấy ra tagname của element trong HTML
        element.getTagName();

        //Lấy giá trị của thuộc tính Css của GUI: font/size/color/location/position/...
        //Ví dụ thuộc tính background-color có value là màu #13231 => Lấy ra được #13231
        element.getCssValue("background-color");

        //Lấy ra vị trí element so với web (bên ngoài)
        element.getLocation();

        //Lấy ra kích thước của element (bên trong)
        element.getSize();

        //Kết hợp cả Location + Size
        element.getRect();

        // Verify 1 checkbox/ radio button/ default dropdown đã chọn chưa
        Assert.assertTrue(element.isSelected());

        Assert.assertTrue(element.isDisplayed());

        Assert.assertTrue(element.isEnabled());

        //Element nằm trong thẻ form (đăng nhập, đăng kí,..)
        //Tương ứng với hành vi Enter của end-user
        element.submit();

        //Chụp hình bị lỗi khi testcase fail, lưu dưới dạng file/bytes/base64
        element.getScreenshotAs(OutputType.FILE); // Lưu thành dạng hình ảnh
        element.getScreenshotAs(OutputType.BYTES);
        element.getScreenshotAs(OutputType.BASE64); // Lưu hình dưới dạng text


    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
