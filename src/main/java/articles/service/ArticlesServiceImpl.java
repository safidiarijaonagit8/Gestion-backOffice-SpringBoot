package articles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import articles.entity.Articles;
import articles.entity.Categories;
import articles.repository.ArticlesRepository;

@Service
public class ArticlesServiceImpl implements ArticlesService{
	
	 @Autowired
	    private ArticlesRepository articlesRepository;
	 
	 @Override
	    public Articles saveArticle(Articles article)
	    {
	        return articlesRepository.save(article);
	    }

	 @Override
	 public List<Articles> getListArticles()
	 {
		 return articlesRepository.findAllByOrderByDatepublicationDesc();
	 }
	 @Override
	 public List<Articles> findTop10ByCategorieOrderByDatepublication(Categories categorie)
	 {
		 return articlesRepository.findTop10ByCategorieOrderByDatepublicationDesc(categorie);
	 }
	 @Override
	 public Optional<Articles> getDetailArticles(int idArticle)
	 {
		 return articlesRepository.findById(idArticle);
	 }
	 
	 @Override
	 public void updateArticle(Integer ArticleId, Articles articleEdit) {
		 Articles article = articlesRepository.findById(ArticleId).get();
		   article.setTitre(articleEdit.getTitre());
		   article.setSoustitre(articleEdit.getSoustitre());
		   article.setContenus(articleEdit.getContenus());
		   if(!articleEdit.getSary().isBlank())
		   {
			   article.setSary(articleEdit.getSary());
		   }
		   
		   	article.setCategorie(articleEdit.getCategorie());
		   articlesRepository.save(article);
		}

		@Override
		public List<Articles> getLast6Articles() {
			return articlesRepository.findTop6ByOrderByDatepublicationDesc();
		}
	 
		@Override
		public Page<Articles> getArticlesByCategory(Integer categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("datepublication").descending());
        return articlesRepository.findByCategorieIdOrderByDatepublicationDesc(categoryId, pageable);
    }
	
}
