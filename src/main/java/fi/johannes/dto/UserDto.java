package fi.johannes.dto;

public class UserDto {

	String login;
	String name;
	// TODO Should probably have token

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public UserDto() {
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
