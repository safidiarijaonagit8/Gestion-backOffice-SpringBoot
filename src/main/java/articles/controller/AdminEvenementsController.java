package articles.controller;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import articles.dto.UserDto;
import articles.entity.Articles;
import articles.entity.Categories;
import articles.entity.Evenements;
import articles.entity.User;
import articles.repository.ArticlesRepository;
import articles.repository.EvenementsRepository;
import articles.service.ArticlesService;
import articles.service.CategoriesService;
import articles.service.EvenementsService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class AdminEvenementsController {

    @Autowired
	    private CategoriesService categorieService;

		@Autowired
	    private EvenementsService evenementsService;

		@Autowired
		private EvenementsRepository eventsRepository;



    @RequestMapping(value="/formajoutevenement",method=RequestMethod.GET)
	    public String formajoutevenement(Model model) throws Exception{
	      
		 Evenements event = new Evenements();
		 model.addAttribute("event", event);
		
	        List<Categories> listCategories = categorieService.getListCategories();
			model.addAttribute("listCategories", listCategories);
			
	        return "evenements/formajoutevenement";
	    }


		public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
	 
	 @PostMapping("/saveevenement")
	    public String saveevenement(@Valid @ModelAttribute("event") Evenements monevent,
	    		@RequestParam("image") MultipartFile file,
	                               BindingResult result,
	                               Model model) throws IOException{
	        
		 	if(result.hasErrors()){
		 		 model.addAttribute("event", monevent);
	            return "evenements/formajoutevenement";
	        }
		 	 StringBuilder fileNames = new StringBuilder();
		        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		        fileNames.append(file.getOriginalFilename());
		        Files.write(fileNameAndPath, file.getBytes());
		        
		        monevent.setSary(file.getOriginalFilename());
		    	
				evenementsService.saveEvenement(monevent);
		      
		        return "redirect:/formajoutevenement?success";
		 	
		 
	    
	        
	    }

		@GetMapping("/evenements")
	  public String getAll(Model model, @RequestParam(required = false) String keyword,
	      @RequestParam(defaultValue = "1") int page,
	      @RequestParam(defaultValue = "10") int size,
	      @RequestParam(defaultValue = "id,asc") String[] sort) {
	    try {
	      List<Evenements> events = new ArrayList<Evenements>();
	      
	      String sortField = sort[0];
	      String sortDirection = sort[1];
	      
	      Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
	      Order order = new Order(direction, sortField);
	      
	      Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));
	      Page<Evenements> pageEvents;
	      if (keyword == null) {
	        pageEvents = eventsRepository.findAll(pageable);
	      } else {
	        pageEvents = eventsRepository.findByTitreContainingIgnoreCase(keyword, pageable);
	        model.addAttribute("keyword", keyword);
	      }

	      events = pageEvents.getContent();
	      
	      model.addAttribute("events", events);
	      model.addAttribute("currentPage", pageEvents.getNumber() + 1);
	      model.addAttribute("totalItems", pageEvents.getTotalElements());
	      model.addAttribute("totalPages", pageEvents.getTotalPages());
	      model.addAttribute("pageSize", size);
	      model.addAttribute("sortField", sortField);
	      model.addAttribute("sortDirection", sortDirection);
	      model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	    }

	    return "evenements/evenements";
	  }


	

        



}
