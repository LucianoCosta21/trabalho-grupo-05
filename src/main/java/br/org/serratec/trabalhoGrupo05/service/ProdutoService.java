package br.org.serratec.trabalhoGrupo05.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.org.serratec.trabalhoGrupo05.dto.ProdutoDto;
import br.org.serratec.trabalhoGrupo05.model.Produto;
import br.org.serratec.trabalhoGrupo05.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	public ProdutoRepository repositorio;
	
	public List<ProdutoDto> listarProdutos(){
		return repositorio.findAll().stream()
				.map(p -> new ProdutoDto(p.getId(),p.getNome(), p.getCategoria(),p.getValor(),p.getDescricao())).toList();
						
	}
	
	public Optional<ProdutoDto> listarProId(Long id){
		Optional<Produto> produto = repositorio.findById(id);
		if(produto.isPresent()) {
			return Optional.of(produto.get().toDto());
		}
		return Optional.empty();
	}

	public ProdutoDto cadastrarProduto(ProdutoDto produto) {
		Produto produtoEntity = repositorio.save(produto.toEntity());
		return produtoEntity.toDto();		
	}
	
	public Optional<ProdutoDto> atualizarProduto(Long id, ProdutoDto produto){
		Produto produtoEntity = produto.toEntity();
		if(repositorio.existsById(id)) {
			produtoEntity.setId(id);
			repositorio.save(produtoEntity);
			return Optional.of(produtoEntity.toDto());
		}return Optional.empty();
	}
	
	public boolean excluirProduto(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
		
	}
	
	

}
