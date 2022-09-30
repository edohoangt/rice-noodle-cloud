package rccloud.web.api;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rccloud.RiceNoodleOrder;
import rccloud.data.OrderRepository;

@RestController
@RequestMapping(path = "/api/rice-noodle-orders", produces = "application/json")
public class RiceNoodleOrderRestController {

	private OrderRepository orderRepo;
	
	public RiceNoodleOrderRestController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@PutMapping(path = "/{orderId}", consumes = "application/json")
	public RiceNoodleOrder putOrder(
			@PathVariable Long orderId, @RequestBody RiceNoodleOrder order) {
		order.setId(orderId);
		return orderRepo.save(order);
	}
	
	@PatchMapping(path = "/{orderId}", consumes = "application/json")
	public RiceNoodleOrder patchOrder(
			@PathVariable Long orderId, @RequestBody RiceNoodleOrder patchOrder) {
		Optional<RiceNoodleOrder> optionalOrder = orderRepo.findById(orderId);
		if (optionalOrder.isEmpty()) {
			return null;
		}
		RiceNoodleOrder oldOrder = optionalOrder.get();
		if (patchOrder.getDeliveryName() != null) {
			oldOrder.setDeliveryName(patchOrder.getDeliveryName());
		}
		if (patchOrder.getDeliveryStreet() != null) {
			oldOrder.setDeliveryStreet(patchOrder.getDeliveryStreet());
		}
		if (patchOrder.getDeliveryTown() != null) {
			oldOrder.setDeliveryTown(patchOrder.getDeliveryTown());
		}
		if (patchOrder.getDeliveryDistrict() != null) {
			oldOrder.setDeliveryDistrict(patchOrder.getDeliveryDistrict());
		}
		if (patchOrder.getDeliveryProvince() != null) {
			oldOrder.setDeliveryProvince(patchOrder.getDeliveryProvince());
		}
		if (patchOrder.getCcNumber() != null) {
			oldOrder.setCcNumber(patchOrder.getCcNumber());
		}
		if (patchOrder.getCcExpiration() != null) {
			oldOrder.setCcExpiration(patchOrder.getCcExpiration());
		}
		if (patchOrder.getCcCVV() != null) {
			oldOrder.setCcCVV(patchOrder.getCcCVV());
		}
		return orderRepo.save(oldOrder);
	}
	
	@DeleteMapping("/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable Long orderId) {
		try {
			orderRepo.deleteById(orderId);
		} catch (EmptyResultDataAccessException e) {
			
		}
	}
	
}
