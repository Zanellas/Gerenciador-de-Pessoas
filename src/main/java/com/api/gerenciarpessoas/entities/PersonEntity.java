package com.api.gerenciarpessoas.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 150)
	private String name;

	@Column(name = "BIRTH_DATE", nullable = false)
	private LocalDate birth;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PERSON", foreignKey = @ForeignKey(name = "FK_ADDRESS_PERSON"))
	@JsonManagedReference
	private List<Address> address;

}
