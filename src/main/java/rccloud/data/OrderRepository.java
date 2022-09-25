package rccloud.data;

import rccloud.RiceNoodleOrder;

public interface OrderRepository {
	
	RiceNoodleOrder save(RiceNoodleOrder order);
	
}
