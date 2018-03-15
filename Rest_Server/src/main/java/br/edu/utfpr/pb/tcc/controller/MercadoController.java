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

import br.edu.utfpr.pb.tcc.model.Mercado;
import br.edu.utfpr.pb.tcc.repository.MercadoRepository;

@RestController
@RequestMapping("/mercado")
public class MercadoController {

	@Autowired
	private MercadoRepository mercadoRepository;
	
	@GetMapping("/list")	
	public List<Mercado> listar(){
		return mercadoRepository.findAll();
	}
	
	@GetMapping("/list/{id}")
	public Map<String,List<Mercado>> findMercadosByCidade(@PathVariable Long id){
		Map<String, List<Mercado>> map = new HashMap<String, List<Mercado>>();
		List<Mercado> mercados = new ArrayList<Mercado>();
		mercados.addAll(mercadoRepository.findByCidadeId(id));
	
	    map.put("mercados", mercados);
		return map;
	}
	
	@GetMapping("/{id}")
	public Mercado buscar(@PathVariable Long id){
		return mercadoRepository.findOne(id);
	}
	
	@PostMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void inserir(@RequestBody 
			Mercado mercado){
		mercadoRepository.save(mercado);
	}
	
	@PutMapping("/")
	@ResponseStatus(value = HttpStatus.OK)
	public void editar(@RequestBody 
			Mercado mercado){
		mercadoRepository.save(mercado);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void remover(@PathVariable Long id){
		mercadoRepository.delete(id);
	}
	
	
	
	
	
	
	
}
