package com.panamby.clientes.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.panamby.clientes.dto.ClienteDTO;
import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.model.repository.ClienteRepository;
import com.panamby.clientes.services.exceptions.FieldMessage;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente cpf = clienteRepository.findByCpf(objDto.getCpf());
		
		if(cpf != null) {
			list.add(new FieldMessage("cpf", "CPF já cadastrado!"));
		}
		
		Cliente email = clienteRepository.findByEmail(objDto.getEmail());
		
		if(email != null) {
			list.add(new FieldMessage("email", "Email já cadastrado!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}