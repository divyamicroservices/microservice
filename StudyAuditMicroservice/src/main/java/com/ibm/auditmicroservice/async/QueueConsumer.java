package com.ibm.auditmicroservice.async;

import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.ibm.auditmicroservice.audit.Audit;
import com.ibm.auditmicroservice.audit.AuditJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class QueueConsumer {

	Logger logger = LoggerFactory.getLogger(QueueConsumer.class);
	
	@Autowired
	private AuditJPARepository repo;
	
	public void receiveMessage(String message) {
		logger.info("Inside Queue Consumer receive message");
		processMessage(message);
	}
	

	public void receiveMessage(byte[] message) {
		
		String str = new String(message);
		processMessage(str);
	}

	
	public void processMessage(String message) {
		logger.info("Inside Queue Consumer processMessage message in Queue Consumer-->"+ message);
		StringTokenizer st1 = new StringTokenizer(message, "---"); 
	             
		
		
		String userName =st1.nextToken().replaceAll("\"","");
		String status =st1.nextToken().replaceAll("\"","");
		String date =st1.nextToken().replaceAll("\"","");
		
		
		
		logger.info("userName-->"+ userName);
		logger.info("status--."+ status);
		logger.info("date-->"+ date);
		Audit adt = new Audit(userName,status,date);
		repo.save(adt);
		
	}


}
