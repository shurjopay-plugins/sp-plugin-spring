![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png)

# shurjoPay plugin (SPRING)
[_**shurjoMukhi Limited**_](https://shurjomukhi.com.bd/) developed plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with spring application. _shurjoPay plugin_ helps merchants and service providers to integrate easily.<br/>
Plugin provides 2 features:
- **Make Payment**
- **Verify payment**
## Audience
This document is intended for the technical personnel of merchants and service providers who wants to integrate our online payment gateway using spring plugin provided by _**shurjoMukhi Limited**_.
## How to use shurjoPay spring plugin
You can download source from our [github source](https://github.com/shurjopay-plugins/sp-plugin-spring).
You can pull binary/jar from central Maven repositories:<br>
**Maven**
```xml
<dependency>
    <groupId>bd.com.shurjopay.plugin</groupId>
    <artifactId>sp-plugin-spring</artifactId>
    <version>1.1.0</version>
</dependency>
```
**Gradle**
```gradle
implementation 'bd.com.shurjopay.plugin:sp-plugin-spring:1.1.0'
```
> **_Attention:_ [_shurjoMukhi Limited_](https://shurjomukhi.com.bd/) offers another plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with <u>_Java Application_. Visit our [Java plugin](https://github.com/shurjopay-plugins/sp-plugin-java)**<hr>

Our sample projects with implementation of **spring plugin** are available. Please visit [Spring Project](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/spring-app-spring-plugin). <br/>
Developer needs to configure application.yml/application.properties & logback.xml file to use _shurjoPay_. Properties file contains four fields ``` username, password, shurjopay-api, sp-callback ``` to configure shurjoPay and 2 other fields to configure _shurjoPay_ logging are ```  path, name. ```
- Visit [_sample_](https://github.com/shurjopay-plugins/sp-plugin-spring/tree/develop/src/test/resources/sample-properties) for **application properties example.**
- Visit [_logback.xml_](https://github.com/shurjopay-plugins/sp-plugin-spring/blob/develop/src/test/resources/logback-sample.xml) for **logback.xml example.**
### Initialize shurjoPay:
Now time to initialize shurjoPay to perform with features. For that
```java
private @Autowired Shurjopay shurjopay;
```
Above code will be initialezed a _shurjoPay_ instance.
### Make Payment: 
After initializing developer should call _makePayment()_ method with payment request object parameter.
- **Example**
	- Request example
	 ```java 
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
	- Returns POJO corresponding this [_JSON_](https://github.com/shurjopay-plugins/sp-plugin-spring/blob/develop/src/test/resources/sample-msg/payment-res.json)

### Verify payment: 
Developer must call _verifyPayment()_ method after completing payment with shurjopay transaction id as a string param.
- **Example**
	- Call verify method
	 ```java
	 	shurjopay.verifyPayment(:=spTxnId)
	 ```
	- Returns POJO corresponding this [_JSON_](https://github.com/shurjopay-plugins/sp-plugin-spring/blob/develop/src/test/resources/sample-msg/verification-res.json)
## Want to see shurjoPay visually?
Run the spring unit test to see shurjopay plugin in action. These tests will run on selenium browser and will provide the complete experience. Just download [source](https://github.com/shurjopay-plugins/sp-plugin-spring) and run ```ShurjopayTest``` class.
Have a look to our other [shurjoPay plugins](https://github.com/shurjopay-plugins)
## License
This code is under the [MIT open source License](https://github.com/shurjopay-plugins/sp-plugin-spring/blob/develop/LICENSE).
#### Please [contact](https://shurjopay.com.bd/#contacts) with shurjoPay team for more detail!
<hr>
Copyright ©️2022 Shurjomukhi Limited.
