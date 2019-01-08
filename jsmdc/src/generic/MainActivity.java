package generic;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import pages.RegisterPage;
import scripts.BaseTest;



public class MainActivity extends BaseTest {

	HomePage objHome;
	RegisterPage objRegr;
	String UserName,PassWord,MobileNumber;

	static int cappedLimit,OTP;
	
	public void info(String string) {
		logger.log(LogStatus.INFO, string);
	}

	public void pass() {
		logger.log(LogStatus.PASS, "Test case passed");
	}

	public void fail() {
		logger.log(LogStatus.FAIL, "Test case Failed");
	}

	public void start(String string) {
		logger=extent.startTest(string);
	}
	
	public void sendKey(WebElement element,String string) throws InterruptedException {
		Thread.sleep(200);
		element.clear();
		if(string.isEmpty()||string==null) {
			element.sendKeys("   ");
		}else {
			element.sendKeys(string);
		}
		Thread.sleep(1000);
	}

	public void comboInputBox(WebElement element,String string) throws InterruptedException {
		element.click();
		Thread.sleep(800);
		objHome=new HomePage(driver);
		objHome.comboinput.sendKeys(string);
		Thread.sleep(400);
		objHome.comboinput.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(300);
		objHome.comboinput.sendKeys(Keys.ENTER);
	}

	public boolean isAlertPresent() throws InterruptedException{
		try{
			Thread.sleep(2000);
			if(objHome.swtalrtAccept.isDisplayed()){
				return true;
			}else {
				driver.switchTo().alert(); 
				return true;}
		}catch(NoAlertPresentException ex){
			return false;
		}}

	public void asserAlert(String string) throws InterruptedException, IOException {
		String actual=getAlertText().trim();
		String expected=PropertyFileRead.getProperty(string).trim();
		info("Actual: "+actual);
		info( "Expected: "+expected);
		Assert.assertTrue(actual.equalsIgnoreCase(expected));
	}

	public void alerts(String message) {
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		String script="alert(\""+message+"\")";	
		jse2.executeScript(script);
	}

	public void comboBox(WebElement element,String string) throws InterruptedException {
		element.clear();
		Thread.sleep(800);
		element.sendKeys(string);
		Thread.sleep(1200);
		element.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(300);
		element.sendKeys(Keys.ENTER);
	}

