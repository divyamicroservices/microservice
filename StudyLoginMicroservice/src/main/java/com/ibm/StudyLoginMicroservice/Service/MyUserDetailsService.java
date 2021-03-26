package com.ibm.StudyLoginMicroservice.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.StudyLoginMicroservice.Login.Login;
import com.ibm.StudyLoginMicroservice.Login.LoginController;
import com.ibm.StudyLoginMicroservice.Login.LoginJPARepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MyUserDetailsService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	@Autowired
	private LoginJPARepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		Login temp = new Login();
		Optional<Login> oLogin =repo.findById(username);
		if(oLogin.isPresent())
		{
			temp= oLogin.get();
		}
		
		logger.info("Inside MyUserDetailsService   temp.getUsername() --> "+ temp.getUsername());
		logger.info("Inside MyUserDetailsService   temp.getPassword() -->"+ temp.getPassword());
		return new User(temp.getUsername(),temp.getPassword(),new ArrayList<>());
	}
}
