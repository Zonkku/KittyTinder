package palvelino.fi.KittyTinder.domain;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.FetchType;

@Entity
public class AgeCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long agecategoryid;
	
	@Size(max=100)
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "agecategory")
	private List<Kitty> kitties;

	public AgeCategory() {}
	
	public AgeCategory(String name) {
		super();
		this.name=name;
	}

	public Long getAgecategoryid() {
		return agecategoryid;
	}

	public void setAgecategoryid(Long agecategoryid) {
		this.agecategoryid = agecategoryid;
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

	@Override
	public String toString() {
		return "AgeCategory [agecategoryid=" + agecategoryid + ", name=" + name + "]";
	}
	
}
