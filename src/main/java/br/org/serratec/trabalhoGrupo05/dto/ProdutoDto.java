package br.org.serratec.trabalhoGrupo05.dto;

import br.org.serratec.trabalhoGrupo05.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ProdutoDto(Long id,
		@NotBlank(message = "Nome não pode ser nulo")
		String nome, 
		@NotBlank (message = "Informe a categoria do produto")
		String categoria,
		@NotNull()
		@Positive(message = " valor tem que ser maior que 0")
		double valor,
		@NotBlank
		String descricao) {
	
	public Produto toEntity() {
		return new Produto(this.id, this.nome, this.categoria,this.valor, this.descricao );
	}
}
