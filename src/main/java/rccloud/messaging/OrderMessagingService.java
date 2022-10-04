package rccloud.messaging;

import rccloud.RiceNoodleOrder;

public interface OrderMessagingService {

	public void sendOrder(RiceNoodleOrder order);
	
}
