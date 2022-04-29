package com.panamby.clientes.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.panamby.clientes.model.entities.Usuario;
import com.panamby.clientes.model.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void insert(@RequestBody @Valid Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
