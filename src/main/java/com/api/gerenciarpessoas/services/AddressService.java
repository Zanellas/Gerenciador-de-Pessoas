	package com.api.gerenciarpessoas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.gerenciarpessoas.models.AddressModel;
import com.api.gerenciarpessoas.models.PersonModel;
import com.api.gerenciarpessoas.repositorys.AddressRepository;
import com.api.gerenciarpessoas.repositorys.PersonRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public AddressModel saveAddress(AddressModel addressModel) {
		return addressRepository.save(addressModel);
	}
	
	public List<AddressModel> listAllAddress(){
		return addressRepository.findAll();
	}
		
	public Optional<AddressModel> findAddressById(Long id) {
		return addressRepository.findById(id);
	}

	public void removeAddressById(Long id) {
		addressRepository.deleteById(id);		
	}
	

}
	