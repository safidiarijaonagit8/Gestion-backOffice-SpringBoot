package articles.dto;

import java.sql.Date;

import org.springframework.http.ResponseEntity;

import articles.entity.Categories;
import jakarta.validation.constraints.NotEmpty;

public class ArticlesDto {
	private Integer id;
	
    
    private String titre;
    
    
    private String soustitre;
    
    private String contenus;
    
   // private ResponseEntity<byte[]> sary;
    private String sary;
    
    private Date datepublication;

	private String categorie;

	public ArticlesDto() {
		super();
	}

	public ArticlesDto(Integer id, String titre, String soustitre, String contenus, String sary,
			Date datepublication, String categorie) {
		super();
		this.id = id;
		this.titre = titre;
		this.soustitre = soustitre;
		this.contenus = contenus;
		this.sary = sary;
		this.datepublication = datepublication;
		this.categorie = categorie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
    
    public String getCategorie()
	{
		return this.categorie;
	}
	public void setCategorie(String categories)
	{
		this.categorie = categories;
	}
	

}
