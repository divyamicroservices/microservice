package com.ibm.auditmicroservice.audit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/audit")
public class AuditController {

	@Autowired
	private AuditJPARepository repo;
	
	Logger logger = LoggerFactory.getLogger(AuditController.class);
	

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<Audit> getAllAudit() {
		logger.info("Inside AuditController inside getAllAudit");
		return repo.findAll();
	}
	
	
}
