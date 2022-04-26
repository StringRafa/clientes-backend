package com.panamby.clientes.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.panamby.clientes.model.entities.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long>{

	@Query("select s from ServicoPrestado s join s.cliente c where upper(c.nome) like upper(:nome) and MONTH(s.data) =:mes")
	List<ServicoPrestado> findByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);

}
