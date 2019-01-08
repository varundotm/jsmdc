package scripts;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

import generic.ExcelData;
import pages.CartPage;
import pages.HomePage;
import pages.PaymentPage;

public class CartOperTest extends Payment{
	PaymentPage objPay;
	CartPage objCart;
	@Test(priority=100)
	void navigateToHomePage() throws InterruptedException {
		start("navigateToHomePage");
		objPay=new PaymentPage(driver);
		clickOnElement(objPay.imglogos);
		info("Click on logo");
		pass();
	}
	@Test(priority=101)
	void checkAddCartBtn() throws InterruptedException, IOException {
		start("Validate Add to Cart without district");
		objCart=new CartPage(driver);
		clickOnElement(objCart.ADDTOCARTbtn);
		asserAlert("alertDistrictBN");
		pass();
	}
	@Test(priority=101)
	void dcheckAddCartBtn() throws InterruptedException, IOException {
		start("Validate Add to Cart without Stockyard");
		objHM=new HomePage(driver);
		comboInputBox(objHM.comboDistrict, "Ranch");
		clickOnElement(objCart.ADDTOCARTbtn);
		asserAlert("alertStockyrdBN");
		pass();
	}
	@Test(priority=101)
	void echeckAddCartBtn() throws InterruptedException, IOException {
		start("Validate Add to Cart without quantity");
		comboInputBox(objHM.comboDistrict, "Ranch");
		comboInputBox(objHM.comboStockyard, "abc");
		clickOnElement(objCart.ADDTOCARTbtn);
		asserAlert("alertQtyBN");
		pass();
	}
	@Test(priority=101)
	void selectDistAndStockyard() throws InterruptedException, IOException {
		start("Select district and stockyard");
		vali_stock();
		exeedlimit();
		zReset();
		pass();
	}
	@Test(priority=102,dataProvider="cart")
	void fillBookingForm(String vehicle,String cap,String NoOfvehicle) throws InterruptedException, IOException {
		start("Fill the booking form");
		checkTotlcft(vehicle,Integer.parseInt(NoOfvehicle),Integer.parseInt(cap),"write");
		pass();
	}
	@DataProvider(name="cart")
	public static Object[][] linit() throws Exception{
		Object[][] data=ExcelData.getTableArray(".\\data\\BookingForm.xls", "limited", 3);
		return data;
	}
	@Test(priority=104)
	void checkCalculation() {
		start("Check calculations");
		validateprice();
		pass();
	}
	@Test(priority=103)
	void checkQtyB() {
		start("Check total quantity");
		checkQTY();
		pass();
	}
	@Test(priority=105)
	void clickonAddToCart() throws InterruptedException {
		start("Click on add  tocart");
		clickOnElement(objCart.ADDTOCARTbtn);
		pass();
	}
	@Test(priority=106)
	void checkStockyardInCart() throws InterruptedException {
		start("Check Stockyard in Cart");
		String actual=getTextFromFirstEle(objCart.stockyardNameIncart);
		String expected=getStockyard();
		Assert.assertTrue(actual.equalsIgnoreCase(expected));
		pass();
	}
	@Test(priority=107)
	void checkQTYInCart() throws InterruptedException {
		start("Check QTY in Cart");
		int actual=Integer.parseInt(getTextFromFirstEle(objCart.cartQty));
		int expected=getTotalQty();
		Assert.assertTrue(actual==expected);
		pass();
	}
	@Test(priority=108)
	void checkPriceInCart() throws InterruptedException {
		start("Check Price in Cart");
		double actual=Double.parseDouble(getTextFromFirstEle(objCart.cartPrice));
		double expected=getTotalPayabl();
		Assert.assertTrue(actual==expected);
		pass();
	}
	@Test(priority=109)
	void clickOnBuy() {
		start("Click on Buy");
		clickFirstele(objCart.buyBtn);
		pass();
	}
	@Test(priority=110)
	void checkDestination() throws InterruptedException, IOException {
		start("Check destination location");
		RBwithoutLoc();
		pass();
	}
	@Test(priority=111)
	void checkDestinationWithOutMobile() throws InterruptedException, IOException {
		start("Validate without Alt mobile no");
		rBWithoutAltmob();
		pass();
	}
	@Test(priority=112)
	void checkDestinationWithInvalidmobile() throws InterruptedException, IOException {
		start("Validate RB with invalid mobile no");
		rBInvalidMobile();
		pass();
	}
	@Test(priority=113)
	void checkDestinationWithvalidmobile() throws InterruptedException, IOException {
		start("Validate RB with valid data");
		validatewithValidRB();
		pass();
	}
	@Test(priority=114)
	void checkStockyardinCP() {
		start("Stockyard validation in confirm page");
		stockyardCP();
		pass();
	}
	@Test(priority=115)
	void checkUnitPriceCP(){
		start("Check unit price in confirm page");
		unitPriceCP();
		pass();
	}
	@Test(priority=116)
	void checkTotalQTY() {
		start("Check total Qty");
		totalQTYCP();
		pass();
	}
	@Test(priority=117)
	void checkTotPriceCP() {
		start("Check total price in CP");
		totalPriceinCP();
		pass();
	}
	@Test(priority=118)
	void checkGSTinCP() {
		start("Check GST in CP");
		gstinCP();
		pass();
	}
	@Test(priority=119)
	void checkTotalPayableinCP() {
		start("Check total payable in CP");
		totalPayableinCP();
		pass();
	}
	@Test(priority=120)
	void navigateEazypay() throws InterruptedException {
		navigateToPayment();
	}
	@Test(priority=121)
	void checkprice() throws InterruptedException {
		start("Validate the payable price");
		paymentPrice();
		pass();
	}
	@Test(priority=122)
	void validatCan() throws InterruptedException {
		start("Cancel the payment Debitcard");
		canclpay();
		pass();
	}
	@Test(priority=123)
	void checkResMsg() throws IOException {
		start("Check Payment message when payment is fail");
		failpay();
		//check booking id
		//payment status
		//user capped limit
		pass();
	}
	@Test(priority=124)
	void checkbookingid() throws IOException {
		start("Check booking ID when payment is fail");
		bookingIDvalidtn();
		pass();
	}
	@Test(priority=125)
	void checkstatus() throws IOException {
		start("Check Payment status when payment is fail");
		checkStatus();
		pass();
	}
	@Test(priority=126)
	void checkaddress() throws IOException {
		start("Check Address when payment is fail");
		addressCheck();
		pass();
	}
	@Test(priority=127)
	void navigateHm() throws InterruptedException {
		home();
	}

