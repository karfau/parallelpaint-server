package services;

import java.util.ArrayList;
import java.util.Collection;

import model.PPUser;
import model.dao.IUserDao;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PPUserDetailsService implements UserDetailsService {
	/*
	 * ausschliesslich mit dem Interface arbeiten, um spaeter evtl. die
	 * Interface-implementierende Klasse *nur* durch Aenderung der Spring-Config
	 * aendern zu koennen!
	 */
	private IUserDao userDaoBean;

	@Required
	public void setUserDaoBean(IUserDao userDaoBean) {
		this.userDaoBean = userDaoBean;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		// Domain-User aus der DB laden
		PPUser ppu;
		try {
			ppu = userDaoBean.loadUserByUsername(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("wrong USERNAME or password.");
		}

		// ermitteln der dem User zugewiesenen Rollen
		// und Konstruktion einer neuen Collection zur Uebergabe an Konstruktor
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (String role : ppu.getRoles()) {
			grantedAuthorities.add(new GrantedAuthorityImpl(role));
		} // for

		// SpringSecurity-User erzeugen (implementiert das Interface UserDetails)
		User authenticatedUser = new User(ppu.getUsername(), ppu.getPassword(), true, true, true, true, grantedAuthorities);

		return authenticatedUser;
	} // loadUserByUsername

} // interface
