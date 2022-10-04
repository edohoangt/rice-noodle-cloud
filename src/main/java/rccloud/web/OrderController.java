package rccloud.web;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import rccloud.AppUser;
import rccloud.RiceNoodleOrder;
import rccloud.data.OrderRepository;
import rccloud.messaging.OrderMessagingService;

//@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("riceNoodleOrder")
public class OrderController {
	
	private OrderProps props;
	
	private OrderRepository orderRepository;
	
	private OrderMessagingService messagingService;
	
	public OrderController(OrderRepository orderRepository, OrderProps props, OrderMessagingService messagingService) {
		this.orderRepository = orderRepository;
		this.props = props;
		this.messagingService = messagingService;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid RiceNoodleOrder order, Errors errors, 
			SessionStatus sessionStatus, @AuthenticationPrincipal AppUser user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		order.setAppUser(user);
		
//		log.info("Order submitted: {}", order);
		messagingService.sendOrder(order);
		orderRepository.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
	
	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal AppUser user, Model model) {
		Pageable pageable = PageRequest.of(0, props.getPageSize());
		model.addAttribute("orders", orderRepository.findByAppUserOrderByPlacedAtDesc(user, pageable));
		return "orderList";
	}
	
}
