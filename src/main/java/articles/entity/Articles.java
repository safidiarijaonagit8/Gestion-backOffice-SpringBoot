package articles.entity;

import java.sql.Date;

import articles.entity.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="articles")
public class Articles extends BaseModel{
	
	@Column(nullable=false)
	  private String titre;
	  
	@Column(nullable=false)
	  private String soustitre;
	  
	  
	  @Column(nullable=false)
	  private String contenus;
	  
	  @Column(nullable=false)
	  private String sary;
	  
	  @Column(nullable=false)
	  private Date datepublication;

	  

	   @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categories categorie;
	  
	  public Articles() {
		  
	  }
	  /*test diff in git*/

	public Articles(String titre, String soustitre, String contenus, String sary, Date datepublication,Categories categorie) {
		super();
		this.titre = titre;
		this.soustitre = soustitre;
		this.contenus = contenus;
		this.sary = sary;
		this.datepublication = datepublication;
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

	public Date getDatepublication() {
		return datepublication;
	}

	public void setDatepublication(Date datepublication) {
		this.datepublication = datepublication;
	}
	  
	public Categories getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}

}
