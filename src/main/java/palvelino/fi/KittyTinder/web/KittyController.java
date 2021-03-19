package palvelino.fi.KittyTinder.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import palvelino.fi.KittyTinder.domain.CategoryRepository;
import palvelino.fi.KittyTinder.domain.Kitty;
import palvelino.fi.KittyTinder.domain.KittyRepository;
import palvelino.fi.KittyTinder.domain.UserRepository;

@Controller
public class KittyController {
	
	
	@Autowired
	private KittyRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private UserRepository urepository;

	//Login page
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/kittyprofiles")
	public String showProfiles(Model model) {
		model.addAttribute("kitties", repository.findAll());
		return "kittyprofiles";
	}
	
	//add new kitty
	@GetMapping("/addkitty")
	public String addKitty(Model model) {
		model.addAttribute("kitty", new Kitty());
		model.addAttribute("agecategories", crepository.findAll());
		return "addkitty";
	}
	
	//save new kitty
		@PostMapping("/save")
		public String saveKitty(@Valid Kitty kitty, BindingResult bindingResult) {
			if (bindingResult.hasErrors()) {
				return "addkitty";
			}
			repository.save(kitty);
			return "redirect:kittyprofiles";
		}

	
	//delete kitty
	@GetMapping("/delete/{id}")
	public String deleteKitty(@PathVariable("id") Long kittyId, Model model) {
	repository.deleteById(kittyId);
	return "redirect:../kittyprofiles";
	}
	
	//edit kitty
	@GetMapping("/edit/{id}")
	public String editKitty(@PathVariable("id") Long kittyId, Model model){
	model.addAttribute("kitty", repository.findById(kittyId));
	model.addAttribute("agecategories", crepository.findAll());
	return "editkitty";
	}
	
	// RESTful service to get all kitties
    @RequestMapping(value="/kitties")
    public @ResponseBody List<Kitty> kittyListRest() {	
        return (List<Kitty>) repository.findAll();
    }    

	// RESTful service to get kitty by id
    @RequestMapping(value="/kitty/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Kitty> findKittyRest(@PathVariable("id") Long kittyId) {	
    	return repository.findById(kittyId);
    }       

	
}
