package articles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {
	
	private Long id;
	
    @NotEmpty
    private String firstName;
    
    @NotEmpty
    private String lastName;
    
    @NotEmpty(message = "Email ne doit pas être vide")
    @Email
    private String email;
    
    @NotEmpty(message = "Mot de passe ne doit pas être vide")
    private String password;

	public UserDto() {
		super();
	}

	public UserDto(Long id, @NotEmpty String firstName, @NotEmpty String lastName,
			@NotEmpty(message = "Email ne doit pas être vide") @Email String email,
			@NotEmpty(message = "Mot de passe ne doit pas être vide") String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    

}
