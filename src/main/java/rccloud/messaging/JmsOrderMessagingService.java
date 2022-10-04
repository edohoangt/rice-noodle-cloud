package rccloud.messaging;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.Data;
import rccloud.RiceNoodleOrder;

@Component
@Data
public class JmsOrderMessagingService implements OrderMessagingService {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Destination orderQueue;

	@Override
	public void sendOrder(RiceNoodleOrder order) {
		jmsTemplate.convertAndSend(orderQueue, order, message -> {
			message.setStringProperty("X_ORDER_SOURCE", "WEB");
			return message;
		});
	}

}
