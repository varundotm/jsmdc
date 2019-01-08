package scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.ExcelData;
import generic.PropertyFileRead;
import pages.HomePage;

public class ConsumerModel extends Login{
	HomePage objHM;
	static int totalQty;
	String district, stockyard,destAddress,altMobile;
	static double unitPriceset,totalPrice,gstTax,totalPay;
	@Test(enabled=false)
	public void notifyBrowse() throws InterruptedException, IOException {
		start("Test");
		alerts("Please update the User name and mobile number before start registration, ignore if already updated.");
		Thread.sleep(5000);
		asserAlert("notifyrUN");
		pass();
	}
	//------------------Test Start----------------

	@Test(priority=47)
	void validateLocationTypoHead() throws InterruptedException {
		start("Validate the location typo head");
		objHM=new HomePage(driver);
		comboBox(objHM.comboAddress, "Ranchi");
		info("Loction Typohead: "+objHM.comboAddress.getAttribute("value"));
		//assert map navigation
		pass();
	}

	@Test(priority=47)
	void validateWithoutDistrict() throws InterruptedException, IOException {
		start("Validate Buy Now without choosing district");
		clickOnElement(objHM.buynowBtn);
		info("Click on Buy Now");
		asserAlert("alertDistrictBN");
		pass();
	}
	@Test(priority=48)
	void validateWithoutStoskYard() throws InterruptedException, IOException {
		start("Validate Buy Now witout choosing stockyard");
		comboInputBox(objHM.comboDistrict, "ranch");
		info("District: Ranchi ");
		clickOnElement(objHM.buynowBtn);
		info("Click on Buy Now");
		asserAlert("alertStockyrdBN");
		pass();
	}
	@Test(priority=49)
	void validateWithoutQTY() throws InterruptedException, IOException {
		start("Validate Buy Now witout choosing stockyard");
		selectDistrictAndstackyard();
		clickOnElement(objHM.buynowBtn);
		info("Click on Buy Now");
		asserAlert("alertQtyBN");
		pass();
	}

	@Test(priority=50)
	public void validateDistrict() {
		start("Validate District values");
		ArrayList<WebElement> districtValues= getAllOptions(objHM.selectDistrict);
		for(WebElement ele:districtValues) {
			info("District: "+ele.getText());
		}			
		Set<String> duplicateValues= findDuplicates((java.util.List<WebElement>) districtValues);
		if(!duplicateValues.isEmpty()) {
			for(String ele:duplicateValues) {
				info("Duplicate District: "+ele);
			}	
		}
		pass();
	}

