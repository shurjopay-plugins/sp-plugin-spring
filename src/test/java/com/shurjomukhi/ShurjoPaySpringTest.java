package com.shurjomukhi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shurjomukhi.model.PaymentReq;
import com.shurjomukhi.model.PaymentRes;
import com.shurjomukhi.model.VerifiedPayment;
/**
 * @author Al-Amin
 * @since 2022-10-16
 *
 */
@SpringBootTest(classes = {ShurjopaySpringConfig.class})
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Testing ShurjoPaySpring =>")
class ShurjoPaySpringTest { 

	@Autowired
	private Shurjopay shurjopay;

	private PaymentRes paymentRes;

	@Test
	@Order(1)
	@DisplayName("For making shurjoPay payment (Success payment test): ")
	void testMakePayment() throws InterruptedException, ShurjopayException {
		PaymentReq req = getPaymentReq();
		paymentRes = shurjopay.makePayment(req);
		String paymentUrl = paymentRes.getPaymentUrl();
		fillupAndSubmitPaymentForm(paymentUrl, false);
		assertNotNull(paymentUrl, () -> "Making Payment returns null");
	}

	@Test
	@Order(2)
	@DisplayName("For verifying order (Success payment test): ")
	void testVerifyOrder() throws ShurjopayException {
		VerifiedPayment order = shurjopay.verifyPayment(paymentRes.getSpTxnId());
		assertNotNull(order.getSpTxnId(), () -> "Order is not found.");
	}
	
	@Test
	@Order(3)
	@DisplayName("For making shurjoPay payment (Failed payment test): ")
	void testMakePaymentFailed() throws ShurjopayException, InterruptedException {
		PaymentReq req = getPaymentReq();
		paymentRes = shurjopay.makePayment(req);
		String paymentUrl = paymentRes.getPaymentUrl();

		fillupAndSubmitPaymentForm(paymentUrl, true);
		assertNotNull(paymentUrl, () -> "Making Payment returns null");
	}

	@Test
	@Order(4)
	@DisplayName("For verifying order (Failed payment test): ")
	void testVerifyOrderFailed() {
		Throwable exception = assertThrows(ShurjopayException.class, () -> shurjopay.verifyPayment(paymentRes.getSpTxnId()));
		assertEquals("Code: 1005 Message: Channel Unavailable", exception.getMessage());
	}
	
	private PaymentReq getPaymentReq() {
		PaymentReq request = new PaymentReq();

		request.setPrefix("dummy");
		request.setAmount(10.00);
		request.setCustomerOrderId("sp315689");
		request.setCurrency("BDT");
		request.setCustomerName("Dummy");
		request.setCustomerAddress("Dhaka");
		request.setCustomerPhone("01766666666");
		request.setCustomerCity("Dhaka");
		request.setCustomerPostCode("1212");
		request.setCustomerEmail("al@gmail.com");
		return request;
	}
	
	private void fillupAndSubmitPaymentForm(String url, boolean shouldFail) throws InterruptedException {
		WebDriver driver = getChrome();
		driver.get(url);
		Thread.sleep(3 * 1000);
		
		/** Fills up card number */
		WebElement webEl = driver.findElement(By.id("input-38"));
		webEl.sendKeys("444444444444");
		
		/** Fills up card expire month */
		webEl = driver.findElement(By.id("mm"));
		webEl.sendKeys("12");
		
		/** Fills up expire year */
		webEl = driver.findElement(By.id("yyyy"));
		webEl.sendKeys("2024");
		
		/** Fills up cvv */
		webEl = driver.findElement(By.id("cvc_cvv"));
		webEl.sendKeys("123");
		
		/** Fills up card holder name */
		webEl = driver.findElement(By.id("card_holder_name"));
		webEl.sendKeys("Robot");
		
		/** click index 1 for SUCCESS or index 0 for FAILED button */
		webEl = shouldFail ? driver.findElements(By.className("paynow_btn")).get(0) : driver.findElements(By.className("paynow_btn")).get(1);;
		webEl.click();
		
		Thread.sleep(2 * 1000);
		driver.quit();
	}
	
	/**
	 * Prepares chrome driver instance
	 *
	 * @return prepared chrome driver.
	 */
	private WebDriver getChrome() {

		String osName = System.getProperty("os.name");
		String chromeDriver = osName.contains("Windows") ? "chromedriver.exe" : osName.contains("Linux") ? "chromedriver" : "chromedriver_mac";
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/".concat(chromeDriver));

		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		options.addArguments("--remote-allow-origins=*");
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		return driver;
	}
}
