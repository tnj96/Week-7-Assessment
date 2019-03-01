package Asssessment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewItemPage {

	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"tasks\"]/div[1]/a[2]")
	private WebElement newItemBtn;
	
	@FindBy(xpath = "//*[@id=\"name\"]")
	private WebElement itemName;
	
	@FindBy(xpath = "//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[1]")
	private WebElement freestyleProj;
	
	@FindBy(xpath = "//*[@id=\"ok-button\"]")
	private WebElement okBtn;
	
	@FindBy(id = "yui-gen38-button")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//*[@id=\"tasks\"]/div[1]/a[2]")
	private WebElement backDashboard;
	
	public void createNewItem(String newItemName) {
		newItemBtn.click();
		
		WebDriverWait itemWait = new WebDriverWait(driver, 10);
		itemWait.until(ExpectedConditions.visibilityOf(itemName));
		itemName.sendKeys(newItemName);
		
		freestyleProj.click();
		okBtn.submit();
		
		WebDriverWait saveWait = new WebDriverWait(driver, 5);
		saveWait.until(ExpectedConditions.visibilityOf(saveBtn));
		saveBtn.submit();
	}
	
	
}
