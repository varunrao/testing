package com.varun.test.SeleniumTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Ignore
public class AppTest {
	private WebDriver driver;
	private ExcelReader excelReader = new ExcelReader(
			"C:\\Users\\Varun\\Documents\\Testing\\Selenium\\Junit Integeration\\TestData.xlsx");

	public WebDriver getDriver() throws InterruptedException {
		Thread.sleep(2000);
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://people.cis.ksu.edu/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		System.out.println(" Read from Excel ... "
				+ excelReader.getCellData("Ages", "Name", 2));
		getDriver().get(baseUrl + "/~varunrao/");
		getDriver().findElement(By.linkText("CIS764")).click();
		getDriver().findElement(By.linkText("Activity Log")).click();
		// Warning: verifyTextPresent may require manual changes
		try {
			assertTrue(getDriver()
					.findElement(By.cssSelector("BODY"))
					.getText()
					.matches(
							"^[\\s\\S]*Had the LOG page ready for the course[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		File scrFile = ((TakesScreenshot) getDriver())
				.getScreenshotAs(OutputType.FILE);
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
