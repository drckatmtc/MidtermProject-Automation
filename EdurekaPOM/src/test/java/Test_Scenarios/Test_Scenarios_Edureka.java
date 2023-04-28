package Test_Scenarios;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectsRepo.CreateAccountPage;
import ObjectsRepo.LaunchPage;
import ObjectsRepo.LoginPage;
import ObjectsRepo.ProfilePage;
import ObjectsRepo.TopicsPage;
import Utils.CommonFucntions;
import Utils.ConfigFileReader;

public class Test_Scenarios_Edureka {
	WebDriver driver;

	ConfigFileReader config = new ConfigFileReader();
	ExtentTest test;

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("***** BEFORE SUITE ******");
		driver = CommonFucntions.launchBrowser();
		test = CommonFucntions.generateExtentReport();
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("****** BEFORE TEST *******");
		String url = config.getSpecificUrlProperty("edurekaurl");
		driver.get(url);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Launch Url", url);
	}

	@Test(priority = 0)
	public void launchEdureka() throws Exception {
		System.out.println("**** Launch Edureka ******");
		LaunchPage launchPage = new LaunchPage(driver, test);
		launchPage.launchEdureka();
	}

	@Test(priority = 1)
	public void signUp() throws Exception {
		System.out.println("***** Create Account *****");
		CreateAccountPage createAccountPage = new CreateAccountPage(driver, test);
		createAccountPage.createAccountEdureka();
	}

	@Test(priority = 2)
	public void logIn() throws Exception {
		System.out.println("******   Log In   ********");
		LoginPage loginPage = new LoginPage(driver, test);
		loginPage.loginToEdureka();
	}

	@Test(priority = 3)
	public void profile() throws Exception {
		System.out.println("******   Profile  ********");
		ProfilePage profilePage = new ProfilePage(driver, test);
		profilePage.navigateToProfile();
	}

	@Test(priority = 4)
	public void interest() throws Exception {
		System.out.println("******  Interest  ********");
		TopicsPage topicsPage = new TopicsPage(driver, test);
		topicsPage.addInterest();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("****** AFTER TEST ********");
		test.log(LogStatus.INFO, "Close the browser", "Chrome");
		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("****** AFTER SUITE ********");
		CommonFucntions.closeExtentReport();
	}
}
