package articles.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class EvenementsDto {
    private Integer id;
	
    
    private String titre;
    
    
    private String soustitre;
    
    private String contenus;
    
   // private ResponseEntity<byte[]> sary;
    private String sary;
    
    private LocalDateTime dateevenement;

	private String categorie;

	public EvenementsDto() {
		super();
	}

	public EvenementsDto(Integer id, String titre, String soustitre, String contenus, String sary,
    LocalDateTime dateevenement, String categorie) {
		super();
		this.id = id;
		this.titre = titre;
		this.soustitre = soustitre;
		this.contenus = contenus;
		this.sary = sary;
		this.dateevenement = dateevenement;
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

	public LocalDateTime getDateevenement() {
		return dateevenement;
	}

	public void setDateevenement(LocalDateTime dateevenement) {
		this.dateevenement = dateevenement;
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
