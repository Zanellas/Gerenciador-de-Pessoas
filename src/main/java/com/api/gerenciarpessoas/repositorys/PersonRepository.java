package com.api.gerenciarpessoas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.gerenciarpessoas.models.AddressModel;
import com.api.gerenciarpessoas.models.PersonModel;

public interface PersonRepository extends JpaRepository<PersonModel, Long> {
	
}