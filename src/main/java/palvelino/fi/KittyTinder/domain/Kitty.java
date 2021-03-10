package palvelino.fi.KittyTinder.domain;

import javax.validation.constraints.Size;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import palvelino.fi.KittyTinder.domain.AgeCategory;

@Entity
public class Kitty {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name, sex, city, intro, addInfo;
	private int age;
	
	@ManyToOne
	@JoinColumn(name = "agecategoryid")
	private AgeCategory agecategory;
	
	public Kitty() {}
	
	public Kitty(String name, int age) {
		this.name=name;
		this.age=age;
		}

	public Kitty(String name, int age, String city) {
		this.name=name;
		this.age=age;
		this.city=city;
	}

	public Kitty(String name, int age, String city,  String sex) {
		this.name=name;
		this.age=age;
		this.city=city;
		this.sex=sex;

	}
	
	
	public Kitty(String name, String city, int age, String intro) {
		this.name=name;
		this.city=city;
		this.age=age;
		this.intro=intro;
	}
	
		
	public Kitty(String name, String city, int age, String intro, String addInfo) {
		this.name=name;
		this.city=city;
		this.age=age;
		this.intro=intro;
		this.addInfo=addInfo;
	}
	
	public Kitty(String name, String city, int age, String intro, String addInfo, AgeCategory agecategory) {
		this.name=name;
		this.city=city;
		this.age=age;
		this.intro=intro;
		this.addInfo=addInfo;
		this.agecategory=agecategory;
	}

	public Long getId() {
		return id;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public AgeCategory getAgecategory() {
		return agecategory;
	}

	public void setAgecategory(AgeCategory agecategory) {
		this.agecategory = agecategory;
	}

	@Override
	public String toString() {
		if (this.agecategory != null)
			return "Kitty [id=" + id + ", name=" + name + ", sex=" + sex + ", city=" + city + ", intro=" + intro
				+ ", addInfo=" + addInfo + ", age=" + age + ", agecategory=" + this.getAgecategory() + "]";
		else
			return "Kitty [id=" + id + ", name=" + name + ", sex=" + sex + ", city=" + city + ", intro=" + intro
					+ ", addInfo=" + addInfo + ", age=" + age + "]";
	}
}
