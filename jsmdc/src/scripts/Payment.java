package scripts;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import generic.ExcelData;
import generic.PropertyFileRead;
import pages.HomePage;
import pages.PaymentPage;

public class Payment extends ConsumerModel {
	PaymentPage objPay;
	@Test(priority=78)
	void navigateToPayment() throws InterruptedException {
		start("Navigate to online payment Gateway");
		navigPayPage();
		pass();
	}
	protected void navigPayPage() throws InterruptedException {
		// TODO Auto-generated method stub
		objHM=new HomePage(driver);
		objPay = new PaymentPage(driver);
		info("Click on cofirm and pay");
		clickOnElement(objHM.confirmPayBtn);
		if(isElementPresent(objHM.swtalrtAccept)){
			info("Alert: "+objHM.swtalrtText.getText());
			clickOnElement(objHM.swtalrtAccept);
		}
		Thread.sleep(2000);
		Assert.assertTrue(objPay.proceedPay.isDisplayed());
		info("Navigated to eazypay Payment Gateway");
	}
	@Test(priority=79)
	void validatePaymentPrice() throws InterruptedException {
		start("Validate the payable price");
		paymentPrice();
		pass();
	}
	protected void paymentPrice() {
		// TODO Auto-generated method stub
		double actPayablePrice=crct(objPay.onlinePayPrice.getText());
		info("Actual payable price: "+actPayablePrice);
		info("Expected Payable price:"+getTotalPayabl());
		Assert.assertEquals(actPayablePrice, getTotalPayabl());
	}
	//debit card
	@Test(priority=80)
	void cancelPayment() throws InterruptedException {
		start("Cancel the payment Debitcard");
		canclpay();
		pass();
	}
	protected void canclpay() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(2000);
		clickOnElement(objPay.debitRadio);
		clickdisplayedBtn(objPay.ProceedPaybtn);
		Thread.sleep(2000);
		clickOnElement(objPay.cancelPaybtn);
		Thread.sleep(2000);
	}
	@Test(description="once payment is fail",priority=81)
	void paymentFail() throws IOException {
		start("Check Payment message when payment is fail");
		failpay();
		//check booking id
		//payment status
		//user capped limit
		pass();
	}
	protected void failpay() throws IOException {
		// TODO Auto-generated method stub
		info("Actual: "+objPay.successMsg.getText().trim());
		Assert.assertFalse(objPay.successMsg.getText().trim().equals(PropertyFileRead.getProperty("paymentSuccessMsg")));
	}
	@Test(priority=82)
	void checkBookingid() throws IOException {
		start("Check booking ID when payment is fail");
		bookingIDvalidtn();
		pass();
	}
	protected void bookingIDvalidtn() throws IOException {
		// TODO Auto-generated method stub
		String act=objPay.bookingId.getText();
		info("Actual: "+act);
		String expec=PropertyFileRead.getProperty("bookingidNA");
		info("Expected: "+expec);
		Assert.assertTrue(act.equals(expec));
	}
	@Test(priority=83)
	void checkPaymentStatus() throws IOException {
		start("Check Payment status when payment is fail");
		checkStatus();
		pass();
	}
	protected void checkStatus() throws IOException {
		// TODO Auto-generated method stub
		String act=objPay.paymentStatus.getText();
		info("Actual: "+act);
		String expec=PropertyFileRead.getProperty("paymentStatusfail");
		info("Expected: "+expec);
		Assert.assertTrue(act.equals(expec));
	}
	@Test(priority=84)
	void checkAddress() throws IOException {
		start("Check Address when payment is fail");
		addressCheck();
		pass();
	}
	protected void addressCheck() {
		// TODO Auto-generated method stub
		String act=objPay.destinationAddress.getText();
		info("Actual: "+act);
		String expec=getDestAddress();
		info("Expected: "+expec);
		Assert.assertTrue(act.equals(expec));
	}
	@Test(priority=85)
	void navigateToHome() throws InterruptedException {
		start("Navigate to home page");
		home();
		pass();		
	}
	
	protected void home() throws InterruptedException {
		// TODO Auto-generated method stub
		clickOnElement(objPay.myaccOrders);
		info("lick on go to orders");
		clickOnElement(objPay.imglogos);
		info("Click on logo");
	}
	//navigate to payment page
	@Test(priority=85)
	void zBookingForm() throws InterruptedException, IOException {
		start("PreRequired");
		Thread.sleep(3000);
		vali_stock();
		pass();
	}
	@Test(priority=85)
	void zzRes() throws InterruptedException, IOException {
		start("Exeed limit");
		exeedlimit();
		zReset();
		pass();
	}
	@Test(priority=86,dataProvider="linit")
	void navigateToPaymentPage(String vehicle,String cap,String NoOfvehicle) throws InterruptedException, IOException {
		start("Fill the booking form");
		checkTotlcft(vehicle,Integer.parseInt(NoOfvehicle),Integer.parseInt(cap),"write");
		pass();
	}
	@Test(priority=86)
	void znavigateToPayPage() throws InterruptedException, IOException {
		start("Navigate to payment page");
		navigateRB();
		validatewithValidRB();
		navigPayPage();
		pass();
	}
	@Test(priority=87)
	void aCreditCard() throws IOException, InterruptedException {
		start("Credit card selection");
		clickOnElement(objPay.creditCard);
		clickdisplayedBtn(objPay.ProceedPaybtn);
		Thread.sleep(2000);
		clickOnElement(objPay.cancelPaybtn);
		Thread.sleep(2000);
		//paymentFail();
		bookingIDvalidtn();
		checkStatus();
		addressCheck();
		home();
		pass();
	}
//	@Test(priority=87,dataProvider="linit")
//	void checkCreditCardFail(String vehicle,String cap,String NoOfvehicle) throws InterruptedException, IOException {
//		start("Credit card cancel payment");
//		navigateToPaymentPage(vehicle,cap,NoOfvehicle);
//		pass();
//	}

	@DataProvider(name="linit")
	public static Object[][] linita() throws Exception{
		Object[][] data=ExcelData.getTableArray(".\\data\\BookingForm.xls", "limited", 3);
		return data;
	}
}