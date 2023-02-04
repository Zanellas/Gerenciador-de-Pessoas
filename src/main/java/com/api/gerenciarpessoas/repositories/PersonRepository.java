package com.api.gerenciarpessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.gerenciarpessoas.entities.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}