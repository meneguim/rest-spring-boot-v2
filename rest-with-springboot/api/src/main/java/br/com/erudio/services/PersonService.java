package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.serialization.converter.DozerConverter;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repo;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(repo.save(entity), PersonVO.class);
		return vo;
	}
	
	public Page<PersonVO> findAll(Pageable pageable) {
		var page = repo.findAll(pageable);
		return page.map(this::convertToPersonVO);
	}
	
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
		var page = repo.findPersonByName(firstName, pageable);
		return page.map(this::convertToPersonVO);
	}
	
	private PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(repo.save(entity), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		var entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + id));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) {
		var entity = repo.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + person.getId()));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerConverter.parseObject(repo.save(entity), PersonVO.class);
		return vo;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		repo.disablePerson(id);
		var entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + id));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public void delete(Long id) {
		Person entity = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + id));
		repo.delete(entity);
	}
	


	
}
