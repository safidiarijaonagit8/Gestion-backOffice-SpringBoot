package articles.dto;

import java.sql.Date;

public class CategoriesDto {
    private Integer id;
	
    
    private String nomCategorie;

    
	public CategoriesDto() {
		super();
	}

	public CategoriesDto(Integer id, String nomCategorie) {
		super();
		this.id = id;
		this.nomCategorie = nomCategorie;
		
	}
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomCategorie() {
		return this.nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

}
