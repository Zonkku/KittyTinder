package palvelino.fi.KittyTinder.web;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import palvelino.fi.KittyTinder.domain.CategoryRepository;
import palvelino.fi.KittyTinder.domain.Kitty;
import palvelino.fi.KittyTinder.domain.KittyRepository;

@Controller
public class KittyController {
	
	
	@Autowired
	private KittyRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@GetMapping("/index")
	public String Hello() {
		return "index";
	}
	
	@GetMapping("/kittyprofiles")
	public String showProfiles(Model model) {
		model.addAttribute("kitties", repository.findAll());
		return "kittyprofiles";
	}
	
	@GetMapping("/addkitty")
	public String kittyForm(Model model) {
		model.addAttribute("kitty", new Kitty());
		model.addAttribute("categories", crepository.findAll());
		return "addkitty";
	}
	
	
	
	@PostMapping("/addkitty")
	public String kittySubmit(@Valid Kitty kitty, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addkitty";
		}
		
		model.addAttribute("kitty", kitty);
		return "kittylist";
	}
	
	
	
	
	
	
	
}
