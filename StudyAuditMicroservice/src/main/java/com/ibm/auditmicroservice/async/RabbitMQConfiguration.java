package com.ibm.auditmicroservice.async;

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
public class RabbitMQConfiguration {

	private static final String LISTENER_METHOD = "receiveMessage"; 
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
	
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory ,
			MessageListenerAdapter listenerAdapter ) {
	
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames("StudyLoginQueue");
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(QueueConsumer consumer) {
		return new MessageListenerAdapter(consumer, LISTENER_METHOD);
	}
}
