package scripts;

import java.io.IOException;
import java.math.BigInteger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import generic.MainActivity;
import generic.PropertyFileRead;
import pages.RegisterPage;

public class RegisterAsIndividual extends MainActivity {
	RegisterPage objReg;
	@Test(priority=0)
	public void navigatetoregister() throws IOException, InterruptedException {
		start("Navigate to Registeration");
		objReg=new RegisterPage(driver);
		objReg.clickRegisterbtn();
		String actual=objReg.verifyRegPage.getText();
		String expected=PropertyFileRead.getProperty("RegPageVerify");
		info("Actual: "+actual);
		info("Expected: "+expected);
		Assert.assertTrue(actual.equalsIgnoreCase(expected));
		pass();
	}
	@Test(priority=1)
	public void validateNameofBuyer() throws InterruptedException, IOException {
		start("Validate Name of Buyer");
		clickOnElement(objReg.registerBtn);
		info("Clickon Register");
		asserAlert("alertNameofBuyer");
		//		JavascriptExecutor js = (JavascriptExecutor) driver;
		//		js.executeScript("var test= prompt(\"Please enter OTP:\", \" \")");
		//		Thread.sleep(10000);
		pass();
	}

	@Test(priority=2)
	public void validateAddress() throws InterruptedException, IOException {
		start("Validate Address");
		sendKey(objReg.nameOfBuyer, PropertyFileRead.getProperty("NameofBuyer"));
		logger.log(LogStatus.INFO, "Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		comboInputBox(objReg.buyerType, PropertyFileRead.getProperty("buyerTypeIndividual"));
		info("Buyer Type: "+PropertyFileRead.getProperty("buyerTypeIndividual"));
		clickOnElement(objReg.registerBtn);
		info("Clickon Register");
		asserAlert("alertAddress");
		pass();
	}
	@Test(priority=3)
	public void validateDistrict() throws InterruptedException, IOException {
		start("Validate District");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		sendKey(objReg.address, PropertyFileRead.getProperty("address"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		clickOnElement(objReg.registerBtn);
		logger.log(LogStatus.INFO, "Clickon Register");
		asserAlert("alertDistrict");
		pass();
	}
	@Test(priority=4)
	public void validateMobile() throws InterruptedException, IOException {
		start("Validate without Mobile");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		sendKey(objReg.address, PropertyFileRead.getProperty("address"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		comboInputBox(objReg.district,"Khun");
		info("District: "+"Khun");
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertMobile");
		pass();
	}
	@Test(priority=5)
	public void validateInvalidMobile() throws InterruptedException, IOException {
		start("Validate with Invalid Mobile");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		sendKey(objReg.mobile, PropertyFileRead.getProperty("InvalidMobileNo"));
		info("Given Mobile No: "+PropertyFileRead.getProperty("InvalidMobileNo"));
		info("Accepted Mobile No: "+objReg.mobile.getAttribute("value"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertMobile");
		pass();
	}
	@Test(priority=6)
	public void validatewithoutOTP() throws InterruptedException, IOException {
		start("Validate without OTP");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		sendKey(objReg.mobile, PropertyFileRead.getProperty("ValidMobileNo"));
		info("Given Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		info("Accepted Mobile No: "+objReg.mobile.getAttribute("value"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertOtp");
		pass();
	}
	@Test(priority=7)
	public void validatewithInvalidOTP() throws InterruptedException, IOException {
		start("Validate with Ivalid OTP");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		sendKey(objReg.otp,  PropertyFileRead.getProperty("InvalidOtp"));
		info("Given OTP: "+PropertyFileRead.getProperty("InvalidOtp"));
		info("Accepted OTP: "+objReg.otp.getAttribute("value"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertOtp");
		pass();
	}
	@Test(priority=8)
	public void validatevillage() throws InterruptedException, IOException {
		start("Validate village/town");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		sendKey(objReg.otp,  PropertyFileRead.getProperty("dummyOtp"));
		info("Given OTP: "+PropertyFileRead.getProperty("dummyOtp"));
		info("Accepted OTP: "+objReg.otp.getAttribute("value"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertVillage");
		pass();
	}
	@Test(priority=9)
	public void validateWithOutPinCode() throws InterruptedException, IOException {
		start("Validate without pin code");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		info("OTP: "+PropertyFileRead.getProperty("dummyOtp"));
		sendKey(objReg.village, PropertyFileRead.getProperty("village"));
		info("Village: "+PropertyFileRead.getProperty("village"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertPincode");
		pass();
	}
	@Test(priority=10)
	public void validateWithInvalidPinCode() throws InterruptedException, IOException {
		start("Validate with invalid pin code");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		info("OTP: "+PropertyFileRead.getProperty("dummyOtp"));
		info("Village: "+PropertyFileRead.getProperty("village"));
		sendKey(objReg.pincode, PropertyFileRead.getProperty("invalidpincode"));
		info("Given Pincode: "+PropertyFileRead.getProperty("invalidpincode"));
		info("Accepted pincode: "+objReg.pincode.getAttribute("value"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertPincode");
		pass();
	}
	@Test(priority=11)
	public void validateWithoutUserName() throws InterruptedException, IOException {
		start("Validate without user name");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		info("OTP: "+PropertyFileRead.getProperty("dummyOtp"));
		info("Village: "+PropertyFileRead.getProperty("village"));
		sendKey(objReg.pincode, PropertyFileRead.getProperty("pincode"));
		info("Pincode: "+PropertyFileRead.getProperty("pincode"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertusername");
		pass();
	}
	@Test(priority=12)
	public void validateWithoutpwd() throws InterruptedException, IOException {
		start("Validate without Password");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		info("OTP: "+PropertyFileRead.getProperty("dummyOtp"));
		info("Village: "+PropertyFileRead.getProperty("village"));
		info("Pincode: "+PropertyFileRead.getProperty("pincode"));
		sendKey(objReg.userName, PropertyFileRead.getProperty("UserNname"));
		info("User Name: "+PropertyFileRead.getProperty("UserNname"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertpassword");
		pass();
	}
	@Test(priority=13)
	public void validateWithinvalidpwd() throws InterruptedException, IOException {
		start("Validate without Password");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		info("OTP: "+PropertyFileRead.getProperty("dummyOtp"));
		info("Village: "+PropertyFileRead.getProperty("village"));
		info("Pincode: "+PropertyFileRead.getProperty("pincode"));
		info("User Name: "+PropertyFileRead.getProperty("UserNname"));
		sendKey(objReg.pwd, PropertyFileRead.getProperty("invlidpwd"));
		info("Password: "+PropertyFileRead.getProperty("invlidpwd"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertpassword");
		pass();
	}
	@Test(priority=14)
	public void validateWithoutcnfmpwd() throws InterruptedException, IOException {
		start("Validate without Confirm Password");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		info("OTP: "+PropertyFileRead.getProperty("dummyOtp"));
		info("Village: "+PropertyFileRead.getProperty("village"));
		info("Pincode: "+PropertyFileRead.getProperty("pincode"));
		info("User Name: "+PropertyFileRead.getProperty("UserNname"));
		sendKey(objReg.pwd, PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertcnfrmpwd6");
		pass();
	}
	@Test(priority=15)
	public void validateWithMismatchcnfmpwd() throws InterruptedException, IOException {
		start("Validate with Miss match Password");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		info("OTP: "+PropertyFileRead.getProperty("dummyOtp"));
		info("Village: "+PropertyFileRead.getProperty("village"));
		info("Pincode: "+PropertyFileRead.getProperty("pincode"));
		info("User Name: "+PropertyFileRead.getProperty("UserNname"));
		sendKey(objReg.pwd, PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		sendKey(objReg.cnfmpwd, PropertyFileRead.getProperty("dpwd"));
		info("Confirm Password: "+PropertyFileRead.getProperty("dpwd"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertcnfrmpwd6");
		pass();
	}
	@Test(priority=16)
	public void validateOtpMismatch() throws InterruptedException, IOException {
		start("Validate with Miss match OTP");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Mobile No: "+PropertyFileRead.getProperty("ValidMobileNo"));
		info("OTP: "+PropertyFileRead.getProperty("dummyOtp"));
		comboInputBox(objReg.buyerType, PropertyFileRead.getProperty("buyerTypeIndividual"));
		info("Buyer Type: "+PropertyFileRead.getProperty("buyerTypeIndividual"));
		info("Village: "+PropertyFileRead.getProperty("village"));
		info("Pincode: "+PropertyFileRead.getProperty("pincode"));
		info("User Name: "+PropertyFileRead.getProperty("UserNname"));
		sendKey(objReg.pwd, PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		sendKey(objReg.cnfmpwd, PropertyFileRead.getProperty("pwd"));
		info("Confirm Password: "+PropertyFileRead.getProperty("pwd"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("otpNotmatch");
		pass();
	}
	@Test(priority=18)
	public void registerWithValidUser() throws InterruptedException, IOException {
		start("Register with valid data");
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		info("Village: "+PropertyFileRead.getProperty("village"));
		info("Pincode: "+PropertyFileRead.getProperty("pincode"));
		sendKey(objReg.mobile, PropertyFileRead.getProperty("otpNtsendInvalidmobile"));
		info("Mobile No: "+PropertyFileRead.getProperty("otpNtsendInvalidmobile"));
		clickOnElement(objReg.sendOTPbtn);
		info("Click on SEND OTP Button.");
		if(isAlertPresent()) {
		asserAlert("alertOtpSent");
		}
		pass();
	}
	@Test(priority=18)
	void zfillValidData() throws InterruptedException, IOException {
		start("Fill the data");
		String otpS=otp();
		sendKey(objReg.otp, otpS);
		sendKey(objReg.userName, PropertyFileRead.getProperty("NewUser"));
		info("User Name: "+PropertyFileRead.getProperty("NewUser"));
		setUserName(PropertyFileRead.getProperty("NewUser"));
		sendKey(objReg.pwd, PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		sendKey(objReg.cnfmpwd, PropertyFileRead.getProperty("pwd"));
		info("Confirm Password: "+PropertyFileRead.getProperty("pwd"));
		sendKey(objReg.emailId, PropertyFileRead.getProperty("email"));
		info("Email ID: "+PropertyFileRead.getProperty("email"));
		sendKey(objReg.gst, PropertyFileRead.getProperty("gst"));
		info("GST No: "+PropertyFileRead.getProperty("gst"));
		sendKey(objReg.panNo, PropertyFileRead.getProperty("pan"));
		info("PAN No: "+PropertyFileRead.getProperty("pan"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		setMobileNo( PropertyFileRead.getProperty("otpNtsendInvalidmobile"));
		info("Mobile NO:"+PropertyFileRead.getProperty("otpNtsendInvalidmobile"));
		setPassword(PropertyFileRead.getProperty("pwd"));
		info("Password:"+PropertyFileRead.getProperty("pwd"));
		setUserName(PropertyFileRead.getProperty("NewUser"));
		info("User Name:"+PropertyFileRead.getProperty("NewUser"));
		//asserAlert("alertsuccess");
		pass();
	}
	@Test(priority=19)
	void aDBSet() throws InterruptedException {
		start("Read the Database values");
		Thread.sleep(5000);
		objReg=new RegisterPage(driver);
		getfromDB(getUserName());
		pass();
	}
	@Test(priority=19)
	void verifyNameWithDB() throws InterruptedException, IOException {
		start("Validate the name with Database");
		info("Actual: "+objReg.getNameofBuyer());
		info("Expected: "+PropertyFileRead.getProperty("NameofBuyer"));
		Assert.assertEquals(objReg.getNameofBuyer(), PropertyFileRead.getProperty("NameofBuyer"));
		pass();
	}
	@Test(priority=19)
	void verifyAddress() throws IOException {
		start("Validate an address with database");
		assertActExpect(objReg.getAddress(),PropertyFileRead.getProperty("address"));
		pass();
	}
	@Test(priority=19)
	void verifypincode() throws IOException {
		start("Validate the pincode with database");
		assertActExpect(objReg.getPinCode(),Integer.parseInt(PropertyFileRead.getProperty("pincode")));
		pass();
	}
	@Test(priority=19)
	void verifyUser() throws IOException {
		start("Validate the User name with database");
		assertActExpect(objReg.getuserName(), PropertyFileRead.getProperty("NewUser"));
		pass();
	}	
	@Test(priority=19)
	void verifyemail() throws IOException {
		start("Validate the email with database");
		assertActExpect(objReg.getEmailId(), PropertyFileRead.getProperty("email"));
		pass();
	}	
	@Test(priority=19)
	void verifygst() throws IOException {
		start("Validate the GST number with database");
		assertActExpect(objReg.getGSTNO(), PropertyFileRead.getProperty("gst"));
		pass();
	}
	@Test(priority=19)
	void verifypan() throws IOException {
		start("Validate the PAN/ID proof with database");
		assertActExpect(objReg.getPanNo(), PropertyFileRead.getProperty("pan"));
		pass();
	}
	@Test(priority=19)
	void verifyvillage() throws IOException {
		start("Validate the village with database");
		assertActExpect(objReg.getVillageTown(), PropertyFileRead.getProperty("village"));
		pass();
	}
	@Test(priority=19)
	void verifyMobile() throws IOException {
		start("Validate the Mobile No with database");
		assertActExpect(objReg.getmobileno(), BigInteger.valueOf(Long.parseLong((PropertyFileRead.getProperty("otpNtsendInvalidmobile")))));
		pass();
	}
	@Test(priority=19)
	void wNavigateToRegister() throws IOException, InterruptedException {
		navigatetoregister();
	}
	@Test(priority=19)
	public void xvalidateMobileAlready() throws InterruptedException, IOException {
		start("Validate with already exist Mobile number");
		sendKey(objReg.mobile, PropertyFileRead.getProperty("otpNtsendInvalidmobile"));
		info("Mobile No: "+PropertyFileRead.getProperty("otpNtsendInvalidmobile"));
		clickOnElement(objReg.sendOTPbtn);
		info("Click on SEND OTP Button.");
		asserAlert("alrtAlrdyMobile");
		pass();
	}

	@Test(priority=19)
	public void yavalidateUsernameAlready() throws InterruptedException, IOException {
		start("Validate with already exist username");
		sendKey(objReg.nameOfBuyer, PropertyFileRead.getProperty("NameofBuyer"));
		info("Name of Buyer: "+PropertyFileRead.getProperty("NameofBuyer"));
		sendKey(objReg.address, PropertyFileRead.getProperty("address"));
		comboInputBox(objReg.district,"Khun");
		sendKey(objReg.village, PropertyFileRead.getProperty("village"));
		sendKey(objReg.pincode, PropertyFileRead.getProperty("pincode"));
		info("Address: "+PropertyFileRead.getProperty("address"));
		info("District: "+PropertyFileRead.getProperty("district"));
		sendKey(objReg.mobile, PropertyFileRead.getProperty("newNumber"));
		info("Mobile No: "+PropertyFileRead.getProperty("newNumber"));
		clickOnElement(objReg.sendOTPbtn);
		info("Click on SEND OTP Button.");
		asserAlert("alertOtpSent");
		String otpS=otp();
		sendKey(objReg.otp, otpS);
		info("Village: "+PropertyFileRead.getProperty("village"));
		info("Pincode: "+PropertyFileRead.getProperty("pincode"));
		sendKey(objReg.userName, PropertyFileRead.getProperty("NewUser"));
		info("User Name: "+PropertyFileRead.getProperty("NewUser"));
		sendKey(objReg.pwd, PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		sendKey(objReg.cnfmpwd, PropertyFileRead.getProperty("pwd"));
		info("Confirm Password: "+PropertyFileRead.getProperty("pwd"));
		clickOnElement(objReg.registerBtn);
		info( "Clickon Register");
		asserAlert("alertAlredyexistuser");
		pass();
	}
	@Test(priority=19)
	void zNavigateToLogin() throws InterruptedException {
		start("Navigate to Login");
		clickOnElement(objReg.loginBtn);
		info("Click on login ");
		pass();
	}

}