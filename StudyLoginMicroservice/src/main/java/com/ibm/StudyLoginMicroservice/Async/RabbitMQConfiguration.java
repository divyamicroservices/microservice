package com.ibm.StudyLoginMicroservice.Async;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	@Bean
	Queue queue() {
		return new Queue("StudyLoginQueue",true);
	}
	
	
	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange("StudyLoginExchange");
	}
	
	
	@Bean
	Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}
	
	
	
}
