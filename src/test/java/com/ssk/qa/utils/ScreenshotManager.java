package com.ssk.qa.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotManager {

	public static String captureScreenshot(WebDriver driver, String testName) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
		String filePath = screenshotDir + testName + "_" + timeStamp + ".png";

		try {
			Files.createDirectories(new File(screenshotDir).toPath());
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(src.toPath(), new File(filePath).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath;
	}

}
