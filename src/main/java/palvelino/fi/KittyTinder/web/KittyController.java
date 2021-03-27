package palvelino.fi.KittyTinder.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import palvelino.fi.KittyTinder.KittyTinderApplication;
import palvelino.fi.KittyTinder.domain.CategoryRepository;
import palvelino.fi.KittyTinder.domain.Kitty;
import palvelino.fi.KittyTinder.domain.KittyRepository;
import palvelino.fi.KittyTinder.domain.UserRepository;
import palvelino.fi.KittyTinder.domain.FileModel;
import palvelino.fi.KittyTinder.domain.FileModelRepository;


@Controller
public class KittyController {
	
	
	@Autowired
	private KittyRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Autowired
	private FileModelRepository frepository;
		
	@Value("${upload.path}")
	private String uploadFolder;

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
		public String saveKitty(Kitty kitty) {
			repository.save(kitty);
			return "redirect:kittyprofiles";
		}

	
	//save new kitty
//		@PostMapping("/save")
//		public String saveKitty(@Valid Kitty kitty, BindingResult bindingResult) {
//			if (bindingResult.hasErrors()) {
//				return "addkitty";
//			}
//			repository.save(kitty);
//			return "redirect:kittyprofiles";
//		}

	
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

    //run add photo
    @GetMapping("/addphoto")
    public String index() {
    return "addphoto";
    }
    
	//add photo
	 @PostMapping("/upload") 
	 public String fileUpload( @RequestParam("file") MultipartFile file, Model model ) {
		 if (file.isEmpty()) {
			 model.addAttribute("msg", "Upload failed");
			 return "uploadstatus";
			 }
		 
		try {
			byte[] bytes = file.getBytes();
	 		FileModel fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), bytes);
	 		frepository.save(fileModel);
	 		model.addAttribute("msg", "File " + file.getOriginalFilename() + "uploaded");
			
			} catch (IOException e) {
			e.printStackTrace();
			}
	
			return "uploadstatus";
	 }
	 
	 @GetMapping("/files")
	 public String fileList(Model model) {
	 model.addAttribute("files", frepository.findAll());
	 return "filelist";
	 }
	 
	 @GetMapping("/file/{id}")
	 public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		 Optional<FileModel> fileOptional = frepository.findById(id);
			 if(fileOptional.isPresent()) {
			 FileModel file = fileOptional.get();
			 return ResponseEntity.ok()
			 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
			 .body(file.getFile());
			 }
		 return ResponseEntity.status(404).body(null);
	 }
		//add photo
		@GetMapping("/addphoto/{id}")
		public String addPhoto(@PathVariable("id") Long kittyId, Model model){
		model.addAttribute("kitty", repository.findById(kittyId));
		model.addAttribute("agecategories", crepository.findAll());
		return "addphoto";
		}
		
		
	 
	
}
