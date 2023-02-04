package com.api.gerenciarpessoas.controllers;

import java.util.Collections;
import java.util.List;

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
import com.api.gerenciarpessoas.repositorys.PersonRepository;
import com.api.gerenciarpessoas.services.AddressService;
import com.api.gerenciarpessoas.services.PersonService;

@RestController
@RequestMapping("/Address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AddressModel save(@RequestBody AddressModel addressModel) {
		return addressService.saveAddress(addressModel);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AddressModel> listAll(){
		return addressService.listAllAddress();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AddressModel findAddressById(@PathVariable("id") Long id) {
		return addressService.findAddressById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrada"));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeAddressById(@PathVariable("id") Long id) {
		addressService.findAddressById(id)
		.map(address -> {
			addressService.removeAddressById(address.getId());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrada"));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void attAddress(@PathVariable("id") Long id, @RequestBody AddressModel addressModel) {
		addressService.findAddressById(id)
		.map(addressBase -> {
			modelMapper.map(addressModel, addressBase);
			addressService.saveAddress(addressBase);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address não encontrada"));	
	}
	
}

