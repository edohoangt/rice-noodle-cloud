package rccloud.kitchen.messaging.jms;

import org.springframework.stereotype.Component;

import rccloud.RiceNoodleOrder;

public interface OrderReceiver {

	public RiceNoodleOrder receiveOrder();
	
}
