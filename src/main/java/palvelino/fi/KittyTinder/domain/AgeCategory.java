package palvelino.fi.KittyTinder.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class AgeCategory {
	private Long id;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "agecategory")
	private List<Kitty> kitties;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public AgeCategory() {}
	
	public AgeCategory(String name) {
		super();
		this.name=name;
	}
		
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Kitty> getKitties() {
		return kitties;
	}

	public void setKitties(List<Kitty> kitties) {
		this.kitties = kitties;
	}
	
	

}
