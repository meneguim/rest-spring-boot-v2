package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;
	
	@ApiOperation(value = "Busca pessoa por ID")
	@GetMapping(value = "/{id}",produces = {"application/json","application/xml","application/x-yaml"})
	public PersonVO findById( @PathVariable("id") Long id) 	{
		PersonVO personVO = personService.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Busca todas as pessoas")
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "15") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"firstName"));
		
		Page<PersonVO> persons = personService.findAll(pageable);
		
		persons
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
				)
			);
		
		PagedResources<?> resources = assembler.toResource(persons);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Busca pessoas por partes de nomes")
	@GetMapping(value = "/findPersonByName/{firstName}",
				produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "15") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"firstName"));
		
		Page<PersonVO> persons = personService.findPersonByName(firstName, pageable);
		
		persons
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(PersonController.class).findById(p.getKey()))
					.withSelfRel()
					)
				);
		PagedResources<?> resources = assembler.toResource(persons);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
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
	
	@ApiOperation(value = "Inativacao de pessoa")
	@PatchMapping(value = "/{id}",produces = {"application/json","application/xml","application/x-yaml"})
	public PersonVO disablePerson( @PathVariable("id") Long id) 	{
		PersonVO personVO = personService.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
}
