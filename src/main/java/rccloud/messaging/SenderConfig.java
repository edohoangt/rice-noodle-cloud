package rccloud.messaging;

import javax.jms.Destination;
import javax.jms.JMSException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class SenderConfig {

	@Value("${artemis.queue}")
	private String orderDestination;
	
	
	 @Bean
	 public Destination orderDestination() {
		return new ActiveMQQueue(orderDestination);
	 }
	 
}
