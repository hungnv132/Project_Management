package com.hungnv132.core.support;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.User;

@Transactional(rollbackFor = Exception.class)
public class JoinedUserDetailsService  implements UserDetailsService {

	final Logger logger = LogManager.getLogger(JoinedUserDetailsService.class) ;
	
	@Inject
	UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
		if(!StringUtils.hasText(email)){
			throw new  UsernameNotFoundException("Email không được trống");
		}
		User  user = userDAO.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("Email không tồn tại");
		}
		logger.debug("+++++++ Password: "+ user.getPassword());
		logger.debug("+++++++ Frist Name: "+ user.getFirstName());	
		return new JoinedUser(user);
	}
	
}
