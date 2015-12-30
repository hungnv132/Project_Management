package com.hungnv132.core.support;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hungnv132.core.domain.User;

public class JoinedUser  extends User implements UserDetails {
	final Logger logger = LogManager.getLogger(JoinedUser.class) ;
	
	private Collection<GrantedAuthority> authorities;
	
	public JoinedUser(User user){
		BeanUtils.copyProperties(user, this);
		Collection<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+user.getRole());
		authorityList.add(authority);
		this.authorities = authorityList;
	}
	
	
	public String getFullName() {
		return this.getFirstName()+ " " + this.getMidName() + " " + this.getLastName();
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}


	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
