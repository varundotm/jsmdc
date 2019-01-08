package scripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.PropertyFileRead;
import pages.LoginPage;

public class Login extends ForgotPassword {
	LoginPage objLP;

	@Test(priority=40)
	public void loginWithoutInput() throws InterruptedException, IOException {
		start("loginWithoutInput test");
		objLP=new LoginPage(driver);
		Thread.sleep(6000);
		objLP.clickOnLogin();
		info("Click on Login btn");
		asserAlert("alrtusername");
		pass();
	}
	@Test(priority=41)
	public void loginWithUsername() throws InterruptedException, IOException {
		start("loginWithUsername test");
		sendKey(objLP.userName,PropertyFileRead.getProperty("usrName"));
		info("User Name: "+PropertyFileRead.getProperty("usrName"));
		objLP.clickOnLogin();
		info("Click on login btn");
		asserAlert("alertPwd");
		pass();
	}
	@Test(priority=42)
	public void loginWithPwd() throws InterruptedException, IOException {
		start("loginWithPwd test");
		objLP.userName.clear();
		info("Username cleared");
		sendKey(objLP.passWord,PropertyFileRead.getProperty("pwd"));
		info("Password: "+PropertyFileRead.getProperty("pwd"));
		objLP.clickOnLogin();
		info("Click on login btn");
		asserAlert("alrtusername");
		pass();
	}
	@Test(priority=43,enabled=false)
	public void invalidUser() throws InterruptedException, IOException {
		start("invalidUser test");
		sendKey(objLP.userName,"");
		info("User Name:  ");
		sendKey(objLP.passWord,PropertyFileRead.getProperty("pwd"));
		info("Pass Word: "+PropertyFileRead.getProperty("pwd"));
		objLP.clickOnLogin();
		info("Click on login btn");
		asserAlert("invalidlogin");
		pass();
	}
	@Test(priority=44,enabled=false)
	public void invalidPwd() throws InterruptedException, IOException {
		start("invalidPwd test");
		sendKey(objLP.userName,PropertyFileRead.getProperty("usrName"));
		sendKey(objLP.passWord,PropertyFileRead.getProperty("dpwd"));
		info("User Name: "+PropertyFileRead.getProperty("usrName"));
		info("Pass Word: "+PropertyFileRead.getProperty("pwd"));
		objLP.clickOnLogin();
		info("Click on login btn");
		asserAlert("invalidlogin");
		pass();
	}
	@Test(priority=45,enabled=false)
	public void blockedUser() throws InterruptedException, IOException {
		start("Blocked User test");
		sendKey(objLP.userName,PropertyFileRead.getProperty("blockedUser"));
		sendKey(objLP.passWord,PropertyFileRead.getProperty("pwd"));
		info("User Name: "+PropertyFileRead.getProperty("blockedUser"));
		info("Pass Word: "+PropertyFileRead.getProperty("pwd"));
		objLP.clickOnLogin();
		info("Click on login btn");
		asserAlert("invalidlogin");
		pass();
	}
	@Test(priority=46)
	public void validCredential() throws InterruptedException, IOException {
		start("Valid Credential test");
		//comment this block if run from login class
				sendKey(objLP.userName,getUserName());
				sendKey(objLP.passWord,getPassword());
				info("User Name: "+getUserName());
				info("Pass Word: "+getPassword());
				objLP=new LoginPage(driver);

		//comment this block if run from register class
//		sendKey(objLP.userName,PropertyFileRead.getProperty("usrName"));
//		setUserName(PropertyFileRead.getProperty("usrName"));
//		sendKey(objLP.passWord,PropertyFileRead.getProperty("pwd"));
//		info("User Name: "+PropertyFileRead.getProperty("usrName"));
//		info("Pass Word: "+PropertyFileRead.getProperty("pwd"));

		objLP.clickOnLogin();
		info("Click on login btn");
		Assert.assertTrue(objLP.assertHomePage.isDisplayed());
		pass();
	}
}
