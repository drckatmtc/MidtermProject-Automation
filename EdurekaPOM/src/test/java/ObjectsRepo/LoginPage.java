package ObjectsRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utils.CommonFucntions;
import Utils.ExcelReader;

public class LoginPage {
	WebDriver driver;
	ExtentTest test;

	public LoginPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	By logInButtonBy = By.xpath("//span[@class='login_click login-vd giTrackElementHeader ']");

	By emailBy = By.xpath("//input[@id='si_popup_email']");

	By passwordBy = By.xpath("//input[@id='si_popup_passwd']");

	By accountBy = By.xpath("//span[@class='webinar-profile-name']");

	public void loginToEdureka() throws Exception {

		String emailStr = ExcelReader.readByColumnName("Credential", "UserName", 1);
		String passwordStr = ExcelReader.readByColumnName("Credential", "Password", 1);

		driver.findElement(logInButtonBy).click();
		test.log(LogStatus.INFO, "Click Log In Button");

		driver.findElement(emailBy).clear();
		driver.findElement(passwordBy).clear();

		Thread.sleep(2000);

		driver.findElement(emailBy).sendKeys(emailStr);
		driver.findElement(passwordBy).sendKeys(passwordStr + Keys.ENTER);

		Thread.sleep(2000);

		String accountStr = driver.findElement(accountBy).getText().trim();
		System.out.println(accountStr);
		if (accountStr.equalsIgnoreCase("Edureka")) {
			test.log(LogStatus.PASS, "Login Successfull", "Edureka");
			test.log(LogStatus.PASS, test.addScreenCapture(CommonFucntions.captureScreenShot()));
		} else {
			test.log(LogStatus.FAIL, "Login failed", "Edureka");
			test.log(LogStatus.FAIL, test.addScreenCapture(CommonFucntions.captureScreenShot()));
		}
	}
}
