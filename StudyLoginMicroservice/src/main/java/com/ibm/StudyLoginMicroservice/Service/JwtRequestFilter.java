package com.ibm.StudyLoginMicroservice.Service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ibm.StudyLoginMicroservice.Login.LoginController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class JwtRequestFilter  extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService myUserDetailsService ; 
	@Autowired
	private JwtUtil jwtUtil;
	
	Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain  ) throws ServletException, IOException {
		
		logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 1");
		
		String authorizationHeader = request.getHeader("Authorization");
		
		logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 2");
		
		String sessionauthorizationHeader = null;
		/*
		if(null != authorizationHeader ) {
		logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 3"+authorizationHeader);
		}
		else {
			logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 3.1");	
			if(null != request.getSession()) {
				logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 3.2");	
				if(null != request.getSession().getAttribute("Authorization")) {
					logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 3.3");
					sessionauthorizationHeader= request.getSession().getAttribute("Authorization").toString();
					logger.info("Inside JwtRequestFilter  sessionauthorizationHeader "+ sessionauthorizationHeader);
					
					authorizationHeader = "Bearer " + sessionauthorizationHeader;
					
					logger.info("Inside JwtRequestFilter  authorizationHeader "+ authorizationHeader);
				}
			}
			
			
		}
		
		*/
		if(null != request.getSession()) {
			logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 3.2");	
			if(null != request.getSession().getAttribute("Authorization")) {
				logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 3.3");
				sessionauthorizationHeader= request.getSession().getAttribute("Authorization").toString();
				logger.info("Inside JwtRequestFilter  sessionauthorizationHeader "+ sessionauthorizationHeader);
				
				authorizationHeader = "Bearer " + sessionauthorizationHeader;
				
				logger.info("Inside JwtRequestFilter  authorizationHeader "+ authorizationHeader);
			}
		}
		
		String username = null;
		String jwt = null;
		
		logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 4");
		if((null != authorizationHeader)&&(authorizationHeader.startsWith("Bearer "))){
			logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 5");
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}
		
		logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 6");
		if((null != username )&&(SecurityContextHolder.getContext().getAuthentication() == null)) {
			logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 7");
			UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
			if(jwtUtil.validateToken(jwt, userDetails)) {
				logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 8");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 9");
			}
			
			logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 10");
		}
		logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 11");
		chain.doFilter(request, response);
		logger.info("Inside JwtRequestFilter  inside JwtRequestFilter 12");
	}
}
