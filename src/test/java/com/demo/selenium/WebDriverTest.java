package com.demo.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;

public class WebDriverTest extends TestCase {
	public void testBaidu(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\yisa\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
        System.out.println("Page title is: " + driver.getTitle());
	}
	
	public void testBaiduSug() {
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\yisa\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();*/
        driver.get("http://www.baidu.com");

        WebElement query = driver.findElement(By.id("kw"));
        query.sendKeys("宝宝");

        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            ArrayList<WebElement> resultsDiv = (ArrayList<WebElement>) driver.findElements(By.className("bdsug"));
            if (resultsDiv.size()>0) {
              break;
            }
        }

        List<WebElement> allSuggestions = driver.findElements(By.xpath(".//*[@id='form']/div/ul"));

        for (WebElement suggestion : allSuggestions) {
            System.out.println(suggestion.getText());
            //You can simulate pressing the arrow keys by using the "Keys" class: 模拟按下向下键
            suggestion.sendKeys(suggestion.getText(), Keys.ARROW_DOWN);
            System.out.println(driver.getTitle());
            break;
        }

       // driver.quit();
	}
	
	public void testToggle() {
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://cpaexam.cicpa.org.cn/");
        WebElement select = driver.findElement(By.xpath("//select"));
       /* List<WebElement> allOptions = select.findElements(By.tagName("option"));
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
        	//option 通过js生成未获取到
        	if (allOptions.size() > 0){
        		for (WebElement option : allOptions) {
    	            System.out.println(String.format("Value is: %s", option.getAttribute("value")));
    	            option.click();
    	        }
        	}
        }*/
       /* Select selector = new Select(select);
        List<WebElement> allOptions = selector.getOptions();
        for (WebElement option : allOptions) {
            System.out.println(String.format("Value is: %s", option.getAttribute("value")));
            option.click();
        }*/
        
        WebDriverWait wait = new WebDriverWait(driver,100);    
        wait.until(new ExpectedCondition<Boolean>() {  
            public Boolean apply(WebDriver webDriver) {  
                System.out.println("Searching ...");  
                WebElement select = webDriver.findElement(By.xpath("//select"));
                return select.findElements(By.tagName("option")).size() != 0;
          }  
        });  
        List<WebElement> allOptions = select.findElements(By.tagName("option"));
    	if (allOptions.size() > 0){
    		for (WebElement option : allOptions) {
	            System.out.println(String.format("Value is: %s", option.getAttribute("value")));
	            option.click();
	        }
    	}
       // driver.quit();
	}
	public void testToggle_dri() {
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\yisa\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();*/
		
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        
        driver.get("file:///C:/Users/yisa/Desktop/test.html");
        WebElement select = driver.findElement(By.xpath("/html/body/form/select"));
        Select selector = new Select(select);
        selector.selectByValue("audi");
        List<WebElement> allOptions = selector.getOptions();
        for (WebElement option : allOptions) {
            System.out.println(String.format("Value is: %s", option.getAttribute("value")));
        }
	}
}
