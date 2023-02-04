package com.api.gerenciarpessoas.controllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.gerenciarpessoas.entities.AddressEntity;
import com.api.gerenciarpessoas.entities.PersonEntity;
import com.api.gerenciarpessoas.services.AddressService;
import com.api.gerenciarpessoas.services.PersonService;

@RestController
@RequestMapping("/Person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressService addressService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PersonEntity save(@RequestBody PersonEntity personModel) {
		return personService.save(personModel);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PersonEntity> listAll() {
		return personService.listAll();
	}

	@GetMapping("/{id}/Address")
	@ResponseStatus(HttpStatus.OK)
	public List<AddressEntity> findAdrressByPerson(@PathVariable Long id) {
		PersonEntity person = personService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
		if (person == null) {
			return Collections.emptyList();
		}
		return person.getAddress().stream().map(address -> {
			AddressEntity model = new AddressEntity();
			model.setId(address.getId());
			model.setCity(address.getCity());
			model.setCep(address.getCep());
			model.setStreet(address.getStreet());
			model.setNumber(address.getNumber());

			return model;
		}).collect(Collectors.toList());
	}

	@PostMapping("/{id}/Address")
	@ResponseStatus(HttpStatus.CREATED)
	public void addAddressToPerson(@PathVariable Long id, @RequestBody AddressEntity addressModel) {
		PersonEntity person = personService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
		if (person == null) {
			return;
		}
		AddressEntity address = new AddressEntity();
		address.setCity(addressModel.getCity());
		address.setCep(addressModel.getCep());
		address.setStreet(addressModel.getStreet());
		address.setNumber(addressModel.getNumber());
		address.setMain(addressModel.getMain());
		address.setPerson(person);
		addressService.save(address);
	}

	@GetMapping("/{id}/main")
	@ResponseStatus(HttpStatus.OK)
	public AddressEntity findMainAddressOfPersonById(@PathVariable("id") Long id) {
		PersonEntity person = personService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
		List<AddressEntity> addresses = person.getAddress();
		for (AddressEntity address : addresses) {
			if (address.getMain()) {
				return address;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PersonEntity findPersonById(@PathVariable("id") Long id) {
		return personService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePersonById(@PathVariable("id") Long id) {
		personService.findById(id).map(person -> {
			personService.removeById(person.getId());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void attPerson(@PathVariable("id") Long id, @RequestBody PersonEntity personModel) {
		personService.findById(id).map(personBase -> {
			modelMapper.map(personModel, personBase);
			personService.save(personBase);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

}
