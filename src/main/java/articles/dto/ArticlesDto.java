package articles.dto;

import java.sql.Date;

import org.springframework.http.ResponseEntity;

import jakarta.validation.constraints.NotEmpty;

public class ArticlesDto {
	private Integer id;
	
    
    private String titre;
    
    
    private String soustitre;
    
    private String contenus;
    
   // private ResponseEntity<byte[]> sary;
    private String sary;
    
    private Date datepublication;

	public ArticlesDto() {
		super();
	}

	public ArticlesDto(Integer id, String titre, String soustitre, String contenus, String sary,
			Date datepublication) {
		super();
		this.id = id;
		this.titre = titre;
		this.soustitre = soustitre;
		this.contenus = contenus;
		this.sary = sary;
		this.datepublication = datepublication;
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
    
    
	

}
