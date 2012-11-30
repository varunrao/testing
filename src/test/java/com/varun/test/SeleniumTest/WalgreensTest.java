package com.varun.test.SeleniumTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WalgreensTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.walgreens.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.cssSelector("a[title=\"Sign In\"] > strong"))
				.click();
		assertTrue(isElementPresent(By.name("Register")));
		driver.findElement(By.name("Register")).click();
		driver.findElement(By.name("submit")).click();
		assertEquals(
				"Please enter your first name",
				driver.findElement(
						By.cssSelector("#firstnameErrorText > p.nopad"))
						.getText());
		driver.findElement(By.id("firstname")).clear();
		driver.findElement(By.id("firstname")).sendKeys("dsadsa");
		driver.findElement(By.name("submit")).click();
		// Warning: assertTextNotPresent may require manual changes
		assertFalse(driver
				.findElement(By.cssSelector("BODY"))
				.getText()
				.matches(
						"^[\\s\\S]*css=#firstnameErrorText > p\\.nopad[\\s\\S]*$"));
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
