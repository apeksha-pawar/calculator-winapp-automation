package org.test.maven.calculator_winApp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;

public class BasicCalculatorValidations {
	private static WindowsDriver driver = null;

	@BeforeClass
	public void testInit() throws MalformedURLException {
		try {
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
			capability.setCapability("deviceName", "ULTP_797");
			driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capability);
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test (priority = 1)
	public void addition() {
		try {
			driver.findElementByName("One").click();
			driver.findElementByName("Plus").click();
			driver.findElementByName("Two").click();
			driver.findElementByName("Equals").click();
			String result = driver.findElement(By.xpath("//*[contains(@AutomationId,'CalculatorResult')]")).getText();
			System.out.println("Addition result is " + result.replace("Display is", ""));
			Assert.assertEquals(result.replace("Display is ", ""), "3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test (priority = 2)
	public void squareOfANumber() {
		try {
			driver.findElementByName("Clear").click();
			driver.findElementByName("Five").click();
			driver.findElement(By.xpath("//*[contains(@AutomationId,'xpower2Button')]")).click();
			driver.findElementByName("Equals").click();
			String squareResult = driver.findElement(By.xpath("//*[contains(@AutomationId,'CalculatorResult')]"))
					.getText();
			System.out.println("Square result is " + squareResult.replace("Display is ", ""));
			Assert.assertEquals(squareResult.replace("Display is ", ""), "25");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test (priority = 3)
	public void inchToCm() {
		try {
			driver.findElementByName("Open Navigation").click();
			driver.findElementByName("Length Converter").click();
			driver.findElementByName("One").click();
			driver.findElementByName("One").click();
			driver.findElementByName("Zero").click();
			String convertedResult = driver.findElement(By.xpath("//*[contains(@AutomationId,'Value2')]"))
					.getText();
			System.out.println("Inch To CM result is " + convertedResult.replace("Converts into ", ""));
			Assert.assertEquals(convertedResult.replace("Converts into ", ""), "‪279.4‬ Centimeters");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
