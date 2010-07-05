package services;

import exceptions.NotImplementedException;
import exceptions.UserExistsException;

public interface IUserService {

	public String getCurrentAuthenticatedUser();

	boolean registerUser(String username, String password, String emailAddress) throws UserExistsException;

	public void setAutoLogin(String autoLoginKey) throws NotImplementedException;
	public String autoLogin(String autoLoginKey) throws NotImplementedException;
}
