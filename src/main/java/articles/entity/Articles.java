package articles.entity;

import java.sql.Date;

import articles.entity.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	  
	  public Articles() {
		  
	  }

	public Articles(String titre, String soustitre, String contenus, String sary, Date datepublication) {
		super();
		this.titre = titre;
		this.soustitre = soustitre;
		this.contenus = contenus;
		this.sary = sary;
		this.datepublication = datepublication;
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
	  
	

}
