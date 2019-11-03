package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Person Endpoint", 
	description = "Descrição do Endpoint Person", 
	tags = {"PersonEndpoint"})
@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@ApiOperation(value = "Busca pessoa por ID")
	@GetMapping(value = "/{id}",produces = {"application/json","application/xml","application/x-yaml"})
	public PersonVO findById( @PathVariable("id") Long id) 	{
		PersonVO personVO = personService.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Busca todas as pessoas")
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public List<PersonVO> findAll() {
		List<PersonVO> persons = personService.findAll();
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}
	
	@ApiOperation(value = "Nova pessoa")
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
				 consumes = {"application/json","application/xml","application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO person) 	{
		PersonVO personVO = personService.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Atualização de pessoa")
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"},
				consumes = {"application/json","application/xml","application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) 	{
		PersonVO personVO = personService.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Exclusão de pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) 	{
		personService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
