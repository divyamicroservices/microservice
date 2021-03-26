package com.nsoft.association.currencyexchangeconversion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("currency")
@RestController
@EnableDiscoveryClient
public class CurrencyExhangeServiceController {
	
	
	@Autowired
	ManageCurrencyService mcservice;
	
	//Get Currency Factor 
	
	 private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	//http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
	 //http://localhost:8000/currency-exchange/from/USD/to/INR -Normal flow 
	
	 
	 //http://localhost:8000/conversionfactor/INR
	 //http://localhost:8765/currency-exchange-service/conversionfactor/INR   --invoking via api gateway
	 @GetMapping("conversionfactor2/{currencyCode}")
	    public Double getConversion(@PathVariable String currencyCode) {
			Currency c = mcservice.getConversion(currencyCode);
			return c.getConversionFactor();
		
	    }
		
	 //http://192.168.99.104:31716/currency/conversion/100/GBP/INR
	// http://192.168.99.104:32244/conversionfactor/GBP/INR
	 //http://localhost:8000/conversionfactor/GBP/INR
	 // by docker :- http://192.168.99.100:8110/currency/conversion/100/GBP/INR
	 //by docker :- http://192.168.99.100:8000/conversionfactor/GBP/INR
	 //http://localhost:9091/currency/conversionfactor3/GBP/INR   -api gateway
	 @GetMapping("conversionfactor3/{fromcurrencycode}/{tocurrencycode}")
	 //@GetMapping("/")
	    public Double getConversionbetweenCurrency(@PathVariable String fromcurrencycode, @PathVariable String tocurrencycode) {
			Currency c1 = mcservice.getConversion(fromcurrencycode);
			Currency c2 = mcservice.getConversion(tocurrencycode);
			Double conversionfactor1=c1.getConversionFactor();
			Double conversionfactor2=c2.getConversionFactor();
			return conversionfactor1/conversionfactor2;
			
	    }
	 
	 @RequestMapping(value = "/caldisc", method = RequestMethod.POST)
		public Double calculateDiscountwithlb(@RequestBody lbRequest request) {
		 Currency c1 = mcservice.getConversion(request.getFromcode());
			Currency c2 = mcservice.getConversion(request.getTocode());
			Double conversionfactor1=c1.getConversionFactor();
			Double conversionfactor2=c2.getConversionFactor();
			return conversionfactor1/conversionfactor2;
		}
		
	 /**
	  * put this in postman 
	  * {
    "id":"10088",
  "country":"SRK",
  "currencyCode":"SR",
   "conversionFactor":4.5
}
Link :- http://localhost:8000/addCC
	  * */
		@PostMapping(value = "/addCC", consumes = "application/json")
		public ResponseEntity<?> addConversion(@RequestBody Currency currency) {
		    return ResponseEntity.ok().body(mcservice.addConversion(currency));
		}
		
		
		/**
		  * put this in postman 
		  *{
    "id":"10088",
  "country":"SRK",
  "currencyCode":"SR",
   "conversionFactor":6.5
}
	Link :- http://localhost:8000/updateCC
		  * */
		@PutMapping(value = "/updateCC", consumes = "application/json")
		public int updateConversion(@RequestBody Currency currency) {
		    return mcservice.updateConversion(currency);
		    
		}
		
		
		/**{
			   
			  "countryCode":"USD",
			   "amount":"1000"
			}
			http://localhost:8000/calConversion
			*/
		@RequestMapping(value = "/calConversion", method = RequestMethod.POST)
		public CCResponse calculateConversion(@RequestBody CCRequest request) {
			System.out.println(request.toString());
			Currency c = mcservice.getConversion(request.getCountryCode());
			CCResponse response = new CCResponse(request.getCountryCode(), request.getAmount(),
					(c.getConversionFactor() *request.getAmount() ));
			return response;
		}
		
		
		

}
