package com.desafioapi.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = true)
	private String nome;
	
	
	@Column(nullable = true)
	@NotNull
	private String email;
	
	@Id
	@Column(nullable = true)
	@NotNull
	private String cpf;
	
	
	@Column(nullable = true)
	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;
	
	@OneToOne(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
	private Endereco endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	
	
	
	
	
}
