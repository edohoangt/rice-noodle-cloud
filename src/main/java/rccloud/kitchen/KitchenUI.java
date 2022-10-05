package rccloud.kitchen;

import org.springframework.stereotype.Component;

import rccloud.RiceNoodleOrder;

@Component
public class KitchenUI {

	public void displayOrder(RiceNoodleOrder order) {
		System.out.println(order);
	}
	
}
