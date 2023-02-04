package com.api.gerenciarpessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.gerenciarpessoas.entities.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
