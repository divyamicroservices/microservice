package com.ibm.StudyLoginMicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ibm.StudyLoginMicroservice.Login.Login;
import com.ibm.StudyLoginMicroservice.Login.LoginController;
import com.ibm.StudyLoginMicroservice.Login.LoginJPARepository;


@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class StudyLoginMicroserviceApplication {

	
	public static void main(String[] args) {
		
		SpringApplication.run(StudyLoginMicroserviceApplication.class, args);
		
	}
	
	@Autowired
	private LoginJPARepository repo;
	
	/*
	@Bean
	CommandLineRunner inspect(ApplicationContext ctx) {
		return (r) -> {
			int beanCount = ctx.getBeanDefinitionCount();
			String[] beans = ctx.getBeanDefinitionNames();
			logger.info("Bean Count = " + beanCount);
			for (int i = 0; i < beanCount; i++) {
				// logger.info(beans[i]);
			}

			//List<Product> products = new ArrayList<Product>();
			repo.save(new Login("user1", "1234"));
			repo.save(new Login("user2", "1234"));
			repo.save(new Login("user3", "1234"));
			repo.save(new Login("user4", "1234"));
			repo.save(new Login("user5", "1234"));
		
			
			

		};
	}
	
	*/

}
