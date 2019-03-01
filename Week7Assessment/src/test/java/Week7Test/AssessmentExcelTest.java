package Week7Test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import Asssessment.Constants;
import Asssessment.LoginPage;
import Asssessment.NewUserPage;


@RunWith(Parameterized.class)
public class AssessmentExcelTest {
	
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
//		driver.quit();
	}
	
	@Parameters
	public static Collection<Object[]> data() throws IOException
	{
		FileInputStream File = new FileInputStream(Constants.FILE_PATH);
		XSSFWorkbook workbook = new XSSFWorkbook(File);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows() - 1][6];
		
		for(int row = 1; row < sheet.getPhysicalNumberOfRows(); row++) {
			
			ob[row-1][0] = sheet.getRow(row).getCell(0).getStringCellValue();
			ob[row-1][1] = sheet.getRow(row).getCell(1).getStringCellValue();
			ob[row-1][2] = sheet.getRow(row).getCell(2).getStringCellValue();
			ob[row-1][3] = sheet.getRow(row).getCell(3).getStringCellValue();
			ob[row-1][4] = sheet.getRow(row).getCell(4).getStringCellValue();
			ob[row-1][5] = row;
		}
		
		workbook.close();
		
		return Arrays.asList(ob);
	}
	
	private String username;
	private String fullName;
	private String password;
	private String confPass;
	private String emailAdd;
	private int row;
	
	public AssessmentExcelTest(String username, String fullName, String password, String confPass, String emailAdd,
			int row) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.confPass = confPass;
		this.emailAdd = emailAdd;
		this.row = row;
	}
	
	
	@Test
	public void newUser() throws IOException {
		
//		driver.get(Constants.JENKINSMAIN);
		driver.get(Constants.JENKINS_LOGIN);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("admin", "admin");
		
		WebElement manageJen = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[4]/a[2]"));
		manageJen.click();
		
		WebElement manageUser = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[16]/a"));
		manageUser.click();
		
		WebElement addUser = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]/a[2]"));
		addUser.click();
		
		NewUserPage newUserPage = PageFactory.initElements(driver, NewUserPage.class);
		
		newUserPage.addUser(username, password, confPass, fullName, emailAdd);
		
		FileInputStream file = new FileInputStream(Constants.FILE_PATH);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow currRow = sheet.getRow(row);
		XSSFCell cell;
		
		try {
			WebElement userTable = driver.findElement(By.xpath("//*[@id=\"people\"]"));
			assertEquals("not added", true, userTable.getText().contains(username));
			cell = currRow.getCell(5);
			if(cell == null) {
				cell = currRow.createCell(5);
			}
			cell.setCellValue("T");
		}catch(AssertionError e) {
			cell = currRow.getCell(5);
			if(cell == null) {
				cell = currRow.createCell(5);
			}
			cell.setCellValue("F");
		}
	
	FileOutputStream fileOut = new FileOutputStream(Constants.FILE_PATH);
	
	workbook.write(fileOut);
	fileOut.flush();
	fileOut.close();
	
	workbook.close();
	file.close();
		
		
	}

}
