package com.panamby.clientes.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {

	@NotEmpty(message = "O campo Descrição é de preenchimento obrigatório")
	private String descricao;
	
	@NotEmpty(message = "O campo Preço é de preenchimento obrigatório")
	private String preco;
	
	@NotEmpty(message = "O campo Data é de preenchimento obrigatório")
	private String data;
	
	@NotNull(message = "O campo Data é de preenchimento obrigatório")
	private Long idCliente;
}
