package rccloud.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rccloud.RiceNoodleOrder;
import rccloud.data.OrderRepository;
import rccloud.messaging.OrderMessagingService;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
public class OrderApiController {
	
	private OrderRepository orderRepository;
	private OrderMessagingService messagingService;
	
	public OrderApiController(OrderRepository orderRepository, OrderMessagingService messagingService) {
		this.orderRepository = orderRepository;
		this.messagingService = messagingService;
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public RiceNoodleOrder postOrder(RiceNoodleOrder order) {
		messagingService.sendOrder(order);
		return orderRepository.save(order);
	}
	
}
