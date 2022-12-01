![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png)

# ShurjoPay Online Payment API Integration:
This document has been prepared by shurjoMukhi Limited to enable the online merchants to integrate shurjoPay payment gateway. The information contained in this document is proprietary and confidential to shurjoMukhi Limited, for the product **_shurjoPay_**.
### Audience
This document is intended for the technical personnel of merchants and service providers that want to integrate a new online payment gateway using java plugin provided by **_shurjoPay_**.
### Prerequisite
	◼️ shurjoPay Version 2.0
## Integration
**_shurjoPay_** Online payment gateway has several APIs which need to be integrated by merchants for accessing different services.
The available services are:
- Authenticate users
- Making payment
- Verifying payment order
- Checking verified payment order status
## shurjoPay plugin (SPRING)
_ShurjoMukhi Limited_ has developed plugin for integrating **_shurjoPay_** with spring application. _shurjoPay plugin_ helps merchants and service providers to integrate easity by using this plugin. Plugin provides 3 services mainly such as
- **Make Payment**
- **Verify payment order**
- **Check verified order status**
## How to implement
### Before All:
First of all developer have to add shurjopay configuration in application.properties/application.yml & logback.xml file which should be located at merchant app's resource path. Properties/yml file contains 6 attributes ``` username, password, shurjopay-api, sp-callback & path, name for shurjopay logging ```
- **application.yml example**
``` 
shurjopay:
  username: sp_sandbox
  password: pyyk97hu&6u6
  shurjopay-api: https://sandbox.shurjopayment.com/api/
  sp-callback: https://sandbox.shurjopayment.com/response
  logging:
    file:
      path: /home/<user>/<your_logging_path>
      name: shurjopay-plugin.log
```
- **application.properties example**
``` 
shurjopay.username=sp_sandbox
shurjopay.password=pyyk97hu&6u6
shurjopay.shurjopay-api=https://sandbox.shurjopayment.com/api/
shurjopay.sp-callback=https://sandbox.shurjopayment.com/response
shurjopay.logging.file.path=/home/<user>/<your_logging_path>
shurjopay.logging.file.name=shurjopay-plugin.log
```
- **logback.xml example**
``` 
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE xml>
<configuration>
	<property resource="application.yml"/>
	
	<appender name="FILE" class="ch.qos.logback.core.FileAppender">
		<file>${path}/${name}</file>
		<append>true</append>
		<encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
		</encoder>
	</appender>
	
	<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <logger name="bd.com.shurjopay.plugin" level="INFO" additivity="false">
        <appender-ref ref="FILE"/>
    </logger>

	<root level="INFO">
		<appender-ref ref="FILE" />
		<appender-ref ref="CONSOLE" />
	</root>
</configuration>
```
- _**Note:** In shurjopay Spring plugin, auto-warable Shurjopay dependency is available._
### Make Payment: 
Merchants and service providers can make payment by calling this service. Developer should call _makePayment()_ method with payment request parameter. _shurjoPay_ needs some information to perform creating payment request. So that, this service method requires request payment object param. After performing with this, service returns response object contains payment URL and customer information.
- **Example**
	- Request payment
	 ``` 
		request.setAmount("10");
		request.setOrderId("sp215689");
		request.setCurrency("BDT");
		request.setCustomerName("John");
		request.setCustomerAddr("Holding no-N/A, Road-16, Gulshan-1, Dhaka");
		request.setCustomerPhn("01766666666");
		request.setCustomerCity("Dhaka");
		request.setCustomerPostCode("1212");
	 ```
	- Response payment
	 ``` 
	 	paymentUrl= <generated payment url by shurjoPay gateway>
		amount=10
		currency=BDT
		spOrderId=sp32aad7c6dad7a
		customerOrderId=sp215689
		customerName=John
		customerAddr=Holding no-N/A, Road-16, Gulshan-1, Dhaka
		customerPhn=01766666666
		customerCity=Dhaka
		customerEmail=null
		clintIp=102.101.1.1
		intent=sale
		transactionStatus=Initiated
	 ```
### Verify payment: 
After a succussful payment merchants or service providers get verify payment order by redirecting callback url. Developer must call _verifyPayment()_ method with shurjopay order id (shurjopay transaction id) parameter. sp order id (shurjopay transaction id) is provided by payment response named spOrderId. A successful successful payment returns payment verification object.
- **Example**
	- Request payment verification of a order
	 ``` 
	 	Parameter: spOrderId
		Parameter type: String
	 ```
	- Response order
	 ``` 
	 	orderId=sp32aad7c6dad7a
		currency=BDT
		amount=10
		payableAmount=10
		discountAmount=null
		discpercent=0
		usdAmt=0
		usdRate=0
		method=null
		spMsg=initiated
		spCode=1068
		name=John
		email=john@example.com
		address=Holding no-N/A, Road-16, Gulshan-1, Dhaka
		city=Dhaka
		value1=value1
		value2=value2
		value3=value3
		value4=value4
	 ```
### Check payment status: 
After a succussful payment (sp_code=1000) and verify payment merchants or service providers can check payment status. Developer should call _checkPaymentStatus()_ method with order id (shurjopay transaction id) parameter. sp order id (shurjopay transaction id) is provided by order response named orderId. A successfully verified payment with orderId returns a payment object.
- **Example**
	- Request verification of a order
	 ``` 
	 	Parameter: spOrderId
		Parameter type: String
	 ```
	- Response order
	 ``` 
	 	orderId=sp32aad7c6dad7a
		currency=BDT
		amount=10
		payableAmount=10
		discountAmount=null
		discpercent=0
		usdAmt=0
		usdRate=0
		method=null
		spMsg=initiated
		spCode=1068
		name=John
		email=john@example.com
		address=Holding no-N/A, Road-16, Gulshan-1, Dhaka
		city=Dhaka
		value1=value1
		value2=value2
		value3=value3
		value4=value4
	 ```
# More...
https://shurjopay.com.bd/#contacts
<hr>
Copyright ©️2022 Shurjomukhi Limited.
