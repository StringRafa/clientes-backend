package com.panamby.clientes.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.panamby.clientes.dto.ClienteDTO;
import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.model.repository.ClienteRepository;
import com.panamby.clientes.services.ClienteService;

@RestController
@RequestMapping(value = "/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ClienteService service;

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Cliente insert(@RequestBody @Valid Cliente cliente) {
//		return repository.save(cliente);
//	}
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Cliente insert(@RequestBody @Valid ClienteDTO objDTO) {
//		Cliente obj = service.fromDTO(objDTO);
//		obj = service.insert(obj);
//		return repository.save(obj);
//	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDTO){
//		Cliente obj = service.fromDTO(objDTO);
//		obj = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cliente> insert(@Valid @RequestBody ClienteDTO objDTO){
		Cliente obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}

//	@GetMapping(value = "/{id}")
//	public Cliente findById(@PathVariable Long id) {
//		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Long id){
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
			service.delete(id);
			return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody @Valid Cliente clienteAtualizado) {
		repository.findById(id)
		.map(cliente -> {
			clienteAtualizado.setId(cliente.getId());
			return repository.save(clienteAtualizado);
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	

//	@GetMapping
//	public List findAll() {
//		List<Cliente> list = repository.findAll();
//		return list;
//	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> list = repository.findAll();
		return ResponseEntity.ok().body(list);
	}
}
