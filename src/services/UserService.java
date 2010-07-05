package services;

import model.PPUser;
import model.dao.IUserDao;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import exceptions.NotImplementedException;
import exceptions.UserExistsException;

public class UserService implements IUserService{

	/*
	 * ausschliesslich mit dem Interface arbeiten, um spaeter evtl. die
	 * Interface-implementierende Klasse *nur* durch Aenderung der Spring-Config
	 * aendern zu koennen!
	 */
	private IUserDao userDaoBean;


	/*
	 * Gegen Interfaces programmieren (relevant fuer AOP Load-Time-Weaving)!
	 * Siehe auch: http://www.coderanch.com/t/485150/Spring/Exception-while-deploying
	 */
	@Required
	public void setUserDaoBean(IUserDao userDaoBean) {
		this.userDaoBean = userDaoBean;
	} // setUserDaoBean


	private UserDetailsService securityService;

	@Required
	public void setSecurityService(UserDetailsService securityService) {
		this.securityService = securityService;
	}

	@Override
	public String getCurrentAuthenticatedUser() {
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  'currentUser' is: " + currentUser);
		if(currentUser.equalsIgnoreCase(PPUser.ANONYMOUS_USER_NAME))
			return null;
		else
			return SecurityContextHolder.getContext().getAuthentication().getName();
	} // getCurrentAuthenticatedUser


	public boolean registerUser(String username, String password, String emailAddress) throws UserExistsException {
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  'currentUser' is: " + currentUser);
		PPUser newUser = new PPUser(username, password, emailAddress);
		return userDaoBean.saveUser(newUser);
	} // registerUser

	public void setAutoLogin(String autoLoginKey) throws NotImplementedException{
		throw new NotImplementedException();
		//load user from db

		//set data

		//persist(DAO: merge)
	}

	public String autoLogin(String autoLoginKey) throws NotImplementedException{
		//throw new NotImplementedException();
		//load user from db (DAO: use extra Named Query)
		PPUser user = userDaoBean.loadUserByAutoLogin(autoLoginKey);
		if(user != null)
			return authenticateUserByUserName(user.getUsername()).getUsername();
		else
			return null;
	}

	/**
	 * Trys to find the user in the DB using the {@link UserDetailsService} and
	 * authenticates it if sucessfull.
	 * Source: http://forum.springsource.org/showthread.php?t=67425
	 *
	 * @param user
	 * @return the created {@link UserDetails} if the user was found and logged in, null otherwise;
	 */
	private UserDetails authenticateUserByUserName(String username) {
		UserDetails user;
		try {
			user = securityService.loadUserByUsername(username);
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
			authentication.setAuthenticated(true);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (DataAccessException dae) {
			System.out.println(this+" CATCHED "+dae+" when trying to loadUserByUsername for username "+username);
			return null;
		} catch (UsernameNotFoundException unfe) {
			System.out.println(this+" CATCHED "+unfe+" when trying to loadUserByUsername for username "+username);
			return null;
		} catch (IllegalArgumentException iae) {
			System.out.println(this+" CATCHED "+iae+" when trying to manually authenticate user.");
			return null;
		}
		return user;

	}


} // class
