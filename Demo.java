package Demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo {
	
	WebDriver driver;

	@BeforeClass
	public void setUp()
	{
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Selenium softwares\\chromedriver.exe");
		
		driver= new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	@Test
	public void demo() {
		
		 driver.get("https://developer.salesforce.com/docs/component-library/documentation/en/48.0/lwc");
		
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 //driver.findElement(By.linkText("Component Reference")).click();
		 //component();
	//}
		 
		//public void component(){ 
		 JavascriptExecutor js=(JavascriptExecutor) driver;
		 
		 Actions actions = new Actions(driver);
		// WebElement comp=driver.findElement(By.xpath("//div[@class='bottom']//span[@title='Component Reference']"));
		 WebElement comp=driver.findElement(By.xpath("//div[@class='bottom']//span[@title='Component Reference']"));
		 js.executeScript("arguments[0].click();", comp);
		 //actions.moveToElement(comp).click().build().perform();
		 WebElement quickfind= driver.findElement(By.xpath("//div[@class='slds-col slds-grow-none']//input"));
		 actions.click(quickfind).sendKeys("datatable").build().perform();
		 driver.findElement(By.xpath("//div[@class='lwc-section slds-m-bottom_medium slds-p-left_medium slds-p-bottom_small']//child::div[2]//span[@class='slds-tree__item-label slds-truncate']")).click();
		 WebElement se= driver.findElement(By.xpath("//button[@name='example']"));
		 js.executeScript("arguments[0].scrollIntoView();", se);
		 //Select select = new Select(se);
		 //select.selectByVisibleText("Datatable from Inline Edit");
		 
		 List<WebElement> opt = driver.findElements(By.xpath("//button[@name='example']"));
	      //for( int j = 0; j< opt.size();j++){
	         for(WebElement drop:opt){
	         if( drop.equals("Datatable from Inline Edit"))
	         {
	            drop.click();
	            //break;
	         }
	         }
	      //} 
	      String value = "Datatable from Inline Edit";
	      WebElement dBox1= (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='example']")));
	      actions.moveToElement(dBox1);
	      actions.click();
	      actions.perform();
	      actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(se, "Datatable from Inline Edit");
	      //actions.keyDown(se, Keys.DOWN).keyDown(Keys.valueOf(value));
	      actions.click().build();
	      actions.perform();

	}

}
