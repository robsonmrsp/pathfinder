package br.com.mr.pathfinder.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_app_user")
@SequenceGenerator(name = "USER_SEQUENCY", sequenceName = "USER_SEQUENCY")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCY")
	private Long id;

	private final String nome;

	private final String cpf;

	public User(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

}
