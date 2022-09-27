package rccloud.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import rccloud.AppUser;
import rccloud.RiceNoodleOrder;
import rccloud.data.OrderRepository;

//@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("riceNoodleOrder")
public class OrderController {
	
	private OrderRepository orderRepository;
	
	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
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
		orderRepository.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
