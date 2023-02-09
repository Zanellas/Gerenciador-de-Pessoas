package com.api.gerenciarpessoas.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class PersonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 150)
	private String name;

	@Column(name = "BIRTH_DATE", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate birth;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PERSON", foreignKey = @ForeignKey(name = "FK_ADDRESS_PERSON"))
	@JsonManagedReference
	private List<AddressEntity> address;

	public PersonEntity(Long id, String name, LocalDate birth, List<AddressEntity> address) {
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.address = address;
	}
	
	public PersonEntity() {
	
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public List<AddressEntity> getAddress() {
		return address;
	}

	public void setAddress(List<AddressEntity> address) {
		this.address = address;
	}
	
	

}

