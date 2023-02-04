package com.api.gerenciarpessoas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.gerenciarpessoas.models.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel, Long>{

}
