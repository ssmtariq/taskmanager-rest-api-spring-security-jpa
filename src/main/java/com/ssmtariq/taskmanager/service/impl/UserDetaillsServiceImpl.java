package com.ssmtariq.taskmanager.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssmtariq.taskmanager.model.User;
import com.ssmtariq.taskmanager.repository.UserRepository;

/**
 * @author SSM Tariq
 */

@Service
public class UserDetaillsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getPassword(),
				getGrantedAuthority(user.get()));
	}

	private Collection<GrantedAuthority> getGrantedAuthority(User user) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (user.getRole().equalsIgnoreCase("admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

	public List<User> listAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public User get(Integer id) {
		return userRepository.findById(id).get();
	}

	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
	
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User>user = userRepository.findByUserName(((UserDetails)principal).getUsername());
		return user.get();
//		if (principal instanceof User) {
//		String username = ((UserDetails)principal).getUsername();
//		} else {
//		String username = principal.toString();
//		}
	}
}
