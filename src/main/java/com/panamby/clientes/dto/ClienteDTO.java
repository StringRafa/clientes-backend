package com.panamby.clientes.dto;

import java.io.Serializable;
import java.time.LocalDate;

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

	private String nome;

	private String cpf;

	private LocalDate dataCadastro;

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		dataCadastro = obj.getDataCadastro();
	}

}
