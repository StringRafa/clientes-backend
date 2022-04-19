package com.panamby.clientes.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

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

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5,max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;

	private LocalDate dataCadastro;

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		dataCadastro = obj.getDataCadastro();
	}

}
