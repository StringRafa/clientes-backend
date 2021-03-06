package com.panamby.clientes.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.model.repository.ClienteRepository;
import com.panamby.clientes.rest.dto.ClienteDTO;
import com.panamby.clientes.rest.dto.ClienteNewDTO;
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
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCpf(), objDto.getDataCadastro());
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCpf(), objDto.getDataCadastro());
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setCpf(obj.getCpf());
	}
}
