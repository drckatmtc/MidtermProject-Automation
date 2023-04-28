package ObjectsRepo;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utils.CommonFucntions;

public class LaunchPage {

	WebDriver driver;
	ExtentTest test;

	public LaunchPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void launchEdureka() throws Exception {
		String title = driver.getTitle();

		if (title.contains("Edureka")) {
			test.log(LogStatus.PASS, "Title contains", "Edureka");
			test.log(LogStatus.PASS, test.addScreenCapture(CommonFucntions.captureScreenShot()));
		} else {
			test.log(LogStatus.FAIL, "Title does not contains", "Edureka");
		}

	}
}
