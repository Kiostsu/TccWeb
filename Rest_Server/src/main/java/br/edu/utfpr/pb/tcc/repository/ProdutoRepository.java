package br.edu.utfpr.pb.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.tcc.model.Produto;

public interface ProdutoRepository extends 
						JpaRepository<Produto, Long>{

}
