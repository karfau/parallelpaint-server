package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/*
 * Named Queries for Entity PPUser
 */

@NamedQueries({
	@NamedQuery(name  = "PPUser.findPPUserById",
			query =	"SELECT ppu " +
						"FROM PPUser ppu " +
						"WHERE ppu.id = :id"),

	@NamedQuery(name  = "PPUser.findPPUserByUsername",
			query =	"SELECT ppu " +
						"FROM PPUser ppu " +
						"WHERE ppu.username = :username"),

	@NamedQuery(name  = "PPUser.findPPUserByAutoLogin",
			query =	"SELECT ppu " +
						"FROM PPUser ppu " +
						"WHERE ppu.autoLogin = :autoLogin"),

	@NamedQuery(name  = "PPUser.findPPUserByEmailAddress",
			query =	"SELECT ppu " +
						"FROM PPUser ppu " +
						"WHERE ppu.emailAddress = :emailAddress")
	})

/**
 * Entity implementation class for Entity: PPUser
 *
 */
@Entity
public class PPUser implements Serializable {

	public static final String ANONYMOUS_USER_NAME = "anonymousUser";

	public static String ANONYMOUS_AUTHOR(final long drawingId){
		return "anonymousAuthor"+drawingId; // dynamische Konstante!
	}

	private int id;
	private String username;
	private String password;
	private String autoLogin = "";
	private String emailAddress;
	// noetig fuer getAuthority()-Methode des Interface org.springframework.security.core.GrantedAuthority
	private Set<String> roles = new HashSet<String>();
	private static final long serialVersionUID = 1L;
	public PPUser() {
		super();
	}

	public PPUser(String username, String password, String emailAddress) {
		super();
		this.username 		= username;
		this.password 		= password;
		this.emailAddress	= emailAddress;
		// jeder neuer User besitzt erst einmal per Default die Rolle 'ROLE_USER'
		this.roles.add("ROLE_USER");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
 		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getUsername() {
 		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
 		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getAutoLogin() {
		return autoLogin;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	// neu in JPA 2
	@ElementCollection
	public Set<String> getRoles() {
		return roles;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

} // class
