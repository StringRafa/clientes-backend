package com.panamby.clientes.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.panamby.clientes.model.entities.Usuario;
import com.panamby.clientes.model.repository.UsuarioRepository;
import com.panamby.clientes.rest.dto.UsuarioDTO;
import com.panamby.clientes.services.UsuarioService;


@RestController
@RequestMapping(value = "/api/usuarios")
//@CrossOrigin("http://localhost:4200")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public void insert(@RequestBody @Valid Usuario usuario) {
//		usuarioRepository.save(usuario);
//	}
	
//	@PostMapping
//	public ResponseEntity<Void> insert(@RequestBody @Valid Usuario usuario) {
//		usuario = usuarioRepository.save(usuario);
//		return ResponseEntity.created(null).build();
//	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> insert(@Valid @RequestBody UsuarioDTO objDTO){
		Usuario obj = usuarioService.fromDTO(objDTO);
		obj = usuarioService.insert(obj);
		return ResponseEntity.created(null).body(obj);
	}
}
