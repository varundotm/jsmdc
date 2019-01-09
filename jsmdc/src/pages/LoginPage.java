package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(xpath="//*[@id='username']")
	public	WebElement userName;
	
	@FindBy(xpath="//*[@id='password']")
	public	WebElement passWord;
	
	@FindBy(xpath="//*[@id=\"sign_in\"]/div/label/span[1]")
	WebElement remeberMe;
	
	@FindBy(xpath="//*[@value='LOG IN']")
	WebElement loginbtn;
	
	@FindBy(linkText="Forgot your password?")
	public	WebElement forgotPasswordlnk;
	
	@FindBy(id="username")
	public WebElement forgotPasswordUsrnme;
	
	@FindBy(id="mobile")
	public WebElement forgotPasswordnumber;
	
	@FindBy(xpath="//*[@value='SEND OTP']")
	public WebElement forgotPasswordSendOtpBtn;
	
	@FindBy(id="otpEntered")
	public WebElement forgotPasswordOtp;
	
	@FindBy(id="password")
	public WebElement forgotPasswordPwd;
	
	@FindBy(id="confirmPassword")
	public WebElement forgotPasswordCnfmPwd;
	
	@FindBy(xpath="//*[@value='CHANGE PASSWORD']")
	public WebElement forgotPasswordChngPwdBtn;
	
	@FindBy(xpath="//*[@value='REGISTER NOW']")
	public WebElement navigateToRegister;
	
	@FindBy(xpath="/html/body/div[3]/div/h3")
	public WebElement assertLoginPage;
	
	@FindBy(linkText="BUY NOW")
	public WebElement assertHomePage;
	
			
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnLogin() throws InterruptedException {
		Thread.sleep(1000);
		loginbtn.click();
	}
}