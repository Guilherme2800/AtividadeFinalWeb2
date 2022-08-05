package br.com.ifpe.baratim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifpe.baratim.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
