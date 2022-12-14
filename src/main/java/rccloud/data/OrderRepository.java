package rccloud.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import rccloud.RiceNoodleOrder;
import rccloud.AppUser;

public interface OrderRepository extends PagingAndSortingRepository<RiceNoodleOrder, Long> {
	
	List<RiceNoodleOrder> findByAppUserOrderByPlacedAtDesc(AppUser appUser, Pageable pageable);
	
}
