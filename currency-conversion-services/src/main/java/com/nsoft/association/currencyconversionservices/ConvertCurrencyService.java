package com.nsoft.association.currencyconversionservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@Service
@RibbonClient(name="exchangeservice")
public class ConvertCurrencyService {

	final CurrencyConversionClient currencyConversionClient;
	
	@Autowired
	LoadBalancerClient  lbclient;
	
	@Autowired
	public ConvertCurrencyService(CurrencyConversionClient currencyConversionClient) {
		this.currencyConversionClient = currencyConversionClient;
	}
	
	
	//@HystrixCommand(fallbackMethod = "conversionFactorFallbackWithLB")
	public Double getConvertAmountWithLB(String fromcode, String tocode ,Double amount) {
		Double convertedAmount = 0.0;
		
		
		
		ServiceInstance instance = lbclient.choose("exchangeservice");
		lbRequest dRequest=new lbRequest(fromcode,tocode);
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/currency/caldisc";
		System.out.println("the host port is "+instance.getPort());
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<lbRequest> dre = new HttpEntity<lbRequest>(dRequest);

		ResponseEntity<Double> dResponseEntity = restTemplate.exchange(url, HttpMethod.POST, dre,
				Double.class);
		convertedAmount = dResponseEntity.getBody() * amount;
		return convertedAmount;
	}
		
	
		@HystrixCommand(fallbackMethod = "conversionFactorFallback")
		public Double getConvertAmount(String fromcode, String tocode ,Double amount) {
			Double convertedAmount = 0.0;
			
			Double factor = currencyConversionClient.getConversionbetweenCurrency(fromcode, tocode);
			convertedAmount = factor * amount;
			return convertedAmount;
		}
	
	public Double conversionFactorFallback(String fromcode, String tocode, Double amount) {
		Double convertedAmount = 0.0;
		Double defFactor = 3.0;
		
		convertedAmount = defFactor * amount;
		
		return convertedAmount;
		
	}
}
