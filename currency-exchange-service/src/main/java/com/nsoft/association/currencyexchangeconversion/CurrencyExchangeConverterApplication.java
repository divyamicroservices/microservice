package com.nsoft.association.currencyexchangeconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class CurrencyExchangeConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeConverterApplication.class, args);
	}
	
	

}
