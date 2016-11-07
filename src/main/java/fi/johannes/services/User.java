package fi.johannes.services;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class User {

	@GeneratedValue
	long id;
	
	String login;
	String name;
	String email;
	
	public User() {
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	
	
}
