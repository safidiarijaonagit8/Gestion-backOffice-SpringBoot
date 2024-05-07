package articles.entity;

import java.sql.Date;

import articles.entity.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name="evenements")
public class Evenements extends BaseModel{
	
  private String titre;
	  
	  private String soustitre;
	  
	  
	  @Column(length=1000)
	  private String contenus;
	  
	  
	  private String sary;
	  
	  private Date dateevenement;

	public Evenements(String titre, String soustitre, String contenus, String sary, Date dateevenement) {
		super();
		this.titre = titre;
		this.soustitre = soustitre;
		this.contenus = contenus;
		this.sary = sary;
		this.dateevenement = dateevenement;
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

	public Date getDateevenement() {
		return dateevenement;
	}

	public void setDateevenement(Date dateevenement) {
		this.dateevenement = dateevenement;
	}
	  
	  

}
