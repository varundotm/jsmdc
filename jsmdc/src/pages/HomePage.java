package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage  {

	@FindBy(xpath="//*[@class='swal-text']")
	public WebElement swtalrtText;

	@FindBy(xpath="//*[@class='swal-button swal-button--confirm']")
	public WebElement swtalrtAccept;

	@FindBy(xpath="//body/div[6]")
	public WebElement swtAlertPresnt;

	@FindBy(xpath="//*[@type='search']")
	public WebElement comboinput;

	@FindBy(tagName="body")
	public WebElement browserTag;

	@FindBy(id="select2-select-menu-district-container")
	public WebElement comboDistrict;

	@FindBy(id="select2-select-menu-container")
	public WebElement comboStockyard;

	@FindBy(id="availableStock")
	public WebElement availableStock;

	@FindBy(id="unitPrice")
	public WebElement unitPrice;

	@FindBy(id="tractorQty")
	public WebElement tractorQty;

	@FindBy(id="miniHaiwaQty")
	public WebElement miniHaiwaQty;

	@FindBy(id="truck6Qty")
	public WebElement truck6Qty;

	@FindBy(id="truck10Qty")
	public WebElement truck10Qty;

	@FindBy(id="truck12Qty")
	public WebElement truck12Qty;

	@FindBy(id="truckTrailor14Qty")
	public WebElement truckTrailor14Qty;

	@FindBy(id="truck14Qty")
	public WebElement truck14Qty;

	@FindBy(id="truck18Qty")
	public WebElement truck18Qty;

	@FindBy(id="truck22Qty")
	public WebElement truck22Qty;

	@FindBy(id="tractorTotal")
	public WebElement tractorTotal;

	@FindBy(id="miniHaiwaTotal")
	public WebElement miniHaiwaTotal;

	@FindBy(id="truck6Total")
	public WebElement truck6Total;

	@FindBy(id="truck10Total")
	public WebElement truck10Total;

	@FindBy(id="truck12Total")
	public WebElement truck12Total;

	@FindBy(id="truckTrailor14Total")
	public WebElement truckTrailor14Total;

	@FindBy(id="truck14Total")
	public WebElement truck14Total;

	@FindBy(id="truck18Total")
	public WebElement truck18Total;

	@FindBy(id="truck22Total")
	public WebElement truck22Total;

	@FindBy(id="totalQty")
	public WebElement totalQty;

	@FindBy(id="gstVal")
	public WebElement gstVal;

	@FindBy(id="totalPrice")
	public WebElement totalPrice;

	@FindBy(id="totalPayable")
	public WebElement totalPayable;

	@FindBy(xpath="//*[@class='card']/div/div[7]/div/span/i")
	public WebElement gstPercent;

	@FindBy(id="select-menu-district")
	public WebElement selectDistrict;

	@FindBy(id="select-menu")
	public WebElement selectStockyard;

	@FindBy(id="gmimap0")
	public List<WebElement> mapPushpins;

	@FindBy(linkText="BUY NOW")
	public WebElement buynowBtn;

	@FindBy(id="address")
	public WebElement comboAddress;

	@FindBy(xpath="//*[@class='form-step-two']/a")
	public WebElement reviewBookingbtn;

	@FindBy(id="altPhone")
	public WebElement altPhone;

	@FindBy(linkText="CONFIRM & PAY")
	public WebElement confirmPayBtn;

	@FindBy(id="stockyard")
	public WebElement stockyardCP;

	@FindBy(id="unitPrice")
	public WebElement unitPriceCP;

	@FindBy(id="quantity")
	public WebElement quantityCP;

	@FindBy(id="price")
	public WebElement priceCP;

	@FindBy(id="gst")
	public WebElement gstCP;

	@FindBy(id="totalPrice")
	public WebElement totalPricePayCP;

	@FindBy(xpath="//*[@id=\"main-blocks-cart\"]/div[1]/div[5]/a/i")
	public WebElement editBtn;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);		
	}
	double gstval;
	public void setGSTVal() {
		gstval=Double.parseDouble(gstPercent.getText().replace("%", ""));
		//System.out.println("Set home gst: "+gstval);

	}
	public double getGSTval(){
		//System.out.println("Get home gst: "+gstval);
		return gstval;
	}
}