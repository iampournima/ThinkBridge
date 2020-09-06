package jabatalks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

public class CJabaTalks {

	WebDriver driver;
	ArrayList<String> arrstrWebCountry = new ArrayList<String>();

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.get("http://jt-dev.azurewebsites.net/#/SignUp");

	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

	@Test(priority = 0)
	public void accessdropdown() {
		ArrayList<String> arrstrCountry = new ArrayList<String>();
		arrstrCountry.add("English");
		arrstrCountry.add("Dutch");

		driver.findElement(By.xpath("//div[@id='language']")).click();
		List<WebElement> arrCountryDropdown = driver.findElements(By.xpath("//li[@id='ui-select-choices-1']/div/a/div"));

		for (int i = 0; i < arrCountryDropdown.size(); i++) {
			arrstrWebCountry.add(arrCountryDropdown.get(i).getText());
		}
		if (arrstrWebCountry.equals(arrstrCountry)) {
			System.out.println("Validated Language dropdown");
		}
	}

	
	  @Test( priority=1 ) 
		public void verifyEmail() {
			driver.findElement(By.xpath("//div[@id='language']")).click();
			driver.findElement(By.id("name")).sendKeys("Pournima");
			driver.findElement(By.name("orgName")).sendKeys("Xento");
			driver.findElement(By.id("singUpEmail")).sendKeys("iampournimashegar@gmail.com");

			WebElement elmCheckbox = driver.findElement(By.xpath("//form[@name='signUpForm']/fieldset/div/label/span"));
			if (false == elmCheckbox.isSelected()) {
				elmCheckbox.click();
			}

			driver.findElement(By.xpath("//button[@class='btn btn-custom btn-block ng-binding']")).submit();

			WebElement strMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-custom']/span"));
			String strSuccessMessage = "A welcome email has been sent. Please check your email.";

			if (strMessage.isDisplayed()) {
				Assert.assertEquals(strSuccessMessage, strMessage.getText());
			}
		}
	 

}
