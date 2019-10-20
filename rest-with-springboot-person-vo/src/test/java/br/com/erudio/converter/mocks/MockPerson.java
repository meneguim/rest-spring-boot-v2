package br.com.erudio.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;

public class MockPerson {
	
	public Person mockEntity() {
		return mockEntity(0);
	}
	
	public PersonVO mockVO() {
		return mockVO(0);
	}
	
	public List<Person> mockEntityList() {
		List<Person> persons = new ArrayList<Person>();
		for (int i=0;i < 14;i++ ) {
			persons.add(mockEntity());
		}
		return persons;
	}
	
	public List<PersonVO> mockVOList() {
		List<PersonVO> personsVO = new ArrayList<PersonVO>();
		for (int i=0;i < 14;i++ ) {
			personsVO.add(mockVO());
		}
		return personsVO;
	}
	
	private Person mockEntity(Integer number) {
		Person person = new Person();
		person.setAddress("Address test" + number);
		person.setFirstName("First Name teste" + number);
		person.setGender(((number%2)==0) ? "Masculino" : "Feminino");
		person.setId(number.longValue());
		person.setLastName("Last Name teste" + number);
		return person;
	}
	
	private PersonVO mockVO(Integer number) {
		PersonVO person = new PersonVO();
		person.setAddress("Address test" + number);
		person.setFirstName("First Name teste" + number);
		person.setGender(((number%2)==0) ? "Masculino" : "Feminino");
		person.setId(number.longValue());
		person.setLastName("Last Name teste" + number);
		return person;
	}
}
