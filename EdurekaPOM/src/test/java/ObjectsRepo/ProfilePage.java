package ObjectsRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utils.CommonFucntions;

public class ProfilePage {
	WebDriver driver;
	ExtentTest test;

	public ProfilePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	By profileBy = By.xpath("//span[@class='webinar-profile-name']");

	By myprofileBy = By.xpath("//a[@data-button-name='My Profile']");

	public void navigateToProfile() throws Exception {

		driver.findElement(profileBy).click();

		driver.findElement(myprofileBy).click();

		test.log(LogStatus.INFO, "Click profile");

		String title = driver.getTitle();
		System.out.println(title);
		if (title.contains("My Profile")) {
			test.log(LogStatus.PASS, "Navigated to profile successful", "Edureka");
			test.log(LogStatus.PASS, test.addScreenCapture(CommonFucntions.captureScreenShot()));
		} else {
			test.log(LogStatus.FAIL, "Navigated to profile failed", "Edureka");
			test.log(LogStatus.FAIL, test.addScreenCapture(CommonFucntions.captureScreenShot()));
		}

	}
}
