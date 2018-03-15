package br.edu.utfpr.pb.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.tcc.model.Mercado;

public interface MercadoRepository extends 
						JpaRepository<Mercado, Long>{
	
	public List<Mercado> findByCidadeId(Long id);

}
