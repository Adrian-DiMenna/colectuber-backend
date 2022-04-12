package com.is.cole.services.userDetails;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.is.cole.daos.IUserDao;
import com.is.cole.entities.Usuarios;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserDao userDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios user= userDao.findByCorreo(username);
		return new User(user.getCorreo(),user.getPassword(),new ArrayList<>());
	}



}
