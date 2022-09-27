package rccloud.data;

import org.springframework.data.repository.CrudRepository;
import rccloud.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Long> {

	AppUser findByUsername(String username);
	
}
