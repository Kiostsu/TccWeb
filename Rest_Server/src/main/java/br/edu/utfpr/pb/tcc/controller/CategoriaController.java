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

import br.edu.utfpr.pb.tcc.model.Categoria;
import br.edu.utfpr.pb.tcc.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("/list")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public Map<String,List<Categoria>> listar(){
		Map<String,List<Categoria>> map = new HashMap<String, List<Categoria>>();
		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias.addAll(categoriaRepository.findAll());
		map.put("categorias", categorias);
		return map;
	}
	
	@GetMapping("/{id}")
	public Categoria buscar(@PathVariable Long id){
		return categoriaRepository.findOne(id);
	}
	
	@PostMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void inserir(@RequestBody 
			Categoria categoria){
		categoriaRepository.save(categoria);
	}
	
	@PutMapping("/")
	@ResponseStatus(value = HttpStatus.OK)
	public void editar(@RequestBody 
			Categoria categoria){
		categoriaRepository.save(categoria);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void remover(@PathVariable Long id){
		categoriaRepository.delete(id);
	}
	
	
	
	
	
	
	
}