	@Test(priority=51)
	public void validateStockyard() throws InterruptedException {
		start("Validate Stockyard values");
		int i=0;
		while(i<5) {
			comboInputBox(objHM.comboDistrict, "giri");
			comboInputBox(objHM.comboDistrict, "ranch");
			i++;}
		new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(objHM.comboStockyard));
		ArrayList<WebElement> stockyardValues= getAllOptions(objHM.selectStockyard);
		for(WebElement ele:stockyardValues) {
			info("Stockyard: "+ele.getText());
		}			
		Set<String> duplicateValues= findDuplicates((java.util.List<WebElement>) stockyardValues);
		if(!duplicateValues.isEmpty()) {
			info("Stockyard is displaying duplicate values");
			for(String ele:duplicateValues) {
				info("Duplicate Stockyard: "+ele);
			}
			fail();
		}else {
			pass();}
	}
	@Test(priority=52)
	public void validateAvailableStock_and_uniPrice() throws InterruptedException {
		start("Validate Available stock");
		vali_stock();
		pass();
	}


	protected void vali_stock() throws InterruptedException {
		Thread.sleep(3000);
		String beforeSelectSYAS=objHM.availableStock.getText();
		info("Available Stock Before Select Stockyard: "+beforeSelectSYAS);
		String beforeSelectSYPrice=objHM.unitPrice.getText();
		info("Unit price Before Select Stockyard: "+beforeSelectSYPrice);
		//comboInputBox(objHM.comboDistrict, "ranch");
		//setDistrict(objHM.comboDistrict.getText());
		//new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(objHM.comboStockyard));
		//comboInputBox(objHM.comboStockyard, "abc");
		//setStockyard(objHM.comboStockyard.getText());
		selectDistrictAndstackyard();
		setUnitPrice(crct(objHM.unitPrice.getText()));
		if(!(Integer.parseInt(objHM.availableStock.getText())>0)) {
			info("*******NO STOCK AVAILABLE IN STOCKYARD********");
			alerts("NO STOCK AVAILABLE IN STOCKYARD");
			fail();
		}
		String afterSelectSY=objHM.availableStock.getText();
		info("Available Stock After Select Stockyard: "+afterSelectSY);
		String afterSelectSYPrice=objHM.unitPrice.getText();
		info("Unit price After Select Stockyard: "+afterSelectSYPrice);
	}


	@Test(priority=53,dataProvider="vehicle")
	public void valdtBookingForm(String vehicle,String cap,String NoOfvehicle) throws InterruptedException, IOException  {
		start("Validate No of vehicle and total CFT ");
		checkTotlcft(vehicle,Integer.parseInt(NoOfvehicle),Integer.parseInt(cap),"write");		
		pass();
	}
	@DataProvider(name="vehicle")
	public static Object[][] vehicleInfor() throws Exception{
		Object[][] data=ExcelData.getTableArray(".\\data\\BookingForm.xls", "vehicle", 3);
		return data;
	}
	@DataProvider(name="limit")
	public static Object[][] limited() throws Exception{
		Object[][] data=ExcelData.getTableArray(".\\data\\BookingForm.xls", "limited", 3);
		return data;
	}
	@Test(priority=54)
	public void checktotalQty() {
		start("Validate total Quantity");
		checkQTY();
		pass();
	}
	protected void checkQTY() {
		objHM.setGSTVal();
		int actTotalQty=Integer.parseInt(objHM.totalQty.getAttribute("value"));
		info("Actual total Quantity: "+actTotalQty);
		int expecTotalQTY=getTotalQty();
		info("Expected total quantity: "+expecTotalQTY);
		Assert.assertEquals(actTotalQty, expecTotalQTY);
	}


	@Test(priority=54)
	void dValidatePrice(){
		start("Validate Total Price");
		validateprice();
		pass();
	}
	protected void validateprice() {
		double actualTotlPrice=crct(objHM.totalPrice.getText());
		double actualGst=crct(objHM.gstVal.getText());
		double actualPayableprice=crct(objHM.totalPayable.getText());
		double expecTotlPrice=(getTotalQty())*((getUnitPrice())/100);
		double gstPercentage=(objHM.getGSTval())/100;
		double expecGst=(expecTotlPrice*gstPercentage);
		expecGst = (double) Math.round(expecGst * 100) / 100;
		double expecPayable=expecTotlPrice+expecGst;
		info("Actual Total price: "+actualTotlPrice);
		info("Expected Total price: "+expecTotlPrice);
		setTotalPrice(expecTotlPrice);
		info("Actual GST: "+actualGst);
		info("Expected GST: "+expecGst);
		setGST(expecGst);
		info("Actual Payable price: "+actualPayableprice);
		info("Expected Payable: "+expecPayable);
		setTotalPayabl(expecPayable);
		Assert.assertEquals(actualTotlPrice, expecTotlPrice);
		Assert.assertEquals(actualGst, expecGst);
		Assert.assertEquals(actualPayableprice, expecPayable);
	}


	@Test(priority=54)
	void exeedUserlimit() throws InterruptedException, IOException {
		start("Validate the user capability");
		exeedlimit();
		pass();
	}
	protected void exeedlimit() throws InterruptedException, IOException {
		int TotalQTY=getTotalQty();
		info("User selected Quantity: "+TotalQTY);
		getfromDB(getUserName());
		int LimitQty= getCappedlimit();
		info("Users cap limit: "+LimitQty);
		if(LimitQty<100) {
			info("User exceed the limit of capability");
			info("Click on Buy Now");
			clickOnElement(objHM.buynowBtn);
			asserAlert("exeedUserLimit");
			Assert.assertTrue(false);
		}
	}


	@Test(priority=54,dependsOnMethods="exeedUserlimit")
	void zReset() {
		//unitPriceset=0;
		//System.out.println(" Before totalPrice: "+totalPrice+" gstTax: "+gstTax+" totalPay: "+totalPay+" totalQty: "+totalQty);
		totalPrice=0;
		gstTax=0;
		totalPay=0;
		totalQty=0;
		//System.out.println(" After totalPrice: "+totalPrice+" gstTax: "+gstTax+" totalPay: "+totalPay+" totalQty: "+totalQty);
	}
	@Test(priority=55,dataProvider="limit",dependsOnMethods="exeedUserlimit")
	public void vlidtBookFrm(String vehicle,String cap,String NoOfvehicle) throws InterruptedException, IOException {
		start("Validate with limited quantity");
		checkTotlcft(vehicle,Integer.parseInt(NoOfvehicle),Integer.parseInt(cap),"write");
		pass();
	}
	@Test(priority=56)
	void aCheckPrices() throws InterruptedException {
		start("Check prices for limited quantity");
		Thread.sleep(2000);
		validateprice();
		pass();
	}
	@Test(priority=56)
	void aCheckQty() throws InterruptedException {
		start("Check Qty for limited quantity");
		Thread.sleep(2000);
		checkQTY();
		pass();
	}
	@Test(priority=56)
	void navigateToReviewBooking() throws InterruptedException, IOException {
		start("Navigate to Review booking");
		navigateRB();
		pass();
	}

	protected void navigateRB() throws InterruptedException {

		info("Click on Buy Now");
		clickOnElement(objHM.buynowBtn);
		if(isSwtalertPresent()){
			info("Alert: "+objHM.swtalrtText.getText());
			clickOnElement(objHM.swtalrtAccept);
		}else {
			Thread.sleep(500);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", objHM.reviewBookingbtn);
			Assert.assertTrue(objHM.reviewBookingbtn.isDisplayed());
		}
	}


	@Test(priority=57)
	void validateRBWithoutLocation() throws InterruptedException, IOException {
		start("Validate Review booking without enter location");
		RBwithoutLoc();
		pass();
	}
	protected void RBwithoutLoc() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		clickOnElement(objHM.reviewBookingbtn);
		info("Click on Review booking");
		asserAlert("alertAddressRB");
	}

	@Test(priority=58)
	void validateRBWithoutAltnatMobile() throws InterruptedException, IOException {
		start("Validate Review booking without Alternate mobile number");
		rBWithoutAltmob();
		pass();
	}
	protected void rBWithoutAltmob() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		comboBox(objHM.comboAddress, "1");
		info("Destination Address: "+objHM.comboAddress.getAttribute("value"));
		clickOnElement(objHM.reviewBookingbtn);
		info("Click on Review booking");
		asserAlert("alertPhoneRB");
	}

	@Test(priority=59)
	void validateRBInvalidAltnatMobile() throws InterruptedException, IOException {
		start("Validate Review booking with Invalid Alternate mobile number");
		rBInvalidMobile();
		pass();
	}
	protected void rBInvalidMobile() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		comboBox(objHM.comboAddress, "1");
		info("Destination Address: "+objHM.comboAddress.getAttribute("value"));
		setDestAddress(objHM.comboAddress.getAttribute("value"));
		sendKey(objHM.altPhone, PropertyFileRead.getProperty("InvalidMobileNo"));
		info("Alternate Mobile number: "+objHM.altPhone.getAttribute("value"));
		setAltMobil(objHM.altPhone.getAttribute("value"));
		clickOnElement(objHM.reviewBookingbtn);
		info("Click on Review booking");
		asserAlert("alertinvalidMobileRB");
	}

	@Test(priority=60)
	void validateRBWithValidData() throws InterruptedException, IOException {
		start("Validate Review booking with Valid data");
		validatewithValidRB();
		pass();
	}
	protected void validatewithValidRB() throws InterruptedException, IOException {

		comboBox(objHM.comboAddress, "1");
		info("Destination Address: "+objHM.comboAddress.getAttribute("value"));
		setDestAddress(objHM.comboAddress.getAttribute("value"));
		sendKey(objHM.altPhone, PropertyFileRead.getProperty("ValidMobileNo"));
		info("Alternate Mobile number: "+objHM.altPhone.getAttribute("value"));
		setAltMobil(objHM.altPhone.getAttribute("value"));
		clickOnElement(objHM.reviewBookingbtn);
		info("Click on Review booking");
		info("Navigate to Confirm oreder and Pay");
		Assert.assertTrue(objHM.confirmPayBtn.isDisplayed());
	}


	@Test(priority=61)
	void validateStockYardinCP() {
		start("Validate Stockyard in Confirm page");
		stockyardCP();
		pass();
	}
	protected void stockyardCP() {
		// TODO Auto-generated method stub
		info("Actual Stockyard: "+objHM.stockyardCP.getText());
		info("Expected Stockyard: "+getStockyard());
		Assert.assertEquals(objHM.stockyardCP.getText(), getStockyard());
	}

	@Test(priority=62)
	void validateUnitPriceinCP() {
		start("Validate Unit price in Confirm page");
		unitPriceCP();
		pass();
	}
	protected void unitPriceCP() {
		// TODO Auto-generated method stub
		double unitprice=crct(objHM.unitPriceCP.getText());
		info("Actual Unit price: "+ unitprice);
		info("Expected Unit price: "+getUnitPrice()/100);
		Assert.assertEquals(unitprice,getUnitPrice()/100);
	}

	@Test(priority=63)
	void validateTotalQtyinCP() {
		start("Validate Total Quantity in Confirm page");
		totalQTYCP();
		pass();
	}
	protected void totalQTYCP() {
		// TODO Auto-generated method stub
		info("Actual Total Quantity: "+objHM.quantityCP.getText());
		info("Expected Total Quantity: "+getTotalQty());
		Assert.assertEquals(Integer.parseInt(objHM.quantityCP.getText()), getTotalQty());
	}

	@Test(priority=64)
	void validateTotalPriceinCP() {
		start("Validate Total Price in Confirm page");
		totalPriceinCP();
		pass();
	}
	protected void totalPriceinCP() {
		// TODO Auto-generated method stub
		info("Actual Total Price: "+objHM.priceCP.getText());
		info("Expected Total Price: "+getTotalPrice());
		Assert.assertEquals(Double.parseDouble(objHM.priceCP.getText()), getTotalPrice());
	}

	@Test(priority=65)
	void validateGstinCP() {
		start("Validate GST value in Confirm page");
		gstinCP();
		pass();
	}
	protected void gstinCP() {
		// TODO Auto-generated method stub
		info("Actual GST Value: "+objHM.gstCP.getText());
		info("Expected GST value: "+getGST());
		Assert.assertEquals(Double.parseDouble(objHM.gstCP.getText()), getGST());
	}
	@Test(priority=66)
	void validateTotalPayableinCP() {
		start("Validate Total payable value in Confirm page");
		totalPayableinCP();
		pass();
	}
	protected void totalPayableinCP() {
		// TODO Auto-generated method stub
		info("Actual Total payable Value: "+objHM.totalPricePayCP.getText());
		info("Expected Total payable value: "+getTotalPayabl());
		Assert.assertEquals(Double.parseDouble(objHM.totalPricePayCP.getText()), getTotalPayabl());
	}

	@Test(priority=67)
	void checkReviewBookingBackward() {
		start("Navigate to Backward to Check existing destination address");
		driver.navigate().back();
		String actualAddress=objHM.comboAddress.getAttribute("value");
		info("Actual destination address: "+actualAddress);
		info("Expected destination address: "+getDestAddress());
		Assert.assertEquals(actualAddress, getDestAddress());
		pass();
	}
	@Test(priority=68)
	void checkalternateMobileBackward() {
		start("Check existing alternate mobile address");
		String actualMobile=objHM.altPhone.getAttribute("value");
		info("Actual destination address: "+actualMobile);
		info("Expected destination address: "+getAltMobil());
		driver.navigate().back();
		Assert.assertEquals(actualMobile, getAltMobil());
		pass();
	}
	@Test(priority=69,dataProvider="limit")
	void checkBookFormBackward(String vehicle,String cap,String NoOfvehicle) throws InterruptedException, IOException {
		start("Navigate to back to check booking form existing value");
		Thread.sleep(2000);
		checkTotlcft(vehicle,Integer.parseInt(NoOfvehicle),Integer.parseInt(cap),"read");
		pass();
	}
	@Test(priority=70)
	void  verifyTotalQTY() {
		start("Navigate to back to verify total quantity");
		int actTotalQty=Integer.parseInt(objHM.totalQty.getAttribute("value"));
		info("Actual total Quantity: "+actTotalQty);
		int expecTotalQTY=getTotalQty();
		info("Expected total quantity: "+expecTotalQTY);
		Assert.assertEquals(actTotalQty, expecTotalQTY);
		pass();
	}
	@Test(priority=71)
	void verifyPrice(){
		start("Navigate to back to verify Total Price, GST, Payable");
		double actualTotlPrice=crct(objHM.totalPrice.getText());
		double actualGst=crct(objHM.gstVal.getText());
		double actualPayableprice=crct(objHM.totalPayable.getText());
		double expecTotlPrice=(getTotalQty())*((getUnitPrice())/100);
		double gstPercentage=(objHM.getGSTval())/100;
		double expecGst=expecTotlPrice*(gstPercentage);
		double expecPayable=expecTotlPrice+expecGst;
		info("Actual Total price: "+actualTotlPrice);
		info("Expected Total price: "+expecTotlPrice);
		info("Actual GST: "+actualGst);
		info("Expected GST: "+expecGst);
		info("Actual Payable price: "+actualPayableprice);
		info("Expected Payable: "+expecPayable);
		Assert.assertEquals(actualTotlPrice, expecTotlPrice);
		Assert.assertEquals(actualGst, expecGst);
		Assert.assertEquals(actualPayableprice, expecPayable);
		pass();
	}
	@Test(priority=72)
	void forwardtoConfirmPage() throws InterruptedException {
		start("Navigate forward to confirm page");
		driver.navigate().forward();
		driver.navigate().forward();
		Thread.sleep(2000);
		Assert.assertTrue(objHM.confirmPayBtn.isDisplayed());
		pass();
	}
	@Test(priority=73,enabled=false)
	void aCheckDistrictEdit() throws InterruptedException {
		start("check existing district in edit");
		clickOnElement(objHM.editBtn);
		info("Click on Edit icon in Confirm page");
		Thread.sleep(2000);
		assertActExpect(objHM.comboDistrict.getText(),getDistrict());
		pass();

	}
	@Test(priority=73,enabled=false)
	void aCheckStockyardEdit() {
		start("check existing stockyard in edit");
		assertActExpect(objHM.comboStockyard.getText(),getStockyard());
		pass();

	}
	@Test(priority=73,dataProvider="limit",enabled=false)
	void checkEditExistingVal(String vehicle,String cap,String NoOfvehicle) throws InterruptedException, IOException {
		start("Check edit is displaying existing booking form values");
		checkTotlcft(vehicle,Integer.parseInt(NoOfvehicle),Integer.parseInt(cap),"read");
		pass();
	}
	@Test(priority=73,enabled=false)
	void selectStockEdit() throws InterruptedException {
		selectDistrictAndstackyard();
		totalQty=0;
		setUnitPrice(crct(objHM.unitPrice.getText()));
	}
	@Test(priority=74,dataProvider="limit",enabled=false)
	void checkEditVal(String vehicle,String cap,String NoOfvehicle) throws InterruptedException, IOException {
		start("Check edit booking form values");
		checkTotlcft(vehicle,Integer.parseInt(NoOfvehicle),Integer.parseInt(cap),"write");
		pass();
	}
	@Test(priority=75,enabled=false)
	void  validtTotalQTYedit() {
		start("verify total quantity in edit");
		int actTotalQty=Integer.parseInt(objHM.totalQty.getAttribute("value"));
		info("Actual total Quantity: "+actTotalQty);
		int expecTotalQTY=getTotalQty();
		info("Expected total quantity: "+expecTotalQTY);
		Assert.assertEquals(actTotalQty, expecTotalQTY);
		pass();
	}
	@Test(priority=76,enabled=false)
	void verifyPriceEdit(){
		start("verify Total Price, GST, Payable in edit");
		double actualTotlPrice=crct(objHM.totalPrice.getText());
		double actualGst=crct(objHM.gstVal.getText());
		double actualPayableprice=crct(objHM.totalPayable.getText());
		double expecTotlPrice=(getTotalQty())*((getUnitPrice())/100);
		double expecGst=(expecTotlPrice*(objHM.getGSTval()/100));
		double expecPayable=expecTotlPrice+expecGst;
		info("Actual Total price: "+actualTotlPrice);
		info("Expected Total price: "+expecTotlPrice);
		info("Actual GST: "+actualGst);
		info("Expected GST: "+expecGst);
		info("Actual Payable price: "+actualPayableprice);
		info("Expected Payable: "+expecPayable);
		Assert.assertEquals(actualTotlPrice, expecTotlPrice);
		Assert.assertEquals(actualGst, expecGst);
		Assert.assertEquals(actualPayableprice, expecPayable);
		pass();
	}
	@Test(priority=77,enabled=false)
	void navigateToConfirmPay() throws InterruptedException {
		start("Navigate to confirm&pay page");
		driver.navigate().back();
		Thread.sleep(2000);
		Assert.assertTrue(objHM.confirmPayBtn.isDisplayed());
	}



	//-------------------Test End-------------------


	//-------------------Supported methods----------
	public void checkTotlcft(String vehicle, int NoOfvehicle,int cap,String readOrWrite) throws InterruptedException, IOException {
		if("Tractor".equalsIgnoreCase(vehicle)) {
			asserCft(vehicle,cap, NoOfvehicle,objHM.tractorQty,objHM.tractorTotal,readOrWrite);
		}else if("Mini Haiwa".equalsIgnoreCase(vehicle)) {
			asserCft(vehicle,cap, NoOfvehicle,objHM.miniHaiwaQty,objHM.miniHaiwaTotal,readOrWrite);
		}else if("Truck-6".equalsIgnoreCase(vehicle)) {
			asserCft(vehicle,cap, NoOfvehicle,objHM.truck6Qty,objHM.truck6Total,readOrWrite);
		}else if("Truck-10".equalsIgnoreCase(vehicle)) {
			asserCft(vehicle,cap, NoOfvehicle,objHM.truck10Qty,objHM.truck10Total,readOrWrite);
		}else if("Truck-12".equalsIgnoreCase(vehicle)) {
			asserCft(vehicle,cap, NoOfvehicle,objHM.truck12Qty,objHM.truck12Total,readOrWrite);
		}else if("TruckTrailor-14".equalsIgnoreCase(vehicle)) {
			asserCft(vehicle,cap, NoOfvehicle,objHM.truckTrailor14Qty,objHM.truckTrailor14Total,readOrWrite);
		}else if("Truck-14".equalsIgnoreCase(vehicle)) {
			asserCft(vehicle,cap, NoOfvehicle,objHM.truck14Qty,objHM.truck14Total,readOrWrite);
		}else if("Truck-18".equalsIgnoreCase(vehicle)) {
			asserCft(vehicle,cap, NoOfvehicle,objHM.truck18Qty,objHM.truck18Total,readOrWrite);
		}else if("Truck-22".equalsIgnoreCase(vehicle)) {
			asserCft(vehicle,cap, NoOfvehicle,objHM.truck22Qty,objHM.truck22Total,readOrWrite);
		}else {}
	}
	public void asserCft(String vehicle,int cap, int NoOfvehicle,WebElement element1,WebElement element2,String readOrWrite) throws InterruptedException, IOException {
		if(readOrWrite.equals("write")) {
			int expcTotalCft;
			int actuTotalcft;
			int availablestock=Integer.parseInt(objHM.availableStock.getText());
			info("Vehicle: "+vehicle);
			info("Capacity: "+cap);
			expcTotalCft=cap*NoOfvehicle;
			info("No. Of Vehicles: "+NoOfvehicle);
			setTotalQty(expcTotalCft);
			element1.clear();
			Thread.sleep(200);
			if(isElementPresent(objHM.swtalrtAccept)){
				info("Alert: "+objHM.swtalrtText.getText());
				clickOnElement(objHM.swtalrtAccept);
				Thread.sleep(100);
			}
			element1.sendKeys(new Integer(NoOfvehicle).toString());
			element1.sendKeys(Keys.ENTER);
			if(totalQty>availablestock||isSwtalertPresent()){
				asserAlert("alertExeed");
				totalQty=totalQty-expcTotalCft;
			}
			actuTotalcft=Integer.parseInt(element2.getText());
			info("Actual total CFT: "+actuTotalcft);
			info("Expected total CFT: "+expcTotalCft);
			Assert.assertTrue(expcTotalCft==actuTotalcft);
		}else {		
			int expcTotalCft;
			int actuTotalcft;
			int expecNoOfVehicl=NoOfvehicle;
			int actualNoOfVehicle;
			info("Vehicle: "+vehicle);
			info("Capacity: "+cap);
			expcTotalCft=cap*NoOfvehicle;
			info("No. Of Vehicles: "+NoOfvehicle);
			actualNoOfVehicle=Integer.parseInt(element1.getAttribute("value"));
			actuTotalcft=Integer.parseInt(element2.getText());
			info("Actual total CFT: "+actuTotalcft);
			info("Expected total CFT: "+expcTotalCft);
			Assert.assertTrue(expecNoOfVehicl==actualNoOfVehicle);
			Assert.assertTrue(expcTotalCft==actuTotalcft);
		}
	}

	public double crct(String input) {
		double output;
		if(input.contains("Rs")) {
			String[] splits=input.split("Rs. ");
			output=Double.parseDouble(splits[1]);
		}else {
			output=Double.parseDouble(input);
		}
		return output;	

	}
	public void setTotalQty(int expcTotalCft) throws InterruptedException, IOException {
		ConsumerModel.totalQty=ConsumerModel.totalQty+expcTotalCft;
		//System.out.println("set total QTY: "+ConsumerModel.totalQty);

	}
	public int getTotalQty() {
		//System.out.println("Get total Qty: "+ConsumerModel.totalQty);
		return ConsumerModel.totalQty;
	}

	public void setDistrict(String district) {
		this.district=district;
	}
	public void setStockyard(String stockyard) {
		this.stockyard=stockyard;
	}
	public void setDestAddress(String destAddress) {
		this.destAddress=destAddress;
	}
	public void setAltMobil(String altMobile) {
		this.altMobile=altMobile;
	}
	public void setUnitPrice(double unitPriceset) {
		ConsumerModel.unitPriceset=unitPriceset;
	}
	public void setGST(double gstTax) {
		ConsumerModel.gstTax=gstTax;
		//System.out.println("Set cons gst: "+ConsumerModel.gstTax);
	}
	public void setTotalPrice(double totalPrice) {
		ConsumerModel.totalPrice=totalPrice;
	}
	public void setTotalPayabl(double totalPay) {
		ConsumerModel.totalPay=totalPay;
	}
	public String getDistrict() {			
		return district;		
	}			
	public String getStockyard() {			
		return stockyard;		
	}			
	public String getDestAddress() {			
		return destAddress;		
	}			
	public String getAltMobil() {			
		return altMobile;		
	}			
	double getUnitPrice() {			
		return unitPriceset;		
	}			
	public double getGST() {	
		//System.out.println("Get cons gst: "+gstTax);
		return gstTax;
	}			
	public double getTotalPrice() {			
		return totalPrice;		
	}			
	public double getTotalPayabl() {			
		return totalPay;		
	}
	public void selectDistrictAndstackyard() throws InterruptedException{
		ArrayList<WebElement> districtValues= getAllOptions(objHM.selectDistrict);
		for(WebElement ele:districtValues) {
			info("District: "+ele.getText());
			comboInputBox(objHM.comboDistrict, ele.getText());
			if(objHM.comboDistrict.getText().contains("Select")) {
				continue;
			}
			new WebDriverWait(driver, 15).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(objHM.comboStockyard));
			ArrayList<WebElement> stockyardValues= getAllOptions(objHM.selectStockyard);
			for(WebElement ele1:stockyardValues) {
				info("Stockyard: "+ele1.getText());
				comboInputBox(objHM.comboStockyard, ele1.getText());
				if(Integer.parseInt(objHM.availableStock.getText())>0&&!objHM.comboStockyard.getText().isEmpty()&&!objHM.comboStockyard.getText().contains("Select")){
					if(isElementPresent(objHM.swtalrtAccept)) {
						info("Alert: "+objHM.swtalrtText.getText());
						clickOnElement(objHM.swtalrtAccept);
					}
					setStockyard(objHM.comboStockyard.getText());
					break;
				}}if(Integer.parseInt(objHM.availableStock.getText())>0&&!objHM.comboDistrict.getText().contains("Select")){
					setDistrict(objHM.comboDistrict.getText());
					break;}
		}	
	}

}