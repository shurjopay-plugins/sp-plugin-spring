package com.shurjopay.plugin;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shurjopay.plugin.model.PaymentReq;
import com.shurjopay.plugin.model.PaymentRes;
import com.shurjopay.plugin.model.VerifiedPayment;
/**
 * @author Al-Amin
 * @since 2022-10-16
 *
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Testing ShurjoPaySpring =>")
class ShurjoPaySpringTest { 

	@Autowired
	private Shurjopay shurjopay;

	private PaymentRes paymentRes;

	@Test
	@Order(1)
	@DisplayName("For making shurjoPay payment: ")
	void testMakePayment() {
		PaymentReq req = getPaymentReq();
		paymentRes = shurjopay.makePayment(req);
		assertNotNull(paymentRes.getPaymentUrl(), () -> "Making Payment returns null");
	}

	@Test
	@Order(2)
	@DisplayName("For verifying order: ")
	void testVerifyOrder() {
		VerifiedPayment order = shurjopay.verifyPayment(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	@Test
	@Order(3)
	@DisplayName("For checking order status: ")
	void testGetPaymentStatus() {
		VerifiedPayment order = shurjopay.checkPaymentStatus(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	private PaymentReq getPaymentReq() {
		PaymentReq request = new PaymentReq();

		request.setPrefix("sp");
		request.setAmount(10.00);
		request.setOrderId("sp315689");
		request.setCurrency("BDT");
		request.setCustomerName("Maharab kibria");
		request.setCustomerAddress("Dhaka");
		request.setCustomerPhone("01766666666");
		request.setCustomerCity("Dhaka");
		request.setCustomerPostCode("1212");
		request.setCustomerEmail("al@gmail.com");
		return request;
	}

}
