package com.ibm.auditmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StudyAuditMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyAuditMicroserviceApplication.class, args);
	}

}
