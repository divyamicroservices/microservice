package com.nsoft.association.currencyconversionservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("convertor")
@RestController
public class CurrencyCalculationCotroller {
	 
	@Autowired
	ConvertCurrencyService convertCurrencyService;
	
	 private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//http://localhost:8100/currency/conversion/100/GBP/INR  ---calling via feign client
	 /**update @GetMapping("/code/CC/{countryCode}") in CurrencyConversionClient to see the hystrix 
	 method being called
	 */
	//http://localhost:9091/convertor/currency/conversion/100/GBP/INR -for api gateway
		//http://localhost:8100/currency/conversion/100/GBP/INR gateway
	 @GetMapping("currency/conversion/{amount}/{fromcode}/{tocode}")
		public Double getConvertAmount(@PathVariable(name = "amount") Double amount,@PathVariable(name = "fromcode") String fromcode, @PathVariable(name = "tocode") String tocode) {

			return convertCurrencyService.getConvertAmount(fromcode,tocode, amount);
			
		}
	 
	//http://localhost:9091/convertor/currencywithlb/conversion/100/GBP/INR -with api gateway
		//http://localhost:8100/currencywithlb/conversion/100/GBP/INR -normal
	 @GetMapping("currencywithlb/conversion/{amount}/{fromcode}/{tocode}")
		public Double getConvertAmountlb(@PathVariable(name = "amount") Double amount,@PathVariable(name = "fromcode") String fromcode, @PathVariable(name = "tocode") String tocode) {

			return convertCurrencyService.getConvertAmountWithLB(fromcode,tocode, amount);
			
		}
}
