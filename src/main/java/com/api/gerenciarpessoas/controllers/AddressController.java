package com.api.gerenciarpessoas.controllers;

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

import com.api.gerenciarpessoas.entities.AddressEntity;
import com.api.gerenciarpessoas.services.AddressService;

@RestController
@RequestMapping("/Address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AddressEntity save(@RequestBody AddressEntity addressModel) {
		return addressService.save(addressModel);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AddressEntity> listAll() {
		return addressService.listAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AddressEntity findAddressById(@PathVariable("id") Long id) {
		return addressService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrada"));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeAddressById(@PathVariable("id") Long id) {
		addressService.findById(id).map(address -> {
			addressService.removeById(address.getId());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrada"));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void attAddress(@PathVariable("id") Long id, @RequestBody AddressEntity addressModel) {
		addressService.findById(id).map(addressBase -> {
			modelMapper.map(addressModel, addressBase);
			addressService.save(addressBase);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address não encontrada"));
	}

}
