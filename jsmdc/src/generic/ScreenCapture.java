package generic;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;

public class ScreenCapture {
	public static void takeSnapShot(WebDriver driver, String fileWithPath) throws IOException {
		fileWithPath=fileWithPath.replaceAll(":", "");
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(fileWithPath));
	}
}
