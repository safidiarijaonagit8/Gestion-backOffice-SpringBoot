package articles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import articles.entity.Articles;
import articles.entity.Categories;
import articles.repository.ArticlesRepository;
import articles.repository.CategoriesRepository;
import org.springframework.stereotype.Service;


@Service
public class CategoriesServiceImpl implements CategoriesService{
        @Autowired
	    private CategoriesRepository categoriesRepository;

         @Override
	 public List<Categories> getListCategories()
	 {
		 return categoriesRepository.findAll();
	 }
	 @Override
	 public Categories findCategorie(int id)
	 {
		 return categoriesRepository.findById(id).get();
	 }

}
