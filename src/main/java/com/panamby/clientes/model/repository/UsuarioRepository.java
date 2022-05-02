package com.panamby.clientes.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.model.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

//	Optional<Usuario> findByUsername(String username);
	
	@Transactional(readOnly=true)
	Usuario findByUsername(String username);
}
