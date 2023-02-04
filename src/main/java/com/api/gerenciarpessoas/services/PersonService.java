package com.api.gerenciarpessoas.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.gerenciarpessoas.models.AddressModel;
import com.api.gerenciarpessoas.models.PersonModel;
import com.api.gerenciarpessoas.repositorys.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public PersonModel savePerson(PersonModel personModel) {
		return personRepository.save(personModel);
	}
	
	public List<PersonModel> listAllPerson(){
		return personRepository.findAll();
	}
	
	public Optional<PersonModel> findPersonById(Long id) {
		return personRepository.findById(id);
	}

	public void removePersonById(Long id) {
		personRepository.deleteById(id);		
	}

}
	