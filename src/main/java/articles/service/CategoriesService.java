package articles.service;

import java.util.List;

import articles.entity.Articles;
import articles.entity.Categories;

public interface CategoriesService {


   List<Categories> getListCategories();

   public Categories findCategorie(int id);
}
