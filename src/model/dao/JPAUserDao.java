package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.PPUser;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import exceptions.UserExistsException;


@Repository
public class JPAUserDao implements IUserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PPUser loadUserById(int userId) {
		return (PPUser) entityManager.createNamedQuery("PPUser.findPPUserById")
									 .setParameter("id", userId)
		                             .getSingleResult();
	} // loadUserById

	public PPUser loadUserByUsername (String username) {
		return (PPUser) entityManager.createNamedQuery("PPUser.findPPUserByUsername")
		                             .setParameter("username", username)
		                             .getSingleResult();
	} // loadUserByUsername

	@Override
	public PPUser loadUserByAutoLogin(String autoLoginKey) {
		return (PPUser) entityManager.createNamedQuery("PPUser.findPPUserByAutoLogin")
      .setParameter("autoLogin", autoLoginKey)
      .getSingleResult();
	}

	// TODO: refactor me! -> besser DAO + create-Methode ???
	@Transactional
	public boolean saveUser(PPUser newUser) throws UserExistsException {

		Query query = entityManager.createNamedQuery("PPUser.findPPUserByEmailAddress");
		query.setParameter("emailAddress", newUser.getEmailAddress());
		// User allready exists
		if (query.getResultList().size() != 0) {
			throw new UserExistsException();
		}
		entityManager.persist(newUser);
		entityManager.flush();
		entityManager.refresh(newUser);
		return entityManager.contains(newUser);
	} // saveUser


} // class
