package com.panamby.clientes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.model.repository.ClienteRepository;

@SpringBootApplication
public class ClienteApplication {
	
	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {
			Cliente cliente1 = Cliente.builder()
					.cpf("71169230040")
					.nome("Rafael José de Souza")
					.email("rafa.jo.souza@gmail.com")
					.dataCadastro(LocalDate.now())
					.build();
			repository.save(cliente1);
			
			Cliente cliente2 = Cliente.builder()
					.cpf("63863620046")
					.nome("William Roberto Garcia Junior")
					.email("william.garcia@gmail.com")
					.build();
			repository.save(cliente2);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}

}
