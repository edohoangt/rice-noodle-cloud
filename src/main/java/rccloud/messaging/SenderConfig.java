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
	
	@Value("${spring.artemis.broker-url}")
	private String brokerUrl;
	
	@Bean
	public ActiveMQConnectionFactory senderConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		try {
			activeMQConnectionFactory.setBrokerURL(brokerUrl);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return activeMQConnectionFactory;
	}
	 
	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(senderConnectionFactory());
	    cachingConnectionFactory.setSessionCacheSize(10);

	    return cachingConnectionFactory;
	}
	 
	 @Bean
	 public Destination orderDestination() {
		return new ActiveMQQueue(orderDestination);
	 }
	 
	 @Bean
     public JmsTemplate orderJmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory());
		jmsTemplate.setDefaultDestination(orderDestination());
		jmsTemplate.setReceiveTimeout(5000);
		return jmsTemplate;	 }
	 
}
