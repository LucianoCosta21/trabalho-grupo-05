package br.org.serratec.trabalhoGrupo05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.trabalhoGrupo05.model.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, Long>{

}
