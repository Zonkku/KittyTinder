package palvelino.fi.KittyTinder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import palvelino.fi.KittyTinder.domain.User;
import palvelino.fi.KittyTinder.domain.UserRepository;
import palvelino.fi.KittyTinder.domain.AgeCategory;
import palvelino.fi.KittyTinder.domain.CategoryRepository;
import palvelino.fi.KittyTinder.domain.Kitty;
import palvelino.fi.KittyTinder.domain.KittyRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class KittyTinderApplication {
	private static final Logger log = LoggerFactory.getLogger(KittyTinderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KittyTinderApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(KittyRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			
			AgeCategory pentu = new AgeCategory("Pentu (0-6 kk)");
			AgeCategory juniori = new AgeCategory("Juniori (6 kk - 2 v)");
			AgeCategory nuoriAikuinen = new AgeCategory("Nuori aikuinen (3 - 6 v)");
			AgeCategory keskiIkainen = new AgeCategory("Keski-ikäinen (7 - 10 v)");
			AgeCategory seniori = new AgeCategory("Seniori (11 - 14 v)");
			AgeCategory vanhus = new AgeCategory("Vanhus (15+ v)");

			crepository.save(pentu);
			crepository.save(juniori);
			crepository.save(nuoriAikuinen);
			crepository.save(keskiIkainen);
			crepository.save(seniori);
			crepository.save(vanhus);
			
			Kitty kisu1 = new Kitty("Tervehdys! Minun nimeni on Ingrid. Olen kuulemma uljasta viikinkineitojen sukua, ja hyvin ylpeä sielu.", "Ingrid", 3, nuoriAikuinen, "/images/Ingrid1.jpg");
			
			
			Kitty kisu2 = new Kitty("Hei, olen Jalmari, hurmaavaksi ja persoonalliseksi kehuttu. Minulla on töpöhäntä ja rakastan lattialämmitystä ", "Jalmari", 13, seniori, "/images/Jalmari1.jpg");
			
			
			
			Kitty kisu3 = new Kitty("Xeni tässä, hei! Lämpenen hitaasti uusille ystäville, mutta kun lämpenen, tykkään silittelyistä ja rapsutteluista", "Xeni", 7, keskiIkainen, "/images/Xeni1.jpg");
			
			repository.save(kisu1);
			repository.save(kisu2);
			repository.save(kisu3);
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "user", "USER");
			User user2 = new User("admin", "admin", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
						
			
			log.info("fetch kitties");
			for (Kitty kitty : repository.findAll()) {
				log.info(kitty.toString());
			}
		};
	}

}
