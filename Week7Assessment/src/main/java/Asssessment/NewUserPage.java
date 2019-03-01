package Asssessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewUserPage {
	
	@FindBy(id = "username")
	private WebElement username;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[2]/td[2]/input" )
	private WebElement password;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[3]/td[2]/input")
	private WebElement confirm;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[4]/td[2]/input")
	private WebElement name;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[5]/td[2]/input")
	private WebElement email;
	
	@FindBy(id = "yui-gen1-button")
	private WebElement subBtn;
	
	public void addUser(String user, String pass, String conPass, String fullname, String eAdd) {
		
		username.sendKeys(user); 
		password.sendKeys(pass);
		confirm.sendKeys(conPass);
		name.sendKeys(fullname);
		email.sendKeys(eAdd);
		subBtn.click();	
		
	}

}
