package pages;

import java.math.BigInteger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.MainActivity;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id="buyer")
	public WebElement nameOfBuyer;

	@FindBy(xpath="//*[@class='registerBtn']")
	public WebElement registerBtn;

	@FindBy(xpath="//*[@id=\"sign_in_form\"]/div/div[2]/a/input")
	public WebElement loginBtn;

	@FindBy(id="address")
	public WebElement address;

	@FindBy(xpath="//*[@id=\"select2-select-district-container\"]")
	public WebElement district;

	@FindBy(xpath="//*[@id=\"select2-select-state-container\"]")
	public WebElement state;

	@FindBy(id="mobile")
	public WebElement mobile;

	@FindBy(id="otp")
	public WebElement otp;

	@FindBy(id="select2-select-type-container")
	public WebElement typeOfBuyer;

	@FindBy(id="village")
	public WebElement village;

	@FindBy(id="pincode")
	public WebElement pincode;

	@FindBy(id="gst")
	public WebElement gst;

	@FindBy(id="emailId")
	public WebElement emailId;

	@FindBy(id="panNo")
	public WebElement panNo;

	@FindBy(xpath="//*[@placeholder='USER NAME*']")
	public WebElement userName;

	@FindBy(xpath="//*[@placeholder='PASSWORD*']")
	public WebElement pwd;

	@FindBy(xpath="//*[@placeholder='CONFIRM PASSWORD*']")
	public WebElement cnfmpwd;

	@FindBy(xpath="//*[@value='SEND OTP']")
	public WebElement sendOTPbtn;

	@FindBy(xpath="//*[@value='REGISTER NOW']")
	public WebElement navigateToRegister;

	@FindBy(xpath="//body/h3[1]")
	public WebElement verifyRegPage;

	@FindBy(id="select2-select-type-container")
	public WebElement buyerType;

	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	static String NameofBuyer,Address,District,GSTNO,VillageTown,TypeofBuyer,PinCode,EmailId,PanNo,username; 
	static BigInteger mobileno;
	JSONArray jsonRslt;
	public void setNameofBuyer(String NameofBuyer) {
		RegisterPage.NameofBuyer=NameofBuyer;
	}
	public String getNameofBuyer() {
		return NameofBuyer;
	}
	public void setAddress(String Address) {
		RegisterPage.Address=Address;
		//System.out.println("Set Address: "+RegisterPage.Address);
	}
	public String getAddress() {
		System.out.println("get Address: "+Address);
		return Address;
	}
	public void setDistrict(String District) {
		RegisterPage.District=District;
	}
	public String getDistrict() {
		return District;
	}
	public void setGSTNO(String GSTNO) {
		if(!GSTNO.equals(null)) {
		RegisterPage.GSTNO=GSTNO;}
	}
	public String getGSTNO() {
		if(!GSTNO.equals(null)) {
		return GSTNO;}else {return null;}
		}
	public void setVillageTown(String VillageTown) {
		RegisterPage.VillageTown=VillageTown;
	}
	public String getVillageTown() {
		return VillageTown;
	}
	public void setTypeofBuyer(String TypeofBuyer) {
		RegisterPage.TypeofBuyer=TypeofBuyer;
	}
	public String getTypeofBuyer() {
		return TypeofBuyer;
	}
	public void setPinCode(String PinCode) {
		RegisterPage.PinCode=PinCode;
	}
	public int getPinCode() {
		return Integer.parseInt(PinCode);
	}
	public void setEmailId(String EmailId) {
		RegisterPage.EmailId=EmailId;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setPanNo(String PanNo) {
		RegisterPage.PanNo=PanNo;
	}
	public String getPanNo() {
		return PanNo;
	}
	public void setuserName(String username) {
		RegisterPage.username=username;
	}
	public String getuserName() {
		return username;
	}
	public void setmobileno(Long mobileno) {
		RegisterPage.mobileno=BigInteger.valueOf(mobileno);
	}
	public BigInteger getmobileno() {
		return mobileno;
	}
	public void setJson(JSONArray jsonRslt) throws JSONException {
		this.jsonRslt=jsonRslt;
		System.out.println(jsonRslt);
		setAllValues();
	}
	public void setAllValues() throws JSONException{
		JSONObject data = jsonRslt.getJSONObject(0);
		setNameofBuyer(data.getString("FullName"));
		setAddress(data.getString("Address"));
		//setDistrict(data.getString("FullName"));
		setEmailId(data.getString("Email"));
		setPanNo(data.getString("ProofOfIdentity"));
		setPinCode(data.getString("Pincode"));
		setTypeofBuyer(data.getString("TypeOfBuyer"));
		setVillageTown(data.getString("City"));
		setuserName(data.getString("UserName"));
		setmobileno(data.getLong("MobileNo"));
		new MainActivity().setOTP(data.getInt("Otp"));
		new MainActivity().setCappedlimit(data.getInt("CappedLimit"));
		//setGSTNO(data.getString("GST"));
	}

	public void clickRegisterbtn() throws InterruptedException {
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", navigateToRegister);
		navigateToRegister.click();
	}

}
