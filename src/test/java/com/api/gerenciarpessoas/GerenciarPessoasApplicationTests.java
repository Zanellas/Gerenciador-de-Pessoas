package com.api.gerenciarpessoas;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.gerenciarpessoas.entities.AddressEntity;
import com.api.gerenciarpessoas.entities.PersonEntity;
import com.api.gerenciarpessoas.repositories.AddressRepository;
import com.api.gerenciarpessoas.repositories.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class GerenciarPessoasApplicationTests {

	@Autowired
	private AddressRepository addressRepository;	

	@Autowired
	private PersonRepository personRepository;
	
	@Test
	void createPersonEntityTest() {
		PersonEntity person = new PersonEntity();
		LocalDate date = LocalDate.of(1999, 10, 25);
		person.setName("name1");	
		person.setBirth(date);
		this.personRepository.save(person);
		assertThat(person.getId()).isNotNull();
		assertThat(person.getName()).isEqualTo("name1");
		assertThat(person.getBirth()).isEqualTo(date);
	}
	
	@Test 
	void deletePersonEntityTest(){
		PersonEntity person = new PersonEntity();
		LocalDate date = LocalDate.of(1999, 10, 25);
		person.setName("name1");	
		person.setBirth(date);
		this.personRepository.save(person);
		personRepository.delete(person);
		assertThat(personRepository.findById(person.getId())).isNotPresent();		
	}
	
	@Test 
	void updatePersonEntityTest(){
		PersonEntity person = new PersonEntity();
		LocalDate date = LocalDate.of(1999, 10, 25);
		person.setName("name1");	
		person.setBirth(date);
		this.personRepository.save(person);
		person.setName("name2");
		person = this.personRepository.save(person); 
		assertThat(person.getName()).isEqualTo("name2");
	}
	
	
	@Test
	void createAddressEntityTest() {
		AddressEntity address = new AddressEntity();
		address.setStreet("Rua 123");
		address.setCep("13245-000");
		address.setNumber("123");
		address.setCity("Sao Paulo");
		address.setMain(true);
		this.addressRepository.save(address);
		assertThat(address.getId()).isNotNull();
		assertThat(address.getStreet()).isEqualTo("Rua 123");
		assertThat(address.getCep()).isEqualTo("13245-000");
		assertThat(address.getNumber()).isEqualTo("123");
		assertThat(address.getCity()).isEqualTo("Sao Paulo");
		assertThat(address.getMain()).isEqualTo(true);
		
	}
	
	@Test
	void deleteAddressEntityTest() {
		AddressEntity address = new AddressEntity();
		address.setStreet("Rua 123");
		address.setCep("13245-000");
		address.setNumber("123");
		address.setCity("Sao Paulo");
		address.setMain(true);
		this.addressRepository.save(address);
		addressRepository.delete(address);
		assertThat(addressRepository.findById(address.getId())).isNotPresent();		
	}
	
	@Test 
	void updateAddressEntityTest(){
		AddressEntity address = new AddressEntity();
		address.setStreet("Rua 123");
		address.setCep("13245-000");
		address.setNumber("123");
		address.setCity("Sao Paulo");
		address.setMain(true);
		this.addressRepository.save(address);
		address.setStreet("Rua 12342343214");
		address.setCep("99999-000");
		address.setNumber("123erqwer");
		address.setCity("Rio de JAneiro");
		address.setMain(false);
		address = this.addressRepository.save(address);
		assertThat(address.getStreet()).isEqualTo("Rua 12342343214");
		assertThat(address.getCep()).isEqualTo("99999-000");
		assertThat(address.getNumber()).isEqualTo("123erqwer");
		assertThat(address.getCity()).isEqualTo("Rio de JAneiro");
		assertThat(address.getMain()).isEqualTo(false);
	}
}
