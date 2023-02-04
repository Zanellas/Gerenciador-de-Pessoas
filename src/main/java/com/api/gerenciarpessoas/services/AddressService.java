package com.api.gerenciarpessoas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.gerenciarpessoas.entities.AddressEntity;
import com.api.gerenciarpessoas.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	public AddressEntity save(AddressEntity addressModel) {
		return addressRepository.save(addressModel);
	}

	public List<AddressEntity> listAll() {
		return addressRepository.findAll();
	}

	public Optional<AddressEntity> findById(Long id) {
		return addressRepository.findById(id);
	}

	public void removeById(Long id) {
		addressRepository.deleteById(id);
	}

}