	@Test(priority=128)
	void preset() throws InterruptedException, IOException {
		vali_stock();
		exeedlimit();
		zReset();
	}
	@Test(priority=129,dataProvider="cartDebit")
	void fillBookingForm2(String vehicle,String cap,String NoOfvehicle) throws InterruptedException, IOException {
		start("Fill the booking form");
		checkTotlcft(vehicle,Integer.parseInt(NoOfvehicle),Integer.parseInt(cap),"write");
		pass();
	}
	@DataProvider(name="cartDebit")
	public static Object[][] linitu() throws Exception{
		Object[][] data=ExcelData.getTableArray(".\\data\\BookingForm.xls", "limited", 3);
		return data;
	}
	@Test(priority=130)
	void checkAll() throws InterruptedException, IOException {
		start("Check calculations");
		checkQTY();
		validateprice();
		pass();
	}
	@Test(priority=131)
	void testaddcart() throws InterruptedException {
		start("Test assert");
		clickOnElement(objCart.ADDTOCARTbtn);
		String actual=getTextFromFirstEle(objCart.stockyardNameIncart);
		String expected=getStockyard();
		Assert.assertTrue(actual.equalsIgnoreCase(expected));
		pass();
	}
	@Test(priority=132)
	void testQTY() throws  InterruptedException {
		start("Test qty");
		int act=Integer.parseInt(getTextFromFirstEle(objCart.cartQty));
		int expec=getTotalQty();
		Assert.assertTrue(act==expec);
		pass();
	}
	@Test(priority=132)
	void testPrice() throws InterruptedException {
		start("Check price");
		double actua=Double.parseDouble(getTextFromFirstEle(objCart.cartPrice));
		double expecte=getTotalPayabl();
		Assert.assertTrue(actua==expecte);
		pass();
	}
	@Test(priority=133)
	void testA() {
		start("Click on buy");
		clickFirstele(objCart.buyBtn);
		pass();
	}
	@Test(priority=134)	 
	void RBwithoutLoca() throws InterruptedException, IOException{
		start("RBwithoutLoc");
		RBwithoutLoc();
		pass();
	}
	@Test(priority=135)	 
	void rBWithoutAltmoba() throws InterruptedException, IOException{
		start("rBWithoutAltmob");
		rBWithoutAltmob();
		pass();
	}
	@Test(priority=136)	 
	void rBInvalidMobilea() throws InterruptedException, IOException{
		start("rBInvalidMobile");
		rBInvalidMobile();
		pass();
	}
	@Test(priority=137)	 
	void validatewithValidRBq() throws InterruptedException, IOException{
		start("validatewithValidRB");
		validatewithValidRB();
		pass();
	}
	@Test(priority=138)	 
	void stockyardCPq(){
		start("stockyardCP");
		stockyardCP();
		pass();
	}
	@Test(priority=139)	 
	void unitPriceCPs(){
		start("unitPriceCP");
		unitPriceCP();
		pass();
	}
	@Test(priority=140)	
	void totalQTYCPe(){
		start("totalQTYCP");
		totalQTYCP();
		pass();
	}
	@Test(priority=141)	 
	void totalPriceinCPq(){
		start("totalPriceinCP");
		totalPriceinCP();
		pass();
	}
	@Test(priority=142)	 
	void gstinCPd(){
		start("gstinCP");
		gstinCP();
		pass();
	}
	@Test(priority=143)	 
	void totalPayableinCPg(){
		start("totalPayableinCP");
		totalPayableinCP();
		pass();
	}
	@Test(priority=144)	 
	void navigateToPayments() throws InterruptedException{
		start("navigateToPayment");
		navigPayPage();
		pass();
	}
	@Test(priority=145)	 
	void paymentPrices(){
		start("paymentPrice");
		paymentPrice();
		pass();
	}

	@Test(priority=146)
	void creditCncl() throws InterruptedException {
		start("Credit card cancel");
		clickOnElement(objPay.creditCard);
		clickdisplayedBtn(objPay.ProceedPaybtn);
		Thread.sleep(2000);
		clickOnElement(objPay.cancelPaybtn);
		pass();
	}
	@Test(priority=147)
	void afterCredit() throws IOException {
		start("Validation");
		failpay();
		bookingIDvalidtn();
		checkStatus();
		addressCheck();
		pass();

	}
	@Test(priority=148)
	void goHome() throws InterruptedException {
		start("Navigate to home");
		home();
		pass();
	}



}
