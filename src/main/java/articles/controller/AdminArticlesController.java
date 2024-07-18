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
import articles.entity.User;
import articles.repository.ArticlesRepository;
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


import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class AdminArticlesController {
	
	 @Autowired
	    private ArticlesService articleService;


		@Autowired
	    private CategoriesService categorieService;
	 
	
	 
	 @Autowired
	  private ArticlesRepository articlesRepository;

	  
	 
	
	 
	/* @RequestMapping(value="/",method=RequestMethod.GET)
	    public ModelAndView loginpage() throws Exception{
	      
		  ModelAndView v = new ModelAndView();
		  List<Articles> listeArticles = articleService.getListArticles();
	        v.addObject("listeArticles",listeArticles);
		  v.setViewName("login");
	        return v;
	    }*/
	 
	 @RequestMapping(value="/formajoutarticle",method=RequestMethod.GET)
	    public String formajoutarticle(Model model) throws Exception{
	      
		 Articles article = new Articles();
		 model.addAttribute("article", article);
		
	        List<Categories> listCategories = categorieService.getListCategories();
			model.addAttribute("listCategories", listCategories);
			
	        return "articles/formajoutarticle";
	    }
	 
	 public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
	 
	 @PostMapping("/savearticle")
	    public String savearticle(@Valid @ModelAttribute("article") Articles monarticle,
	    		@RequestParam("image") MultipartFile file,
	                               BindingResult result,
	                               Model model) throws IOException{
	        
		 	if(result.hasErrors()){
		 		 model.addAttribute("article", monarticle);
	            return "/formajoutarticle";
	        }
		 	 StringBuilder fileNames = new StringBuilder();
		        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		        fileNames.append(file.getOriginalFilename());
		        Files.write(fileNameAndPath, file.getBytes());
		        
		        monarticle.setSary(file.getOriginalFilename());
		    	monarticle.setDatepublication(Date.valueOf(LocalDate.now()));
			 	articleService.saveArticle(monarticle);
		      
		        return "redirect:/formajoutarticle?success";
		 	
		 
	    
	        
	    }
	 @GetMapping("/articles")
	  public String getAll(Model model, @RequestParam(required = false) String keyword,
	      @RequestParam(defaultValue = "1") int page,
	      @RequestParam(defaultValue = "10") int size,
	      @RequestParam(defaultValue = "id,asc") String[] sort) {
	    try {
	      List<Articles> articles = new ArrayList<Articles>();
	      
	      String sortField = sort[0];
	      String sortDirection = sort[1];
	      
	      Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
	      Order order = new Order(direction, sortField);
	      
	      Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));
	      Page<Articles> pageArticles;
	      if (keyword == null) {
	        pageArticles = articlesRepository.findAll(pageable);
	      } else {
	        pageArticles = articlesRepository.findByTitreContainingIgnoreCase(keyword, pageable);
	        model.addAttribute("keyword", keyword);
	      }

	      articles = pageArticles.getContent();
	      
	      model.addAttribute("articles", articles);
	      model.addAttribute("currentPage", pageArticles.getNumber() + 1);
	      model.addAttribute("totalItems", pageArticles.getTotalElements());
	      model.addAttribute("totalPages", pageArticles.getTotalPages());
	      model.addAttribute("pageSize", size);
	      model.addAttribute("sortField", sortField);
	      model.addAttribute("sortDirection", sortDirection);
	      model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	    }

	    return "articles/articles";
	  }
	 
	 @GetMapping("/detailarticle/{id}")
	 public String detailArticle(@PathVariable("id") Integer articleId,Model model)
	    {
		 model.addAttribute("article", articlesRepository.findById(articleId).get());
		 return "articles/detailarticle";
	    }
	 
	 @GetMapping("/editarticle/{id}")
	 public String editArticle(@PathVariable("id") Integer articleId,Model model)
	    {
		 model.addAttribute("article", articlesRepository.findById(articleId).get());
		 List<Categories> listCategories = categorieService.getListCategories();
		 model.addAttribute("listCategories", listCategories);
		 return "articles/editarticle";
	    }
	 
	 
	 @PostMapping("/editarticlefunc")
	    public String editArticleFunc(@Valid @ModelAttribute("article") Articles monarticle,
	    		@RequestParam("image") MultipartFile file,
	                               BindingResult result,
	                               Model model) throws IOException{
	        
		 	if(result.hasErrors()){
		 		 model.addAttribute("article",articlesRepository.findById(monarticle.getId()).get() );
	            return "/editarticle";
	        }
		 	
		 	String saryedit = "";
		 	
		 	if(!file.isEmpty())
		 	{
		 	 StringBuilder fileNames = new StringBuilder();
		        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		        fileNames.append(file.getOriginalFilename());
		        Files.write(fileNameAndPath, file.getBytes());
		        saryedit = file.getOriginalFilename();
		        monarticle.setSary(saryedit);
		       
		 	}
		 	else
		 	{
		 		monarticle.setSary(saryedit);
		 	}
		 	
		    	//monarticle.setDatepublication(Date.valueOf(LocalDate.now()));
		 	
			 	articleService.updateArticle( monarticle.getId(), monarticle);
		        return "redirect:/articles?success";
		 	
		 
	    
	        
	    }

	 @GetMapping("/deletearticle/{id}")
	 public String deleteArticle(@PathVariable("id") Integer articleId,Model model)
	    {
		articlesRepository.deleteById(articleId);
		
		 return "redirect:/articles?deletesuccess";
	    }
	 

}
