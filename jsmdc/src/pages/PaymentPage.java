package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

	@FindBy(xpath="//*[@id=\"DDC\"]/table/tbody/tr[6]/td[2]/b/span")
	public WebElement onlinePayPrice;
	
	@FindBy(xpath="//*[@id=\"procPay\"]/span")
	public WebElement proceedPay;
	
	@FindBy(id="RDDC")
	public WebElement debitRadio;
	
	@FindBy(xpath="//*[@value='Proceed Now']")
	public List<WebElement> ProceedPaybtn;
	
	@FindBy(id="cancel")
	public WebElement cancelPaybtn;
	
	@FindBy(xpath="/html/body/div[2]/div/div[2]/span[1]")
	public WebElement bookingId;
	
	@FindBy(xpath="/html/body/div[2]/div/div[2]/span[2]")
	public WebElement paymentStatus;
	
	@FindBy(id="address")
	public WebElement destinationAddress;
	
	@FindBy(linkText="Go to all of my bookings")
	public WebElement myaccOrders;
	
	@FindBy(xpath="/html/body/div[2]/div/div[1]/div[1]/h1")
	public WebElement successMsg;
	
	@FindBy(xpath="//*[@id=\"col-size-logo\"]/a/img")
	public WebElement imglogos;	
	
	@FindBy(xpath="//*[@id=\"cc\"]/a")
	public WebElement creditCard;
	
	public PaymentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}