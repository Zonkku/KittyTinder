package palvelino.fi.KittyTinder.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository <AgeCategory, Long> {
	List<AgeCategory> findByName(String name);
	

}
