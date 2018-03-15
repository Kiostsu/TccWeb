package br.edu.utfpr.pb.tcc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.tcc.model.Produto;
import br.edu.utfpr.pb.tcc.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/list")
	public Map<String,List<Produto>> listar(){
		Map<String,List<Produto>> map = new HashMap<String,List<Produto>>();
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.addAll(produtoRepository.findAll());
		map.put("produtos", produtos);
		return map;
	}
		
	
	@GetMapping("/{id}")
	public Produto buscar(@PathVariable Long id){
		return produtoRepository.findOne(id);
	}
	
	@PostMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void inserir(@RequestBody 
			Produto produto){
		produtoRepository.save(produto);
	}
	
	@PutMapping("/")
	@ResponseStatus(value = HttpStatus.OK)
	public void editar(@RequestBody 
			Produto produto){
		produtoRepository.save(produto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void remover(@PathVariable Long id){
		produtoRepository.delete(id);
	}
	
	
	
	
	
	
	
}
