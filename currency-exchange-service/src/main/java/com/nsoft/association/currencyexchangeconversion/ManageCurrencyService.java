package com.nsoft.association.currencyexchangeconversion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ManageCurrencyService {
	
	
	@Autowired
    private JdbcTemplate jtm;
	
	public Currency getConversion(String currencycode) {
		String sql = "SELECT * FROM Currency WHERE currencycode = ?";

        return jtm.queryForObject(sql, new Object[]{currencycode},
                new BeanPropertyRowMapper<>(Currency.class));
	}
	
	
	public Currency getConversionfactor(String countryCode) {
		String sql = "SELECT * FROM Currency WHERE id = ?";

        return jtm.queryForObject(sql, new Object[]{countryCode},
                new BeanPropertyRowMapper<>(Currency.class));
	}
	
	public int updateConversion(Currency currency) {
		return jtm.update("update currency " + " set conversionFactor = ?" + " where country = ?",
		        new Object[] {
		        		currency.getConversionFactor(), currency.getCountry()
		        });
	}
	
	public int addConversion(Currency currency) {
		 return jtm.update("INSERT into Currency (id, currencyCode, country, conversionFactor) " + "values(?, ?,  ?, ?)",
			        new Object[] {
			        		currency.getId(),currency.getCurrencyCode(), currency.getCountry(), currency.getConversionFactor()
			        });
	}

	
}
