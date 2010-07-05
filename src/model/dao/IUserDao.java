package model.dao;

import model.PPUser;
import exceptions.UserExistsException;

public interface IUserDao {

	public PPUser loadUserById(int userId);

	public PPUser loadUserByUsername (String username);
	public PPUser loadUserByAutoLogin(String autoLoginKey);

	public boolean saveUser(PPUser newUser) throws UserExistsException;

}
