package com.panamby.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panamby.clientes.model.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
