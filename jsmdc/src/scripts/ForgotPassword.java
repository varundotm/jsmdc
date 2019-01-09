package scripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.PropertyFileRead;
import pages.LoginPage;

public class ForgotPassword extends RegisterAsIndividual {
	LoginPage objLP;
	public void fpWithvalidData() throws InterruptedException {
		sendKey(objLP.forgotPasswordUsrnme, getUserName());
		info("User Name: "+getUserName());
		sendKey(objLP.forgotPasswordnumber, getMobileNo()); 
		info("Mobile number: "+getMobileNo());
		clickOnElement(objLP.forgotPasswordSendOtpBtn); 
		info("Click on SEND OTP button.");
	}
	@Test(priority=20)
	public void navigateToForgotPwd() throws InterruptedException {
		start("Navigate to Forgot Password");
		Thread.sleep(3000);
		objLP=new LoginPage(driver);
		clickOnElement(objLP.forgotPasswordlnk);
		info("Click on Forgot Password link");
		pass();
	}
	@Test(priority=21)
	public void forgotPasswordwithoutUserName() throws InterruptedException, IOException {
		start("forgotPassword without user name test");
		clickOnElement(objLP.forgotPasswordSendOtpBtn);
		info("Click on SEND OTP button.");
		asserAlert("alrtusername");
		pass();
	}
	@Test(priority=22)
	public void forgotPasswordwithoutNum() throws InterruptedException, IOException {
		start("forgotPassword without mobile number test");
		sendKey(objLP.forgotPasswordUsrnme, PropertyFileRead.getProperty("usrName"));
		info("User Name: "+PropertyFileRead.getProperty("usrName"));
		clickOnElement(objLP.forgotPasswordSendOtpBtn);
		info("Click on SEND OTP button.");
		asserAlert("alertFPwdMob");
		pass();
	}
	@Test(priority=23)
	public void forgotPasswordwithInvalidMobile() throws InterruptedException, IOException {
		start("forgot Password with Invalid Mobile test");
		sendKey(objLP.forgotPasswordUsrnme, PropertyFileRead.getProperty("usrName"));
		info("User Name: "+PropertyFileRead.getProperty("usrName"));
		sendKey(objLP.forgotPasswordnumber, PropertyFileRead.getProperty("InvalidMobileNo"));
		info("Mobile number: "+PropertyFileRead.getProperty("InvalidMobileNo"));
		clickOnElement(objLP.forgotPasswordSendOtpBtn);
		info("Click on SEND OTP button.");
		asserAlert("alertFPMobile");
		pass();
	}
	@Test(priority=24)
	public void forgotPasswordwithInvaliddata() throws InterruptedException, IOException {
		start("forgot Password with Invalid Data");
		sendKey(objLP.forgotPasswordUsrnme, PropertyFileRead.getProperty("InvalidOtp"));
		info("User Name: "+PropertyFileRead.getProperty("InvalidOtp"));
		sendKey(objLP.forgotPasswordnumber, PropertyFileRead.getProperty("ValidMobileNo"));
		info("Mobile number: "+PropertyFileRead.getProperty("ValidMobileNo"));
		clickOnElement(objLP.forgotPasswordSendOtpBtn);
		info("Click on SEND OTP button.");
		asserAlert("alertOtpNtSent");
		pass();
	}
	@Test(priority=25)
	public void forgotPasswordwithValidData() throws InterruptedException, IOException {
		start("forgot Password with valid Data");
		fpWithvalidData();
		pass();
	}
	@Test(priority=26)
	public void forgotPasswordwithoutOTP() throws InterruptedException, IOException {
		start("forgot Password without give OTP");
		Thread.sleep(3000);
		clickOnElement(objLP.forgotPasswordChngPwdBtn);
		info("Click on change password");
		asserAlert("alertOtpFP");		
		pass();
	}
	@Test(priority=27)
	public void forgotPasswordwithInvalidOTP() throws InterruptedException, IOException {
		start("forgot Password with Invalid OTP");
		sendKey(objLP.forgotPasswordOtp, PropertyFileRead.getProperty("InvalidOtp"));
		info("OTP: "+ PropertyFileRead.getProperty("InvalidOtp"));
		clickOnElement(objLP.forgotPasswordChngPwdBtn);
		info("Click on change password");
		asserAlert("alertOtp");		
		pass();
	}
	@Test(priority=28)
	public void forgotPasswordwithoutPWD() throws InterruptedException, IOException {
		start("forgot Password without Password");
		sendKey(objLP.forgotPasswordOtp, PropertyFileRead.getProperty("dummyOtp"));
		info("OTP: "+ PropertyFileRead.getProperty("dummyOtp"));
		clickOnElement(objLP.forgotPasswordChngPwdBtn);
		info("Click on change password");
		asserAlert("alertPwd");		
		pass();
	}
	@Test(priority=29)
	public void forgotPasswordwithinvalidPWD() throws InterruptedException, IOException {
		start("forgot Password with invalid Pwd");
		sendKey(objLP.forgotPasswordOtp, PropertyFileRead.getProperty("dummyOtp"));
		info("OTP: "+ PropertyFileRead.getProperty("dummyOtp"));
		sendKey(objLP.forgotPasswordPwd, PropertyFileRead.getProperty("invlidpwd"));
		info("Password: "+PropertyFileRead.getProperty("invlidpwd"));
		clickOnElement(objLP.forgotPasswordChngPwdBtn);
		info("Click on change password");
		asserAlert("alertpwdsix");		
		pass();
	}
	@Test(priority=30)
	public void forgotPasswordwithoutCnfmPWD() throws InterruptedException, IOException {
		start("forgot Password without confirmPwd");
		sendKey(objLP.forgotPasswordOtp, PropertyFileRead.getProperty("dummyOtp"));
		info("OTP: "+ PropertyFileRead.getProperty("dummyOtp"));
		sendKey(objLP.forgotPasswordPwd, PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		clickOnElement(objLP.forgotPasswordChngPwdBtn);
		info("Click on change password");
		asserAlert("alertCnfmPwd");		
		pass();
	}
	@Test(priority=31)
	public void forgotPasswordwithMissmatchPWD() throws InterruptedException, IOException {
		start("forgot Password with miss match Pwd");
		sendKey(objLP.forgotPasswordOtp, PropertyFileRead.getProperty("dummyOtp"));
		info("OTP: "+ PropertyFileRead.getProperty("dummyOtp"));
		sendKey(objLP.forgotPasswordPwd, PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		sendKey(objLP.forgotPasswordCnfmPwd, PropertyFileRead.getProperty("dpwd"));
		info("Confirm Password: "+PropertyFileRead.getProperty("dpwd"));
		clickOnElement(objLP.forgotPasswordChngPwdBtn);
		info("Click on change password");
		asserAlert("alertFPMISmatchPWD");		
		pass();
	}

	@Test(priority=32)
	public void forgotPasswordwithWrongOTP() throws InterruptedException, IOException {
		start("forgot Password with wrong OTP");
		sendKey(objLP.forgotPasswordOtp, PropertyFileRead.getProperty("InvalidOtp"));
		info("OTP: "+ PropertyFileRead.getProperty("InvalidOtp"));
		sendKey(objLP.forgotPasswordPwd, PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		sendKey(objLP.forgotPasswordCnfmPwd, PropertyFileRead.getProperty("pwd"));
		info("Confirm Password: "+PropertyFileRead.getProperty("pwd"));
		clickOnElement(objLP.forgotPasswordChngPwdBtn);
		info("Click on change password");
		asserAlert("alertFail");		
		pass();
	}
	@Test(priority=33)
	public void forgotPasswordwithAllValidDATA() throws InterruptedException, IOException {
		start("forgot Password with all valid data");
		fpWithvalidData();
		getfromDB(getUserName());
		sendKey(objLP.forgotPasswordOtp, new Integer(getOTP()).toString());
		info("OTP: "+ new Integer(getOTP()).toString());
		sendKey(objLP.forgotPasswordPwd, PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		sendKey(objLP.forgotPasswordCnfmPwd, PropertyFileRead.getProperty("pwd"));
		info("Confirm Password: "+PropertyFileRead.getProperty("pwd"));
		clickOnElement(objLP.forgotPasswordChngPwdBtn);
		info("Click on change password");
		Thread.sleep(2000);
		Assert.assertTrue(objLP.assertLoginPage.getText().equals("LOGIN"));	
		pass();
	}
}