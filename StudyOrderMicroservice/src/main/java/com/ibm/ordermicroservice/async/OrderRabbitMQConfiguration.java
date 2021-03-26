package com.ibm.ordermicroservice.async;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitMQConfiguration {

	private static final String LISTENER_METHOD = "orderReceiveMessage"; 
	@Bean
	Queue queue() {
		return new Queue("StudyOrderQueue",true);
	}
	
	
	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange("StudyOrderExchange");
	}
	
	
	@Bean
	Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}
	
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory ,
			MessageListenerAdapter listenerAdapter ) {
	
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames("StudyOrderQueue");
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(OrderQueueConsumer consumer) {
		return new MessageListenerAdapter(consumer, LISTENER_METHOD);
	}
}
