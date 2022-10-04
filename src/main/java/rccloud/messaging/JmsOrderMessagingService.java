package rccloud.messaging;

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
	

	@Override
	public void sendOrder(RiceNoodleOrder order) {
		jmsTemplate.send(session -> session.createObjectMessage(order));
	}
	
	
	
}
