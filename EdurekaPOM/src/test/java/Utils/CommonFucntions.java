package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class CommonFucntions {
	static WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	static ConfigFileReader cfr = new ConfigFileReader();

	@SuppressWarnings("deprecation")
	public static WebDriver launchBrowser() {
		String browserName = cfr.getSpecificUrlProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			return driver;
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			return driver;
		}
		return driver;
	}

	public static ExtentTest generateExtentReport() {
		String dateNameER = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		report = new ExtentReports(dateNameER + "/ExtentReport.html");
		test = report.startTest("ExtentDemo");
		return test;
	}

	public static void closeExtentReport() {
		report.endTest(test);
		report.flush();
	}

	public static String captureScreenShot() throws IOException {
		String dateNameSS = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/TestScreenshots/" + "Screenshot " + dateNameSS + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
