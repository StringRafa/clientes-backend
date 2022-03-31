package com.panamby.clientes.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternacionalizacaoConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource msgSource = new ReloadableResourceBundleMessageSource();
		msgSource.setBasename("classpath:messages");
		msgSource.setDefaultEncoding("ISO-8859-1");
		msgSource.setDefaultLocale(Locale.getDefault());
		return msgSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
