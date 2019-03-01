package Week7Test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Asssessment.Constants;
import Asssessment.LoginPage;
import Asssessment.NewItemPage;

public class Testing {
	
	WebDriver driver;
	
	@Before
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Applications/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@After
	public void teardown() throws InterruptedException
	{
		Thread.sleep(1500);
		driver.quit();
	}
	
	@Test
//	@Ignore
	public void taskOne() throws InterruptedException {
		driver.get(Constants.JENKINS_LOGIN);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("admin", "admin");
		
		WebElement newItem = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/a[2]"));
		newItem.click();
		
		WebElement itemName = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		WebDriverWait itemWait = new WebDriverWait(driver, 10);
		itemWait.until(ExpectedConditions.visibilityOf(itemName));
		itemName.sendKeys("AutomationItem1");
		
		WebElement freestyleProj = driver.findElement(By.xpath("//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[1]"));
		freestyleProj.click();
		Thread.sleep(1500);
		WebElement okBtn = driver.findElement(By.xpath("//*[@id=\"ok-button\"]"));
		
		okBtn.click();
		Thread.sleep(1500);

		WebDriverWait saveWait = new WebDriverWait(driver, 10);
//		WebElement saveBtn = driver.findElement(By.id("yui-gen38-button"));
		WebElement saveBtn = saveWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("yui-gen38-button"))));

		saveBtn.click();		
		
		
//		NewItemPage newItemPage = PageFactory.initElements(driver, NewItemPage.class);
//		String itemText = ;
//		newItemPage.createNewItem("automated item");
		
		WebElement backDashboard = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/a[2]"));
		backDashboard.click();
		
//		WebElement projects = driver.findElement(By.id("projectstatus"));
		WebElement projects = driver.findElement(By.xpath("//*[@id=\"projectstatus\"]"));
		
//		System.out.println(projects.getText());
		assertEquals("No project with such name", true, projects.getText().contains("AutomationItem1"));
	}
	
	

	

}
