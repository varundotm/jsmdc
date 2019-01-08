package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="ADD TO CART")
	public WebElement ADDTOCARTbtn;
	
	@FindBy(xpath="//*[@id='cartHistory']/div/div/div[@class='cart-name-prodct']/span")
	public List<WebElement> stockyardNameIncart;
	
	@FindBy(xpath="//*[@id='cartHistory']/div/div/div[@class='cart-mark']/span[1]")
	public List<WebElement> cartQty;
	
	@FindBy(xpath="//*[@id='cartHistory']/div/div/div[@class='cart-overall-price']/span[2]")
	public List<WebElement> cartPrice;
	
	@FindBy(xpath="//*[@id='cartHistory']/div/div/div[5]")
	public List<WebElement> buyBtn;
	
	@FindBy(xpath="//*[@id='cartHistory']/div/div/div[@class='delete-cart-product']")
	public List<WebElement> deleteCart;

}
