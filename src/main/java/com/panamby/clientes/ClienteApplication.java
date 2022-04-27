package com.panamby.clientes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.panamby.clientes.model.entities.Cliente;
import com.panamby.clientes.model.entities.ServicoPrestado;
import com.panamby.clientes.model.repository.ClienteRepository;
import com.panamby.clientes.model.repository.ServicoPrestadoRepository;
import com.panamby.clientes.util.BigDecimalConverter;

@SpringBootApplication
public class ClienteApplication {
	
	@Autowired
	ServicoPrestadoRepository servicoPrestadoRepository;
	
	BigDecimalConverter bigDecimalConverter;
	
	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {
			Cliente cliente1 = Cliente.builder()
					.cpf("71169230040")
					.nome("Rafael José de Souza")
					.email("rafa.jo.souza@gmail.com")
					.dataCadastro(LocalDate.now())
					.build();
			
			Cliente cliente2 = Cliente.builder()
					.cpf("63863620046")
					.nome("William Roberto Garcia Junior")
					.email("william.garcia@gmail.com")
					.build();
			Cliente cliente3 = Cliente.builder()
					.cpf("52172576018")
					.nome("Ana Paula de Souza")
					.email("paula.souza@gmail.com")
					.build();
			repository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));
			
			LocalDate data = LocalDate.parse("26/04/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			ServicoPrestado servico1 = ServicoPrestado.builder()
					.descricao("Manutenção de notebook")
					.cliente(cliente2)
					.valor(new BigDecimal(150))
					.data(data)
					.build();
			ServicoPrestado servico2 = ServicoPrestado.builder()
					.descricao("Manutenção de computador")
					.cliente(cliente1)
					.valor(new BigDecimal(100))
					.data(LocalDate.now())
					.build();
			ServicoPrestado servico3 = ServicoPrestado.builder()
					.descricao("Formatação")
					.cliente(cliente3)
					.valor(new BigDecimal(80))
					.data(LocalDate.now())
					.build();
			ServicoPrestado servico4 = ServicoPrestado.builder()
					.descricao("Instalação de software")
					.cliente(cliente2)
					.valor(new BigDecimal(50))
					.data(LocalDate.now())
					.build();
			ServicoPrestado servico5 = ServicoPrestado.builder()
					.descricao("Instalação de placa de vídeo")
					.cliente(cliente1)
					.valor(new BigDecimal(1000))
					.data(LocalDate.now())
					.build();
			
			servicoPrestadoRepository.saveAll(Arrays.asList(servico1, servico2, servico3, servico4, servico5));
//			servicoPrestadoRepository.save(servico1);		
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}

}
