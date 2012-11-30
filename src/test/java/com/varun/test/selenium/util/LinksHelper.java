package com.varun.test.selenium.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinksHelper {

	public void testAllLinks(WebDriver myDriver) {

		/* Extract all links from the webpage using selenium webdriver */
		List<WebElement> all_links_webpage = myDriver.findElements(By
				.tagName("a"));

		/* Print total no of links on the webpage */
		System.out
				.println("Print total no of links on the webpage---------------------------------------------");
		System.out.println(all_links_webpage.size());

		/* Print text of all links */
		System.out
				.println("Print text of all links------------------------------------------------------------");
		for (int i = 0; i < all_links_webpage.size(); i++) {
			System.out.println(all_links_webpage.get(i).getText());
		}

		/* Print Links */
		System.out
				.println("Print Links------------------------------------------------------------------------");
		for (int i = 0; i < all_links_webpage.size(); i++) {
			System.out.println(all_links_webpage.get(i).getAttribute("href"));
		}
	}
}
