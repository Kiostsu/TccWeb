package br.edu.utfpr.pb.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.tcc.model.Cidade;

public interface CidadeRepository extends 
						JpaRepository<Cidade	, Long>{
		
public List<Cidade> findByEstadoId(Long id);
}
