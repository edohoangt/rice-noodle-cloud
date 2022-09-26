package rccloud.data;

import org.springframework.data.repository.CrudRepository;

import rccloud.RiceNoodleOrder;

public interface OrderRepository extends CrudRepository<RiceNoodleOrder, Long> {
		
}
