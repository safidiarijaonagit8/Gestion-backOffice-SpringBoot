package articles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import articles.entity.Articles;
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
		 return articlesRepository.findAll();
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
			   
		   articlesRepository.save(article);
		}
	 
	
}
