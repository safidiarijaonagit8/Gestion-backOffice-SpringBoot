package articles.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import articles.dto.ArticlesDto;
import articles.dto.CategoriesDto;
import articles.entity.Articles;
import articles.entity.Categories;
import articles.service.ArticlesService;
import articles.service.CategoriesService;
import articles.service.EvenementsService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/articles/api/")
public class ApiArticlesController {
	
	 @Autowired
	    private ArticlesService articleService;

        @Autowired
	    private CategoriesService categorieService;
	 
	
	 
	 @GetMapping("/articles")
	 public ResponseEntity<List<ArticlesDto>> articles() throws Exception{
	      
		  
		   try {
            List<Articles> listeArticles = articleService.getListArticles();
            
            List<ArticlesDto> listeArticlesDto = listeArticles.stream()
                    .map(article -> {
                        ArticlesDto articlesDto = new ArticlesDto();
                        articlesDto.setId(article.getId());
                        articlesDto.setTitre(article.getTitre());
                        articlesDto.setSoustitre(article.getSoustitre());
                        articlesDto.setContenus(article.getContenus());
                        articlesDto.setDatepublication(article.getDatepublication());
                        articlesDto.setSary(article.getSary());
                        articlesDto.setCategorie(article.getCategorie().getNomCategorie());
                        return articlesDto;
                    })
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(listeArticlesDto);
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., logging, returning error response)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	    }

        @GetMapping("/latest-articles")
        public ResponseEntity<List<ArticlesDto>> latestarticles() throws Exception{
             
             
              try {
               
                List<Articles> articles = articleService.getLast6Articles();
               List<ArticlesDto> listeArticlesDto = articles.stream()
                       .map(article -> {
                           ArticlesDto articlesDto = new ArticlesDto();
                           articlesDto.setId(article.getId());
                           articlesDto.setTitre(article.getTitre());
                           articlesDto.setSoustitre(article.getSoustitre());
                           articlesDto.setContenus(article.getContenus());
                           articlesDto.setDatepublication(article.getDatepublication());
                           articlesDto.setSary(article.getSary());
                           articlesDto.setCategorie(article.getCategorie().getNomCategorie());
                           return articlesDto;
                       })
                       .collect(Collectors.toList());
               
               return ResponseEntity.ok(listeArticlesDto);
           } catch (Exception e) {
               // Handle exceptions appropriately (e.g., logging, returning error response)
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
           }
           }

        @GetMapping("/categories")
        public ResponseEntity<List<CategoriesDto>> categories() throws Exception{
             
             
              try {
               List<Categories> listeCategories = categorieService.getListCategories();

               List<CategoriesDto> listeCategoriesDto = listeCategories.stream()
               .map(categorie -> {
                CategoriesDto categoriesDto = new CategoriesDto();
                categoriesDto.setId(categorie.getId());
                categoriesDto.setNomCategorie(categorie.getNomCategorie());
                  
                   return categoriesDto;
               })
               .collect(Collectors.toList());
       
       return ResponseEntity.ok(listeCategoriesDto);
           } catch (Exception e) {
               // Handle exceptions appropriately (e.g., logging, returning error response)
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
           }
           }
		@GetMapping("/latest-articles-by-category/{id}")
    public ResponseEntity<List<ArticlesDto>> getLatestArticleByCategory(@PathVariable int id) {


        try{
        Categories categories = categorieService.findCategorie(id);
        List<Articles> articles = articleService.findTop10ByCategorieOrderByDatepublication(categories);
    
        List<ArticlesDto> listeArticlesDto = articles.stream()
        .map(article -> {
            ArticlesDto articlesDto = new ArticlesDto();
            articlesDto.setId(article.getId());
            articlesDto.setTitre(article.getTitre());
            articlesDto.setSoustitre(article.getSoustitre());
            articlesDto.setContenus(article.getContenus());
            articlesDto.setDatepublication(article.getDatepublication());
            articlesDto.setSary(article.getSary());
            articlesDto.setCategorie(article.getCategorie().getNomCategorie());
            return articlesDto;
        })
        .collect(Collectors.toList());

        return ResponseEntity.ok(listeArticlesDto);
} catch (Exception e) {
// Handle exceptions appropriately (e.g., logging, returning error response)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
       

		
    }
	 
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticlesDto> getArticleById(@PathVariable int id) {
        Optional<Articles> article = articleService.getDetailArticles(id);
		ArticlesDto articlesDto = new ArticlesDto();
        if (article.isPresent()) {
			Articles articleObject = article.get();
			
			articlesDto.setId(articleObject.getId());
			articlesDto.setTitre(articleObject.getTitre());
			articlesDto.setSoustitre(articleObject.getSoustitre());
			articlesDto.setContenus(articleObject.getContenus());
			articlesDto.setDatepublication(articleObject.getDatepublication());
	  
		   articlesDto.setSary(articleObject.getSary());
		   return ResponseEntity.ok(articlesDto);
		   
        } else {
            return ResponseEntity.notFound().build();
        }

		
    }
    
	  @GetMapping("/articles/category/{categoryId}")
    public ResponseEntity<Page<ArticlesDto>> getArticlesByCategory(
            @PathVariable Integer categoryId,
            @RequestParam int page,
            @RequestParam int size) {
        Page<Articles> articles = articleService.getArticlesByCategory(categoryId, page, size);
        Page<ArticlesDto> articlesDtoPage = articles.map(article -> {
            ArticlesDto articlesDto = new ArticlesDto();
            articlesDto.setId(article.getId());
            articlesDto.setTitre(article.getTitre());
            articlesDto.setSoustitre(article.getSoustitre());
            articlesDto.setContenus(article.getContenus());
            articlesDto.setDatepublication(article.getDatepublication());
            articlesDto.setSary(article.getSary());
            articlesDto.setCategorie(article.getCategorie().getNomCategorie());
            return articlesDto;
        });
        return ResponseEntity.ok(articlesDtoPage);
       
    }

	 

}
