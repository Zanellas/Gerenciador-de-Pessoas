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
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PERSON", foreignKey = @ForeignKey(name = "FK_ADDRESS_PERSON"))
	@JsonBackReference
	private Person person;

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

}
