package add_account;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddingAccount {

	WebDriver driver;

	@Before
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://techfios.com/billing/?ng=admin/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void LoginAndAddAccount() throws InterruptedException {

		// Using CSS selector-id
		// login
		driver.findElement(By.cssSelector("input#username")).sendKeys("demo@techfios.com");
		// using CSS  selector- class
		driver.findElement(By.cssSelector("input.form-control[id='password']")).sendKeys("abc123");
		driver.findElement(By.cssSelector("button.btn[name='login'][type='submit']")).click();

		// Add Account
		// using relative xpath
		driver.findElement(By.xpath("//i[@class='fa fa-university']")).click();
		driver.findElement(By.xpath("//a[text()='New Account']")).click();
		// Account Title
		driver.findElement(By.xpath("//input[@id='account'][@name='account']")).sendKeys("SepQA_Account_002");
		// description
		driver.findElement(By.xpath("//input[@type='text'][@name='description']")).sendKeys("This is a Saving Account");
		// initial balance
		driver.findElement(By.xpath("//input[@type='text'][@name='balance'][@id='balance']")).sendKeys("1000");
		// account Number
		driver.findElement(By.xpath("//input[@class='form-control' and @id ='account_number']"))
				.sendKeys("12345678901234");
		// contact person
		driver.findElement(By.xpath("//input[@name='contact_person' and @id='contact_person']"))
				.sendKeys("Subhashini Balaji");
		// phone number
		driver.findElement(By.xpath("//input[@type='text' and @id='contact_phone']")).sendKeys("+1 123-444-5656");
		// submit

		driver.findElement(By.xpath("//button[@class='btn btn-primary'][@type='submit'] ")).click();
		
	
		
		Thread.sleep(3000);

	}

	@After
	public void teardown() {
		driver.close();
		driver.quit();
	}
}
