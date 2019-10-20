package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repo;
	
	public Person create(Person person) {
		return repo.save(person);
	}
	
	public Person update(Person person) {
		Person entity = repo.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + person.getId()));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repo.save(entity);
	}
	
	public void delete(Long id) {
		Person entity = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + id));
		repo.delete(entity);
	}
	
	public Person findById(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + id));
	}
	
	public List<Person> findAll() {
		return repo.findAll();
	}

	
}
