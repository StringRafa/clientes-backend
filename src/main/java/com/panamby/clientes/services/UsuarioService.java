package com.panamby.clientes.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.panamby.clientes.model.entities.Usuario;
import com.panamby.clientes.model.repository.UsuarioRepository;
import com.panamby.clientes.rest.dto.UsuarioDTO;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario;
		try {
			usuario = usuarioRepository.findByUsername(username);
			return User.builder()
					.username(usuario.getUsername())
					.password(usuario.getPassword())
					.roles("USER")
					.build();
		} catch (UsernameNotFoundException e) {
			return (UserDetails) new UsernameNotFoundException("Usuário não cadastrado.");
		}
//				.orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado."));	
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = usuarioRepository.save(obj);
		return obj;
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getUsername(), objDto.getPassword());
	}

}
