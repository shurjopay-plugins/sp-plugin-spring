package com.shurjopay.plugin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shurjopay.plugin.model.ShurjopayConfig;

/**
 * This service provides all the services of shurjoPay For using this service,
 * You have to provide below namespace's values in spring default properties
 * file <code>
 * shurjopay.username,
 * shurjopay.password,
 * shurjopay.shurjopay-api,
 * shurjopay.sp-callback,
 * </code>
 * 
 * @author Al-Amin
 * @since 2022-10-16
 */
@Configuration
public class ShurjoPaySpringConfig {

	@Value("${shurjopay.username}")
	private String username;

	@Value("${shurjopay.password}")
	private String password;

	@Value("${shurjopay.shurjopay-api}")
	private String apiBaseUrl;

	@Value("${shurjopay.sp-callback}")
	private String callbackUrl;

	@Bean
	public ShurjoPay shurjoPay() {
		return new ShurjoPay(shurjopayConfig());
	}

	@Bean
	public ShurjopayConfig shurjopayConfig() {
		return new ShurjopayConfig().setUsername(username).setPassword(password).setApiBaseUrl(apiBaseUrl)
				.setCallbackUrl(callbackUrl);
	}
}
