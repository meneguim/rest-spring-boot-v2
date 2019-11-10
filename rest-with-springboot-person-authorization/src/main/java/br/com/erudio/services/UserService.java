package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.UserRepository;
import br.com.erudio.serialization.converter.DozerConverter;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repo.findByUsername(username);
		if (user != null) {
			return user;
		} 
		else {
			throw new UsernameNotFoundException("Username: " + username + " não localizado");
		}
	}
		
}
