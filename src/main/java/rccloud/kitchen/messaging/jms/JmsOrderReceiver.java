package rccloud.kitchen.messaging.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import rccloud.RiceNoodleOrder;

@Component
public class JmsOrderReceiver implements OrderReceiver { // pull model
	
	private JmsTemplate jmsTemplate;

	@Value("${artemis.queue}")
	private String destinationName;
	
	public JmsOrderReceiver(JmsTemplate jmsTemplate, MessageConverter converter) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public RiceNoodleOrder receiveOrder() {
		return (RiceNoodleOrder) jmsTemplate.receiveAndConvert(destinationName);
	}
	
	
}
