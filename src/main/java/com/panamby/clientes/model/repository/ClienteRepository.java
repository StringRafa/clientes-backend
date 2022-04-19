package com.panamby.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.panamby.clientes.model.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Transactional(readOnly=true)
	Cliente findByCpf(String cpf);
	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
