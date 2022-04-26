package com.panamby.clientes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.model.entities.ServicoPrestado;
import com.panamby.clientes.model.repository.ClienteRepository;
import com.panamby.clientes.model.repository.ServicoPrestadoRepository;
import com.panamby.clientes.rest.dto.ServicoPrestadoDTO;
import com.panamby.clientes.utils.BigDecimalConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository prestadoRepository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado insert(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Long idCliente = dto.getIdCliente();

		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não cadastrado!"));

		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

		return prestadoRepository.save(servicoPrestado);
	}

	@GetMapping
	public List<ServicoPrestado> find(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes) {
		return prestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}
}
