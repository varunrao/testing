package com.varun.test.SeleniumTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.varun.test.selenium.util.LinksHelper;

@Ignore
public class StJudeSiteTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private LinksHelper linksHelper = new LinksHelper();

	@Before
	public void setUp() throws Exception {
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\Varun\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		baseUrl = "http://www.stjude.org/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		driver.get(baseUrl
				+ "/stjude/v/index.jsp?vgnextoid=f87d4c2a71fca210VgnVCM1000001e0215acRCRD");

		linksHelper.testAllLinks(driver);

		((JavascriptExecutor) driver)
				.executeScript("jQuery('#headerregion2links').mouseover();");
		driver.findElement(
				By.xpath("//a[contains(text(),'A-Z List of Diseases')]"))
				.click();
		assertTrue(isElementPresent(By.linkText("AIDS / HIV Vaccine")));
		driver.findElement(
				By.xpath("//a[contains(text(),'AIDS / HIV Vaccine')]")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [isTextPresent]]
	}

	@After
	public void tearDown() throws Exception {
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
