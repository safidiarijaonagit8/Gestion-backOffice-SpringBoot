package articles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import articles.entity.Articles;
import articles.entity.Categories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



@Repository
public interface ArticlesRepository extends JpaRepository <Articles, Integer>{
	 Page<Articles> findByTitreContainingIgnoreCase(String keyword, Pageable pageable);

	 List<Articles> findTop6ByOrderByDatepublicationDesc();

	 List<Articles> findAllByOrderByDatepublicationDesc();

	 List<Articles> findTop10ByCategorieOrderByDatepublicationDesc(Categories categorie);

	 Page<Articles> findByCategorieIdOrderByDatepublicationDesc(Integer categoryId, Pageable pageable);



}