	public void checkBox(WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}
	}

	public void clickOnElement(WebElement element) throws InterruptedException {
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public boolean isElementPresent(WebElement element) throws InterruptedException{
		try{
			Thread.sleep(1200);
			if(element.isDisplayed()) {
				return true;
			}else {
				return false;
			}}
		catch(NoSuchElementException e){
			return false;
		}
	}
	public boolean isSwtalertPresent() {
		try {
			if(objHome.swtAlertPresnt.getAttribute("class").contains("show")) {
				return true;
			}else {
				return false;
			}
		}catch(NoSuchElementException e) {
			return false;
		}
	}

	public static void chechboxSelect(WebElement element1,WebElement element2) {
		System.out.println(element1.getAttribute("class").contains("selected"));
		if(!element1.getAttribute("class").contains("selected")) {
			element2.click();
		}
	}

	public void clickdisplayedBtn(List<WebElement> element) throws InterruptedException {
		Thread.sleep(3000);
		for(WebElement ele: element) {
			if(ele.isDisplayed()) {
				ele.click();
				break;
			}
		}
	}

	public void assertActExpect(String act,String expec) {
		info("Actual: "+act);
		info("Expected: "+expec);
		Assert.assertEquals(act, expec);
	}
	public void assertActExpect(int act,int expec) {
		info("Actual: "+act);
		info("Expected: "+expec);
		Assert.assertTrue(act==expec);
	}
	public void assertActExpect(BigInteger act,BigInteger expec) {
		info("Actual: "+act);
		info("Expected: "+expec);
		Assert.assertTrue(act==expec);
	}

	public String getTextFromFirstEle(List<WebElement> element) throws InterruptedException {
		Thread.sleep(2000);
		if(element.size()>0) {
			WebElement firstele=element.get(0);
			String output=firstele.getText();
			return output;
		}else { return null;}
	}
	public void clickFirstele(List<WebElement> element) {
		if(element.size()>0) {
			WebElement firstele=element.get(0);
			firstele.click();}
	}

	public void sendTodisplayedTxt(List<WebElement> element,String input) throws InterruptedException {
		Thread.sleep(3000);
		for(WebElement ele: element) {
			if(ele.isDisplayed()) {
				ele.clear();
				ele.sendKeys(input);
				break;
			}
		}
	}

	public void sendTodisplayedCombo(List<WebElement> element,String string) throws InterruptedException {
		Thread.sleep(3000);
		for(WebElement ele: element) {
			if(ele.isDisplayed()) {
				ele.clear();
				Thread.sleep(800);
				ele.sendKeys(string);
				Thread.sleep(1200);
				ele.sendKeys(Keys.ARROW_DOWN);
				ele.sendKeys(Keys.ENTER);
				break;
			}
		}
	}

	

	public void clrDisEle(List<WebElement> element) {
		for(WebElement ele: element) {
			if(ele.isDisplayed()) {
				ele.clear();
				break;
			}
		}
	}

	public void scrollTo(WebElement element) {
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		jse2.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void checkFrames() {
		int i=0;
		List<WebElement> frames=new ArrayList<>();
		frames=driver.findElements(By.tagName("iframe"));
		for(WebElement ele:frames) {
			System.out.println(i+": "+ele.getAttribute("src"));
			i++;		
		}
	}
	//	public String getAlertText() throws InterruptedException {
	//		new WebDriverWait(driver, 80)
	//		.ignoring(NoAlertPresentException.class)
	//		.until(ExpectedConditions.alertIsPresent());
	//		Alert alert=driver.switchTo().alert();
	//		String alertText=alert.getText();
	//		Thread.sleep(2000);
	//		alert.accept();
	//		return alertText;
	//	}

	public String getAlertText() throws InterruptedException {
		objHome=new HomePage(driver);
		Thread.sleep(1200);
		String alertText=objHome.swtalrtText.getText();
		Thread.sleep(2000);
		objHome.swtalrtAccept.click();
		return alertText;
	}

	public String slectedValue(WebElement ele) {
		Select select=new Select(ele);
		String result =select.getFirstSelectedOption().getText();
		return result;
	}

	public String otp() throws InterruptedException {
		Thread.sleep(3000);
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		String entryall="";
		for (LogEntry entry : logEntries) {
			entryall=entryall+entry.getMessage();
		}
		String reverse=new StringBuilder(entryall).reverse().toString();
		String[] value= reverse.split("\" ");
		String out=value[0].replaceAll("[^0-9]", "");
		return new StringBuilder(out).reverse().toString();
	}

	public void clearLog() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "console.clear()";
		js.executeScript(script);
	}

	public void setUserName(String UserName) {
		this.UserName=UserName;
	}

	public void setPassword(String PassWord) {
		this.PassWord=PassWord;
	}

	public void setMobileNo(String MobileNumber) {
		this.MobileNumber=MobileNumber;
	}

	public void setOTP(int OTP) {
		MainActivity.OTP=OTP;
	}

	public void setCappedlimit(int cappedLimit) {
		MainActivity.cappedLimit=cappedLimit;
	}

	public int getCappedlimit() {
		return cappedLimit;
	}

	public String getUserName() {
		return UserName;
	}

	public String getPassword() {
		return PassWord;
	}

	public String getMobileNo() {
		return MobileNumber;
	}

	public int getOTP() {
		return OTP;
	}
	void testingDb() throws InterruptedException {
		getfromDB("Automation");
	}


	public void getfromDB(String string) throws InterruptedException {
		Thread.sleep(1000);
		try{  
			objRegr=new RegisterPage(driver);
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://13.233.180.38:3306/sand_consumer_model","varunm","India@123");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from users where UserName='"+string+"'"); 
			objRegr.setJson(ResultSetConverter.convert(rs));
			con.close();  
		}catch(Exception e){ System.out.println(e);}  
	}

	public ArrayList<WebElement> getAllOptions(WebElement element){
		Select select = new Select(element);
		ArrayList<WebElement> allOptions = (ArrayList<WebElement>) select.getOptions();
		return allOptions ;
	}

	public Set<String> findDuplicates(List<WebElement> listContainingDuplicates)
	{ 
		final Set<String> setToReturn = new HashSet<String>(); 
		final Set<String> set1 = new HashSet<String>();
		for (WebElement element : listContainingDuplicates)
		{
			if (!set1.add(element.getText()))
			{
				setToReturn.add(element.getText());
			}
		}
		return setToReturn;
	}
	

}