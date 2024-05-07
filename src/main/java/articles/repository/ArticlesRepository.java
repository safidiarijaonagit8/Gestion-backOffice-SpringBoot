package articles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import articles.entity.Articles;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



@Repository
public interface ArticlesRepository extends JpaRepository <Articles, Integer>{
	 Page<Articles> findByTitreContainingIgnoreCase(String keyword, Pageable pageable);

}
