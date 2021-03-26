package com.nsoft.association.currencyconversionservices;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="exchangeservice")

//enable ribbon 
@RibbonClient(name="exchangeservice") 
public interface CurrencyConversionClient {

	@GetMapping("/currency/conversionfactor3/{fromcurrencycode}/{tocurrencycode}")
	public Double getConversionbetweenCurrency(@PathVariable String fromcurrencycode, @PathVariable String tocurrencycode);
}
