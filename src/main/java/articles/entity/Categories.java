package articles.entity;
import java.sql.Date;
import java.util.List;

import articles.entity.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="categories")
public class Categories extends BaseModel{


@Column(nullable=false)
	  private String nomCategorie;

      @OneToMany(mappedBy = "categorie")
    private List<Articles> articles;


    @OneToMany(mappedBy = "categorie")
    private List<Evenements> evenements;

      public Categories() {
		  
	  }

      public Categories(String nomCategorie,List<Articles> articles,List<Evenements> evenements) {
		super();
		this.nomCategorie = nomCategorie;
        this.articles = articles;
        this.evenements = evenements;
		}

        public String getNomCategorie() {
            return nomCategorie;
        }
    
        public void setNomCategorie(String nomCategorie) {
            this.nomCategorie = nomCategorie;
        }

        public List<Articles> getArticles()
        {
            return this.articles;
        }

        public void setArticles(List<Articles> articles)
        {
            this.articles = articles;
        }
        public List<Evenements> getEvenements()
        {
            return this.evenements;
        }

        public void setEvenements(List<Evenements> evenements)
        {
            this.evenements = evenements;
        }


}
