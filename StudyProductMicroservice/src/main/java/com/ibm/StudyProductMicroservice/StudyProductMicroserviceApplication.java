package com.ibm.StudyProductMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ibm.StudyProductMicroservice.Product.Product;
import com.ibm.StudyProductMicroservice.Product.ProductJPARepository;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class StudyProductMicroserviceApplication {


	@Autowired
	private ProductJPARepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(StudyProductMicroserviceApplication.class, args);
	}

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
			repo.save(new Product("Apple", "100"));
			repo.save(new Product("Orange", "75"));
			repo.save(new Product("Banana", "50"));
			repo.save(new Product("Grapes", "130"));
			repo.save(new Product("Mango", "200"));
		
			
			

		};
	}
	
	*/
}
