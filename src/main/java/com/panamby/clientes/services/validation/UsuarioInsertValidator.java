package com.panamby.clientes.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.panamby.clientes.model.entities.Usuario;
import com.panamby.clientes.model.repository.UsuarioRepository;
import com.panamby.clientes.rest.dto.UsuarioDTO;
import com.panamby.clientes.services.exceptions.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioDTO> {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioDTO obj, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Usuario userame = usuarioRepository.findByUsername(obj.getUsername());

		if (userame != null) {
			list.add(new FieldMessage("username", "Usuário já cadastrado!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}