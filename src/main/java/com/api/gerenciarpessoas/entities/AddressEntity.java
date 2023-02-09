package com.api.gerenciarpessoas.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ADDRESS")
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PERSON", foreignKey = @ForeignKey(name = "FK_ADDRESS_PERSON"))
	@JsonBackReference
	private PersonEntity person;

	@Column(name = "STREET", nullable = false, length = 150)
	private String street;

	@Column(name = "CEP", nullable = false, length = 9)
	private String cep;

	@Column(name = "NUMBER", nullable = false, length = 30)
	private String number;

	@Column(name = "CITY", nullable = false, length = 150)
	private String city;

	@Column(name = "MAIN", nullable = false)
	private Boolean main;

	public AddressEntity(Long id, PersonEntity person, String street, String cep, String number, String city,
			Boolean main) {
		this.id = id;
		this.person = person;
		this.street = street;
		this.cep = cep;
		this.number = number;
		this.city = city;
		this.main = main;
	}
	
	public AddressEntity() {

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}	

	
}
