package com.shurjomukhi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shurjomukhi.model.ShurjopayConfig;

/**
 * Configures auto-wired shurjopay spring bean,
 * Developers need to provide below namespace's values in spring default properties.
 * <code>
 * shurjopay.username,
 * shurjopay.password,
 * shurjopay.shurjopay-api,
 * shurjopay.sp-callback
 * </code>
 * 
 * @author Al - Amin
 * @since 2022-10-16
 */
@Configuration
public class ShurjopaySpringConfig {
	@Value("${shurjopay.username}")
	private String username;

	@Value("${shurjopay.password}")
	private String password;

	@Value("${shurjopay.shurjopay-api}")
	private String apiBaseUrl;

	@Value("${shurjopay.sp-callback}")
	private String callbackUrl;

	/**
	 * Instantiates Shurjopay spring bean to serve auto-wired injection.
	 * @return {@link Shurjopay} spring bean with spring default properties configurations for shurjopay.
	 */
	@Bean
	public Shurjopay shurjoPay() {
		return new Shurjopay(shurjopayConfig());
	}

	/**
	 * Loads shurjopay configurations.
	 * @return {@link ShurjopayConfig} with shurjopay configuration which are comes from default properties.
	 */
	@Bean
	public ShurjopayConfig shurjopayConfig() {
		return new ShurjopayConfig()
				   .setUsername(username)
				   .setPassword(password)
				   .setApiBaseUrl(apiBaseUrl)
				   .setCallbackUrl(callbackUrl);
	}
}
