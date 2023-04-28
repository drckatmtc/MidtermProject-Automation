package ObjectsRepo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utils.CommonFucntions;
import Utils.ExcelReader;

public class TopicsPage {
	WebDriver driver;
	ExtentTest test;

	public TopicsPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	By topicsBy = By.xpath("//a[normalize-space()='Topics of Interest']");

	By addNowBy = By.xpath("//button[normalize-space()='Add Now']");

	By saveBy = By.xpath("//button[normalize-space()='Save and Continue']");

	public void addInterest() throws Exception {

		WebElement topicsEle = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(topicsBy));
		topicsEle.click();

		WebElement addNowEle = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(addNowBy));
		addNowEle.click();

		Thread.sleep(2000);

		List<String> topics = new ArrayList<String>();
		topics = ExcelReader.readAllBySheet("Topics");

		for (String topic : topics) {
			System.out.println(topic);

			WebElement topicEle = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
					ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='" + topic + "']")));
			topicEle.click();
		}

		driver.findElement(By.xpath("//button[normalize-space()='Save and Continue']")).click();

		Thread.sleep(3000);

		test.log(LogStatus.INFO, test.addScreenCapture(CommonFucntions.captureScreenShot()));

		WebElement liElements = driver.findElement(By.xpath("//ul[@class='toi-list']"));
		List<WebElement> ListItems = liElements.findElements(By.tagName("li"));
		for (WebElement e : ListItems) {
			System.out.println("List Item Text : " + e.getText());
			if (topics.contains(e.getText())) {
				test.log(LogStatus.PASS, "Topic is selected", e.getText());
			} else {
				test.log(LogStatus.FAIL, "Topic is not selected", e.getText());
			}
		}

	}
}
