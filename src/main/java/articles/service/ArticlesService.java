package articles.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

import articles.entity.Articles;
import org.springframework.data.domain.Page;

public interface ArticlesService {

	Articles saveArticle(Articles article);
	
	List<Articles> getListArticles();
	
	 public void updateArticle(Integer ArticleId,Articles articleEdit);
}
