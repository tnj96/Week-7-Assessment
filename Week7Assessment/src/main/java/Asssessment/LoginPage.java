package Asssessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage 
{
	@FindBy(id = "j_username")
	private WebElement username;
	
	@FindBy(xpath = "/html/body/div/div/form/div[2]/input")
	private WebElement password;
	
	@FindBy(xpath = "/html/body/div/div/form/div[3]/input")
	private WebElement submitBtn;
	
	public void login(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		submitBtn.click();
	}

}
