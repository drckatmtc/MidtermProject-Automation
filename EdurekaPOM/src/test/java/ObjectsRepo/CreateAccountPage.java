package ObjectsRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utils.CommonFucntions;
import Utils.ConfigFileReader;
import Utils.ExcelReader;

public class CreateAccountPage {
	WebDriver driver;
	ExtentTest test;
	ConfigFileReader config = new ConfigFileReader();

	public CreateAccountPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	By signUpBy = By.xpath("//span[@class='signup_click signup-vd giTrackElementHeader ']");

	By emailBy = By.xpath("//input[@id='sg_popup_email']");

	By phoneCodeBy = By.xpath("//div[@class='inputfld captcha_parent_input']//select[@aria-label='prefix']");

	By phoneNumBy = By.xpath("//input[@id='sg_popup_phone_no']");

	By passwordBy = By.xpath("//input[@id='signup_password']");

	By accountBy = By.xpath("//span[@class='webinar-profile-name']");

	By logOutBy = By.xpath("//ul[@class='dropdown-menu user-menu dropdown-menu-right']"
			+ "//a[@class='trackButton'][normalize-space()='Log Out']");

	public void createAccountEdureka() throws Exception {

		String emailStr = ExcelReader.readByColumnName("Credential", "UserName", 1);
		String passwordStr = ExcelReader.readByColumnName("Credential", "Password", 1);

		String phoneNumStr = Long.toString(config.getPhoneNumber());
		String countryStr = config.getCountry("country");

		driver.findElement(signUpBy).click();

		driver.findElement(emailBy).sendKeys(emailStr);

		driver.findElement(phoneCodeBy).click();

		List<WebElement> allOptions = driver.findElements(By.cssSelector("select option"));
		for (int i = 0; i < allOptions.size(); i++) {
			if (allOptions.get(i).getText().contains(countryStr)) {
				allOptions.get(i).click();
				System.out.println("clicked");
				break;
			}
		}

		driver.findElement(phoneNumBy).sendKeys(phoneNumStr + Keys.ENTER);

		WebElement signInEle = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(passwordBy));
		signInEle.sendKeys(passwordStr + Keys.ENTER);

		Thread.sleep(2000);

		String accountStr = driver.findElement(accountBy).getText().trim();
		System.out.println(accountStr);
		if (accountStr.equalsIgnoreCase("Edureka")) {
			test.log(LogStatus.PASS, "Sign-Up Successfull", "Edureka");
			test.log(LogStatus.PASS, test.addScreenCapture(CommonFucntions.captureScreenShot()));
		} else {
			test.log(LogStatus.FAIL, "Sign-Up Failed", "Edureka");
			test.log(LogStatus.FAIL, test.addScreenCapture(CommonFucntions.captureScreenShot()));
		}
		
		test.log(LogStatus.INFO, "Log out", "Edureka");

		driver.findElement(accountBy).click();

		driver.findElement(logOutBy).click(); // logout
	}
}
