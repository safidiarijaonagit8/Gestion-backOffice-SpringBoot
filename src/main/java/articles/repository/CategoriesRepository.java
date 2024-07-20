package articles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import articles.entity.Categories;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoriesRepository extends JpaRepository <Categories, Integer>{

}
