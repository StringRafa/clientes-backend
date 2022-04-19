package com.panamby.clientes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.model.repository.ClienteRepository;
import com.panamby.clientes.services.exceptions.DataIntegrityException;
import com.panamby.clientes.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente find(Long id) {
		
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possuí pedidos");
		}
	}
	
}
