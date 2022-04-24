package com.panamby.clientes.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.model.repository.ClienteRepository;
import com.panamby.clientes.rest.dto.ClienteNewDTO;
import com.panamby.clientes.services.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteNewDTO> {
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = (long) Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente auxEmail = clienteRepository.findByEmail(objDto.getEmail());
		Cliente auxCpf = clienteRepository.findByCpf(objDto.getCpf());
		
		if(auxEmail != null && !auxEmail.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já cadastrado"));
		}
		
		if(auxCpf != null && !auxCpf.getId().equals(uriId)) {
			list.add(new FieldMessage("cpf", "CPF já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}