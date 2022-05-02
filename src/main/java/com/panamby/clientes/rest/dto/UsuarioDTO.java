package com.panamby.clientes.rest.dto;

import javax.validation.constraints.NotEmpty;

import com.panamby.clientes.model.entities.Usuario;
import com.panamby.clientes.services.validation.UsuarioInsert;

import lombok.Data;
import lombok.NoArgsConstructor;

@UsuarioInsert
@Data
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	
	@NotEmpty(message = "O campo Login é de preenchimento obrigatório")
	private String username;

	@NotEmpty(message = "O campo Senha é de preenchimento obrigatório")
	private String password;
	
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		username = obj.getUsername();
		password = obj.getPassword();
	}
	
}
