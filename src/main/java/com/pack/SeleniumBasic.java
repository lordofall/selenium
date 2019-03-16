package com.pack;

import java.time.Instant;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// to use firefox driver
//	import org.openqa.selenium.firefox.FirefoxDriver;
// to use chrome driver
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * add selenium-java jar in pom.xml
 * download chromedriver.exe from https://sites.google.com/a/chromium.org/chromedriver/downloads depending on your chrome version.
 * @author LordOfAll
 *
 */
public class SeleniumBasic {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LordOfAll\\workspace\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			String baseUrl = "https://www.nseindia.com/live_market/dynaContent/live_analysis/top_gainers_losers.htm";
			
			// launch chrome 
			driver.get(baseUrl);
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Losers tab
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"tab8\"]")));
				driver.findElement(By.xpath("//*[@id=\"tab8\"]")).click();
				// Symbol of top looser
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id=\"topLosers\"]/tbody/tr[2]/td[1]/a")));
				WebElement topLooserCompany = driver
						.findElement(By.xpath("//*[@id=\"topLosers\"]/tbody/tr[2]/td[1]/a"));
				String topLooser = topLooserCompany.getText();
				// corresponding percentage change
				wait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"topLosers\"]/tbody/tr[2]/td[3]")));
				WebElement topLooserCompanyChange = driver
						.findElement(By.xpath("//*[@id=\"topLosers\"]/tbody/tr[2]/td[3]"));
				float percentageChange = Float.parseFloat(topLooserCompanyChange.getText());
				// how to refresh the page
				
				String message = "Top looser company: " + topLooser + "\nPercentage change: " + percentageChange;
				System.out.println(message);
				if (percentageChange < -8) {
					JOptionPane.showMessageDialog(null, message, Instant.now().toString(),
							JOptionPane.INFORMATION_MESSAGE);
					break;
				} 
				driver.navigate().refresh();
			}
		} finally {
			driver.close();
			System.out.println("all good");
			
		}
	}

}
