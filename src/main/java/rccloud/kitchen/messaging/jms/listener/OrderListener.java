package rccloud.kitchen.messaging.jms.listener;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import rccloud.RiceNoodleOrder;
import rccloud.kitchen.KitchenUI;

@Component
public class OrderListener { // push model
	
	private KitchenUI ui;
	
	@Autowired
	public OrderListener(KitchenUI ui) {
		this.ui = ui;
	}
	
	@JmsListener(destination = "queue1")
	public void receiveOrder(@Payload RiceNoodleOrder order, @Headers MessageHeaders headers, Message message) {
		ui.displayOrder(order);
	}
	
}
