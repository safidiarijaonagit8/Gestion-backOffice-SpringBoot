package articles.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import articles.dto.ArticlesDto;
import articles.entity.Articles;
import articles.service.ArticlesService;
import articles.service.EvenementsService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class ApiController {
	
	 @Autowired
	    private ArticlesService articleService;
	 
	 @Autowired
	    private EvenementsService evenementService;
	 
	 @GetMapping("/articles")
	 public List<ArticlesDto> articles() throws Exception{
	      
		  
		  List<Articles> listeArticles = articleService.getListArticles();
		  List<ArticlesDto> listeArticlesDto = new ArrayList();
		  
		  for(int i=0;i<listeArticles.size();i++)
		  {
			  ArticlesDto articlesDto = new ArticlesDto();
			  articlesDto.setId(listeArticles.get(i).getId());
			  articlesDto.setTitre(listeArticles.get(i).getTitre());
			  articlesDto.setSoustitre(listeArticles.get(i).getSoustitre());
			  articlesDto.setContenus(listeArticles.get(i).getContenus());
			  articlesDto.setDatepublication(listeArticles.get(i).getDatepublication());
		//	  Resource resource = new ClassPathResource("static/uploads/"+listeArticles.get(i).getSary());
			////  byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
			//  articlesDto.setSary(ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes));
			 articlesDto.setSary(listeArticles.get(i).getSary());
			  listeArticlesDto.add(articlesDto);
			  
			  
		  }
		  
	       
	        return listeArticlesDto;
	    }
	 
	 @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
	    public ResponseEntity<byte[]> getImage() throws IOException {
	        Resource resource = new ClassPathResource("static/uploads/election.jpg");

	        if (!resource.exists()) {
	            return ResponseEntity.notFound().build();
	        }

	        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
	        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	    }
	 /*   @GetMapping(value = "/images", produces = MediaType.IMAGE_JPEG_VALUE)
	    public List<ResponseEntity<byte[]>> getImages() throws IOException {
	    	  List<Articles> listeArticles = articleService.getListArticles();
	    	  
//	    	 + List<Resource> resources = new ArrayList();
	    	  List<ResponseEntity<byte[]>> listeImages = new ArrayList();
	    	  for(int i=0;i<listeArticles.size();i++)
	    	  {
	    		  Resource resource = new ClassPathResource("static/uploads/"+listeArticles.get(i).getSary());
	    		  byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
	    		  ResponseEntity<byte[]> r = ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	    		  listeImages.add(r);
//	    		  resources.add(resource);
	    	  }
	       

	        if (!resource.exists()) {
	            return ResponseEntity.notFound().build();
	        }
	        

//	        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
	        return listeImages;
	    }*/
	 
	 @GetMapping(value = "/sary", produces = MediaType.IMAGE_JPEG_VALUE)
	    public ResponseEntity<byte[]> getImage(@RequestParam("sary") String sary) throws IOException {
	    	
	    	
	        Resource resource = new ClassPathResource("static/uploads/"+sary);

	        if (!resource.exists()) {
	            return ResponseEntity.notFound().build();
	        }

	        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
	        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	    }
	 

}
