package scripts;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import generic.PropertyFileRead;
import generic.ScreenCapture;

public class BaseTest {
	public static WebDriver driver;
	public ExtentReports extent;
	public ExtentTest logger;
	//	public static void main(String[] args) throws ClassNotFoundException {
	//        TestNG test = new TestNG();
	//         test.setTestClasses(new Class[] { Payment.class });
	//         test.run();
	//    }
	@Parameters("browser")
	@BeforeTest()
	public void setup(@Optional("chrome")String browser) throws IOException{
		extent = new ExtentReports (System.getProperty("user.dir") +"/extend report/STMExtentReport.html", true);
		// getProperty("/ExtendReport/STMExtentReport.html", true));
		//extent.addSystemInfo("Environment","Environment Name")
		extent
		.addSystemInfo("Host Name", PropertyFileRead.getProperty("URL"))
		.addSystemInfo("Environment", "Automation Testing")
		.addSystemInfo("User Name", "Varun M");
		//loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		//You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		extent.loadConfig(new File(System.getProperty("user.dir")+"/driver/extent-config.xml"));
		//String browserName=PropertyFileRead.getProperty("browserName");
		int responseCode=200;
		URL nUrl=new URL( PropertyFileRead.getProperty("URL"));
		HttpURLConnection con=(HttpURLConnection)nUrl.openConnection();
		con.connect();
		responseCode=con.getResponseCode();
		if(responseCode>400) {
			//info(PropertyFileRead.getProperty("URL")+" is down!! :(");
		}else {
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.ALL);
				caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
				driver = new ChromeDriver(caps);
			}else {
				System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
				driver=new FirefoxDriver();
			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get(PropertyFileRead.getProperty("URL"));
			driver.manage().window().maximize();
		}
		con.disconnect();
	}
	@AfterMethod
	public void failScreenShot(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			try{
				String filePath=".//screenshot//SN"+LocalDateTime.now()+".png";
				ScreenCapture.takeSnapShot(driver, filePath);
			}catch(Exception e) {
				System.out.println(e);
			}
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
		}
	@AfterTest
	public void closeCon() throws InterruptedException {
		Thread.sleep(2000);
		extent.flush();
		extent.close();
		Thread.sleep(2000);
		driver.quit();
	}
}