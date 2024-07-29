package articles.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import articles.entity.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="evenements")
public class Evenements extends BaseModel{
	
	@Column(nullable=false)
  private String titre;
	  
  @Column(nullable=false)
	  private String soustitre;
	  
	  
	  @Column(nullable=false) /*update type text au niveau base */
	  private String contenus;
	  
	  @Column(nullable=false)
	  private String sary;
	  

	  @Column(nullable=false)
	  private LocalDateTime dateevenement;

	  @ManyToOne
	  @JoinColumn(name = "categorie_id", nullable = false)
	  private Categories categorie;



	public Evenements() {
		  
	  }

	public Evenements(String titre, String soustitre, String contenus, String sary, LocalDateTime dateevenement,Categories categorie) {
		super();
		this.titre = titre;
		this.soustitre = soustitre;
		this.contenus = contenus;
		this.sary = sary;
		this.dateevenement = dateevenement;
		this.categorie = categorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getSoustitre() {
		return soustitre;
	}

	public void setSoustitre(String soustitre) {
		this.soustitre = soustitre;
	}

	public String getContenus() {
		return contenus;
	}

	public void setContenus(String contenus) {
		this.contenus = contenus;
	}

	public String getSary() {
		return sary;
	}

	public void setSary(String sary) {
		this.sary = sary;
	}

	public LocalDateTime getDateevenement() {
		return dateevenement;
	}

	public void setDateevenement(LocalDateTime dateevenement) {
		this.dateevenement = dateevenement;
	}
	public Categories getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}
	  

}
