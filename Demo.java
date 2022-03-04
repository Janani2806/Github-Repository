package Demo;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Demo {

	WebDriver driver;

	@BeforeClass
	public void SetUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Selenium softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterClass
	public void Close() {
		driver.quit();
	}

	@Test
	public void SiteTrackerDemo() {

		driver.get("https://developer.salesforce.com/docs/component-library/documentation/en/48.0/lwc");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		SelectDataTableComponent();
		DataTablePreview();
	}

	public void SelectDataTableComponent() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		WebElement component = driver
				.findElement(By.xpath("//div[@class='bottom']//span[@title='Component Reference']"));
		js.executeScript("arguments[0].click();", component);
		WebElement quickFind = driver.findElement(By.xpath("//div[@class='slds-col slds-grow-none']//input"));
		actions.click(quickFind).sendKeys("datatable").build().perform();
		driver.findElement(By
				.xpath("//div[@class='lwc-section slds-m-bottom_medium slds-p-left_medium slds-p-bottom_small']//child::div[2]//span[@class='slds-tree__item-label slds-truncate']"))
		.click();
		SelectDataInline(js, actions);
	}

	public void SelectDataInline(JavascriptExecutor js, Actions actions) {
		WebElement dropdownPath = driver.findElement(By.xpath("//button[@name='example']"));
		js.executeScript("arguments[0].scrollIntoView();", dropdownPath);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement dropdownSelect = (new WebDriverWait(driver, 1000))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='example']")));
		actions.moveToElement(dropdownSelect).click().build().perform();
		WebElement selectDataInline = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@id='dropdown-element-386']//span[@class='slds-media__body']//span[@title='Data Table with Inline Edit']")));
		actions.moveToElement(selectDataInline).click().build().perform();
		driver.findElement(By.xpath("//div[@id='example']//button[@class='slds-button slds-button_neutral']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void DataTablePreview() {
		WebElement frameName = driver.findElement(By.xpath("//iframe[@name='preview']"));
		driver.switchTo().frame(frameName);
		List<WebElement> rowsNumber = driver.findElements(By.xpath(
				"//*[@class='slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/div/div/table[1]/tbody/tr[1]/td[1]"));
		int rowCount = rowsNumber.size();
		System.out.println("No of rows in this table : " + rowCount);

	}

}
