package customers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AddCustomers {

	WebDriver driver;

	@Before
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://techfios.com/billing/?ng=login/after/dashboard");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void LoginAndAddCustomer() throws InterruptedException {
Random rn = new Random();
int n = rn.nextInt(200);
		WebElement UserNameElement = driver.findElement(By.xpath("//input[@id ='username']"));
		WebElement PasswordElement = driver.findElement(By.xpath("//input[@id ='password']"));

		UserNameElement.sendKeys("demo@techfios.com");
		PasswordElement.sendKeys("abc123" + Keys.ENTER);

		// validating if we are on the right page after login

		boolean titlePageVerify = driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]")).isDisplayed();
		Assert.assertTrue("Title Page Not Displayed", titlePageVerify);

		// Adding new customer
		WebElement customerElement = driver.findElement(By.xpath("//span[contains(text(),'Customers')]"));
		customerElement.click();

		WebElement addCustomerElement = driver.findElement(By.xpath("//a[contains(text(),'Add Customer')]"));
		addCustomerElement.click();

		// verifying that we are on the new deposit page
		WebElement addCustomerVerify = driver.findElement(By.xpath("//h5[contains(text(),'Add Contact')]"));
		boolean addCustomerVerifyStatus = addCustomerVerify.isDisplayed();
		Assert.assertTrue("Not On the Add Customer Page!!!", addCustomerVerifyStatus);

		// Add Contact
		WebElement FullNameElement = driver.findElement(By.xpath("//input[@id='account']"));
		FullNameElement.sendKeys("Hadley_Davis_"+n);

		WebElement companyElement = driver.findElement(By.xpath("//select[@id='cid']"));
		companyElement.click();
		// Using select class to choose an option
		Select sel = new Select(companyElement);
		 sel.selectByVisibleText("Techfios");
		//companyElement.sendKeys("Selenium Company");

		WebElement emailElement = driver.findElement(By.xpath("//input[@id='email']"));
		emailElement.sendKeys("Hadley_Davis"+n+"@test.com");

		WebElement phoneElement = driver.findElement(By.xpath("//input[@id='phone']"));
		phoneElement.sendKeys("777-"+n+"-7848");

		WebElement addressElement = driver.findElement(By.xpath("//input[@id='address']"));
		addressElement.sendKeys("82,plano,tx-75454");

		WebElement cityElement = driver.findElement(By.xpath("//input[@id='city']"));
		cityElement.sendKeys("PLano");

		WebElement stateElement = driver.findElement(By.xpath("//input[@id='state']"));
		stateElement.sendKeys("Texas");

		WebElement postalCodeElement = driver.findElement(By.xpath("//input[@id='zip']"));
		postalCodeElement.sendKeys("75454");

		WebElement countryElement = driver.findElement(By.xpath("//select[@id='country']"));
		Select select = new Select(countryElement);
		select.selectByVisibleText("United States");

		WebElement tagsElement = driver.findElement(By.xpath("//select[@id='tags']"));
		Select sel2 = new Select(tagsElement);
		sel2.selectByVisibleText("My tags");

		WebElement currencyElement = driver.findElement(By.xpath("//select[@id='currency']"));
		Select sel3 = new Select(currencyElement);
		sel3.selectByIndex(0);

		WebElement groupselectElement = driver.findElement(By.xpath("//select[@id='group']"));
		Select sel4 = new Select(groupselectElement);
		sel4.selectByVisibleText("September_QA_2021");

		WebElement welcomeEmailElement = driver.findElement(By.xpath("//label[text()='Yes']"));
		welcomeEmailElement.click(); // deselects the selected option(yes to no)

		WebElement saveElement = driver.findElement(By.xpath("//button[@id='submit']"));
		saveElement.click();

		// Check after save, its on contacts page
		String ContactpageElement = driver.findElement(By.xpath("//h2[contains(text(),' Contacts ')]")).getText();
		Assert.assertEquals("Not on Contact Page", "Contacts", ContactpageElement);
		Thread.sleep(6000);

		// Checking the added customer under list of customer
		WebElement customerElement1 = driver.findElement(By.xpath("//span[contains(text(),'Customers')]"));
		customerElement1.click();
		
		WebElement ListCustomerElement = driver.findElement(By.xpath("//a[text()='List Customers']"));
		ListCustomerElement.click();

		WebElement CustomerCreateCheckElement1 = driver.findElement(By.xpath("//td[normalize-space()='Hadley_Davis_"+n+"']/parent::tr/td[7]"));
		CustomerCreateCheckElement1.click();
		Thread.sleep(4000);

		driver.navigate().back();
		
		// option 2
		WebElement SearchElement = driver.findElement(By.xpath("	//input[@id='foo_filter']"));
		SearchElement.sendKeys("Hadley_Davis_"+n);
		SearchElement.click();
		Thread.sleep(4000);

	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
