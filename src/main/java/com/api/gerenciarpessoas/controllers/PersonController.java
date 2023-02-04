package com.api.gerenciarpessoas.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

import com.api.gerenciarpessoas.models.AddressModel;
import com.api.gerenciarpessoas.models.PersonModel;
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
	public PersonModel save(@RequestBody PersonModel personModel) {
		return personService.savePerson(personModel);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PersonModel> listAll() {
		return personService.listAllPerson();
	}

	@GetMapping("/{id}/Address")
	@ResponseStatus(HttpStatus.OK)
	public List<AddressModel> findAdrressByPerson(@PathVariable Long id) {
	    PersonModel person = personService.findPersonById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	    if (person == null) {
	        return Collections.emptyList();
	    }
	    return person.getAddress().stream().map(address -> {
	        AddressModel model = new AddressModel();
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
	public void addAddressToPerson(@PathVariable Long id, @RequestBody AddressModel addressModel) {
	    PersonModel person = personService.findPersonById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	    if (person == null) {
	        return;
	    }
	    AddressModel address = new AddressModel();
	    address.setCity(addressModel.getCity());
	    address.setCep(addressModel.getCep());
	    address.setStreet(addressModel.getStreet());
	    address.setNumber(addressModel.getNumber());
	    address.setMain(addressModel.getMain());
	    address.setPerson(person);
	    addressService.saveAddress(address);
	}

	@GetMapping("/{id}/main")
	@ResponseStatus(HttpStatus.OK)
	public AddressModel findMainAddressOfPersonById(@PathVariable("id") Long id) {
	    PersonModel person = personService.findPersonById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	    List<AddressModel> addresses = person.getAddress();
	    for (AddressModel address : addresses) {
	        if (address.getMain()) {
	            return address;
	        }
	    }
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PersonModel findPersonById(@PathVariable("id") Long id) {
		return personService.findPersonById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePersonById(@PathVariable("id") Long id) {
		personService.findPersonById(id).map(person -> {
			personService.removePersonById(person.getId());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void attPerson(@PathVariable("id") Long id, @RequestBody PersonModel personModel) {
		personService.findPersonById(id).map(personBase -> {
			modelMapper.map(personModel, personBase);
			personService.savePerson(personBase);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

}
