package br.edu.utfpr.pb.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.tcc.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

	Permissao findByPermissao(String permissao);
	
}
