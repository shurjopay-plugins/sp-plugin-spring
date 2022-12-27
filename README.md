# ![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png) Spring Plugin

Official shurjoPay Java plugin for merchants or service providers to connect with [**_shurjoPay_**](https://shurjopay.com.bd) Payment Gateway ``` v2.1 ``` developed and maintained by [_**ShurjoMukhi Limited**_](https://shurjomukhi.com.bd).

This plugin can be used with any java application or framework (e.g. Spring).
Also it makes easy for developers to integrate with shurjoPay ``` v2.1 ``` with just three API calls:

1. **makePayment**: create and send payment request
1. **verifyPayment**: verify payment status at shurjoPay
1. **paymentStatus**: Check payment details and status

Also reduces many of the things that you had to do manually:

- Handles http request and errors.
- JSON serialization and deserialization.
- Authentication during initiating and verifying of payments.
## Audience
This document is intended for the technical personnel of merchants and service providers who wants to integrate our online payment gateway using spring plugin provided by _**shurjoMukhi Limited**_.
## How to use this shurjoPay plugin
To integrate the shurjoPay Payment Gateway in your Java project do the following tasks sequentially.
### Step 1: Add dependency to your project corresponding Maven or Gradle:<br>
**Maven**
```xml
<dependency>
    <groupId>com.shurjomukhi</groupId>
    <artifactId>sp-plugin-spring</artifactId>
    <version>1.1.0</version>
</dependency>
```
**Gradle**
```gradle
implementation 'com.shurjomukhi:sp-plugin-spring:1.1.0'
```
> **_Attention:_ [_shurjoMukhi Limited_](https://shurjomukhi.com.bd/) offers another plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with <u>_Java Application_. Visit our [Java plugin](https://github.com/shurjopay-plugins/sp-plugin-java)**<hr>

### Step 2: Add shurjoPay properties to application properties.
Properties contains four fields ``` username, password, shurjopay-api, sp-callback ``` to configure shurjoPay and 2 other fields to configure _shurjoPay_ logging are ``` path ``` and ``` name. ```
```yml
shurjopay:
  username: <merchant_username>
  password: <merchant_password>
  shurjopay-api: https://sandbox.shurjopayment.com/api/
  sp-callback: https://sandbox.shurjopayment.com/response
  logging:
    file:
      path: /var/log
      name: sp-plugin-spring.log
```
- Visit [_YML format_](https://github.com/shurjopay-plugins/sp-plugin-spring/blob/develop/src/test/resources/application-sample.yml) and [_properties format_](https://github.com/shurjopay-plugins/sp-plugin-spring/blob/develop/src/test/resources/application-sample.properties) for examples.
- If you add ```logback-classic``` dependency in your application then shurjoPay will maintain separate log file to track errors.
### Initialize shurjoPay:
Now time to initialize shurjoPay to perform with features. For that
```java
private @Autowired Shurjopay shurjopay;
```
Above code will be initialezed a _shurjoPay_ instance.
### Step 3: After that, you can initiate payment request to shurjoPay using below code example.
- Request example
 ```java 
	// Initialize shurjopay
	private @Autowired Shurjopay shurjopay;

	// Prepare payment request to initiate payment
	PaymentReq request = new PaymentReq();
	request.setPrefix("sp");
	request.setAmount(10.00);
	request.setCustomerOrderId("sp315689");
	request.setCurrency("BDT");
	request.setCustomerName("Dummy");
	request.setCustomerAddress("Dhaka");
	request.setCustomerPhone("01766666666");
	request.setCustomerCity("Dhaka");
	request.setCustomerPostCode("1212");
	request.setCustomerEmail("dummy@gmail.com");

	// Calls first method to initiate a payment
	shurjopay.makePayment(request);
 ```
- Returns [_POJO_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/main/java/com/shurjomukhi/model/PaymentRes.java) corresponding this [_JSON_](https://github.com/shurjopay-plugins/sp-plugin-spring/blob/develop/src/test/resources/sample-msg/payment-res.json)

### Step 4: Payment verification can be done after each transaction with shurjopay transaction id.
- Call verify method
 ```java
	shurjopay.verifyPayment(:=spTxnId)
 ```
- Returns [_POJO_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/main/java/com/shurjomukhi/model/VerifiedPayment.java) corresponding this [_JSON_](https://github.com/shurjopay-plugins/sp-plugin-spring/blob/develop/src/test/resources/sample-msg/verification-res.json)
## Want to see shurjoPay visually?
Run the spring unit test to see shurjopay plugin in action. These tests will run on selenium browser and will provide the complete experience. Just download source and run ```ShurjopayTest``` class.
## References
1. [Spring example application](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/spring-app-spring-plugin) showing usage of the spring plugin.
2. [Sample applications and projects](https://github.com/shurjopay-plugins/sp-plugin-usage-examples) in many different languages and frameworks showing shurjopay integration.
3. [shurjoPay Postman site](https://documenter.getpostman.com/view/6335853/U16dS8ig) illustrating the request and response flow using the sandbox system.
4. [shurjopay Plugins](https://github.com/shurjopay-plugins) home page on github
## License
This code is under the [MIT open source License](https://github.com/shurjopay-plugins/sp-plugin-spring/blob/develop/LICENSE).
#### Please [contact](https://shurjopay.com.bd/#contacts) with shurjoPay team for more detail!
<hr>
Copyright ©️2022 Shurjomukhi Limited.
