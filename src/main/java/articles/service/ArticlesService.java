package articles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import articles.entity.Articles;
import articles.entity.Categories;

import org.springframework.data.domain.Page;

public interface ArticlesService {

	Articles saveArticle(Articles article);
	
	List<Articles> getListArticles();
	
	 public void updateArticle(Integer ArticleId,Articles articleEdit);

	 Optional<Articles> getDetailArticles(int idArticle);

	 public List<Articles> getLast6Articles();

	 public List<Articles> findTop10ByCategorieOrderByDatepublication(Categories categorie);

	 public Page<Articles> getArticlesByCategory(Integer categoryId, int page, int size);

}
