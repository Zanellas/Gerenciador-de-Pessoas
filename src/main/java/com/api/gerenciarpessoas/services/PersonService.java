package com.api.gerenciarpessoas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.gerenciarpessoas.entities.PersonEntity;
import com.api.gerenciarpessoas.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public PersonEntity save(PersonEntity personModel) {
		return personRepository.save(personModel);
	}

	public List<PersonEntity> listAll() {
		return personRepository.findAll();
	}

	public Optional<PersonEntity> findById(Long id) {
		return personRepository.findById(id);
	}

	public void removeById(Long id) {
		personRepository.deleteById(id);
	}

}
