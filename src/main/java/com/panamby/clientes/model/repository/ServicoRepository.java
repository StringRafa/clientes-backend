package com.panamby.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panamby.clientes.model.entities.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{

}
