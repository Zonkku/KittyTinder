package palvelino.fi.KittyTinder.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface KittyRepository extends CrudRepository <Kitty, Long> {
	List<Kitty> findByName(String name);
	
}
