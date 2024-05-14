package br.org.serratec.trabalhoGrupo05.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.trabalhoGrupo05.dto.ProdutoDto;
import br.org.serratec.trabalhoGrupo05.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping
	public ResponseEntity<List<ProdutoDto>> obterTodosOsProdutos() {
		return ResponseEntity.ok(service.listarProdutos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> obterProduto(@PathVariable Long id) {
		Optional<ProdutoDto> produto = service.listarProId(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<ProdutoDto> cadastrarProduto(@Valid @RequestBody ProdutoDto produto) {
		return new ResponseEntity<ProdutoDto>(service.cadastrarProduto(produto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> alterarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoAlterado) {
		Optional<ProdutoDto> produto = service.atualizarProduto(id, produtoAlterado);
		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
		if (!service.excluirProduto(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();

	}

}
