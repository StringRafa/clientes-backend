package com.panamby.clientes.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.services.validation.ClienteInsert;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ClienteInsert
@NoArgsConstructor
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "O campo nome é de preenchimento obrigatório")
	private String nome;
	
	@NotEmpty(message = "O campo Email é de preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "O campo CPF é de preenchimento obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		cpf = obj.getCpf();
		dataCadastro = obj.getDataCadastro();
	}

}
