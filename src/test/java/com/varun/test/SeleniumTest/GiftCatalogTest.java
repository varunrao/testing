package com.varun.test.SeleniumTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@Ignore
public class GiftCatalogTest {

	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://shop.qa.stjude.org/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testGCRegularEcardTransaction() throws Exception {
		driver.get(baseUrl + "/GiftCatalog/shop.do?cID=10189");
		Thread.sleep(100);
		if (isElementPresent(By.linkText("Sign out"))) {
			driver.findElement(By.linkText("Sign out")).click();
			driver.get(baseUrl + "/GiftCatalog/shop.do?cID=10189");
		}

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.linkText("Cards you email")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.linkText("Cards you email")).click();
		driver.findElement(By.cssSelector("div.grid > #product13574 > div"))
				.click();
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By
						.cssSelector("img[alt=\"Add Recipients\"]")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.cssSelector("img[alt=\"Add Recipients\"]"))
				.click();
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.name("Submit")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		try {
			assertTrue(isElementPresent(By.name("titleRec")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(isElementPresent(By.name("firstNameRec")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(isElementPresent(By.name("lastNameRec")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// ERROR: Caught exception [Error: locator strategy either id or name
		// must be specified explicitly.]
		new Select(
				driver.findElement(By
						.name("ATTRIBUTE__productvariationdto__12027__tributeDonationAmount__NONE__NONE__Picklist")))
				.selectByVisibleText("$30.00");
		driver.findElement(By.name("firstNameRec")).clear();
		driver.findElement(By.name("firstNameRec")).sendKeys("varun");
		driver.findElement(By.name("lastNameRec")).clear();
		driver.findElement(By.name("lastNameRec")).sendKeys("Rao");
		driver.findElement(By.name("emailRec")).clear();
		driver.findElement(By.name("emailRec"))
				.sendKeys("varun.rao@stjude.org");
		driver.findElement(By.id("addRecipientButton")).click();
		driver.findElement(By.cssSelector("div.tributeRecipientName")).click();
		// Warning: verifyTextPresent may require manual changes
		try {
			assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
					.matches("^[\\s\\S]*varun Rao [\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("msgTA")).clear();
		driver.findElement(By.id("msgTA")).sendKeys("varun");
		driver.findElement(
				By.name("ATTRIBUTE__productvariationdto__12027__senderName__NONE__NONE__TextShort"))
				.clear();
		driver.findElement(
				By.name("ATTRIBUTE__productvariationdto__12027__senderName__NONE__NONE__TextShort"))
				.sendKeys("varun");
		driver.findElement(
				By.name("ATTRIBUTE__productvariationdto__12027__email__NONE__NONE__TextShort"))
				.clear();
		driver.findElement(
				By.name("ATTRIBUTE__productvariationdto__12027__email__NONE__NONE__TextShort"))
				.sendKeys("varun.rao@stjude.org");
		driver.findElement(By.name("Submit")).click();
		driver.findElement(By.name("addToCartSubmit")).click();

		driver.findElement(By.cssSelector("img[alt=\"Checkout Now Button\"]"))
				.click();
		driver.findElement(By.cssSelector("img[alt=\"Guest Checkout Button\"]"))
				.click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("varun.rao@stjude.org");
		driver.findElement(By.name("firstName")).clear();
		driver.findElement(By.name("firstName")).sendKeys("varun");
		driver.findElement(By.name("lastName")).clear();
		driver.findElement(By.name("lastName")).sendKeys("rao");
		driver.findElement(By.name("street1")).clear();
		driver.findElement(By.name("street1")).sendKeys("7020");
		driver.findElement(By.name("street2")).clear();
		driver.findElement(By.name("street2")).sendKeys("occidental rd");
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys("plano");
		new Select(driver.findElement(By.name("stateProvince")))
				.selectByVisibleText("Texas");
		driver.findElement(By.name("zipPostalCode")).clear();
		driver.findElement(By.name("zipPostalCode")).sendKeys("75056");
		driver.findElement(By.name("phoneNumber")).clear();
		driver.findElement(By.name("phoneNumber")).sendKeys("111-111-1111");
		driver.findElement(By.cssSelector("img[alt=\"Submit Button\"]"))
				.click();

		driver.findElement(By.id("label")).clear();
		driver.findElement(By.id("label")).sendKeys("varun rao");
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.name("cardNumber")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.name("cardNumber")).clear();
		driver.findElement(By.name("cardNumber")).sendKeys("4111111111111111");
		driver.findElement(By.name("cardCvv2")).clear();
		driver.findElement(By.name("cardCvv2")).sendKeys("123");
		driver.findElement(By.name("Submit")).click();
		driver.findElement(By.cssSelector("img[alt=\"Submit Button\"]"))
				.click();
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
