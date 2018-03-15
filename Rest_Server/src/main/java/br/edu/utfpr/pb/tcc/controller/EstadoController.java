package br.edu.utfpr.pb.tcc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.tcc.model.Cidade;
import br.edu.utfpr.pb.tcc.model.Estado;
import br.edu.utfpr.pb.tcc.repository.CidadeRepository;
import br.edu.utfpr.pb.tcc.repository.EstadoRepository;

@RestController
@RequestMapping("/estado")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
		
	@GetMapping("/list")
	public Map<String,List<Estado>> listar(){
		Map<String, List<Estado>> map = new HashMap<String, List<Estado>>();
		List<Estado> estados = new ArrayList<Estado>();
			estados.addAll(estadoRepository.findAll());
	
	    map.put("estados", estados);
		return map;
	}
	
	@GetMapping("/{id}")
	public Estado buscar(@PathVariable Long id){
		return estadoRepository.findOne(id);
	}
	
	@GetMapping("/{id}/cidade")
	public Map<String,List<Cidade>> findCidadesByEstado(@PathVariable Long id){
		Map<String, List<Cidade>> map = new HashMap<String, List<Cidade>>();
		List<Cidade> cidades = new ArrayList<Cidade>();
			cidades.addAll(cidadeRepository.findByEstadoId(id));
	
	    map.put("cidade", cidades);
		return map;
	}
	
	
	@GetMapping("/cidade/{id}")
	public Map<String,List<Cidade>> buscarCidade(@PathVariable Long id){
		Map<String, List<Cidade>> map = new HashMap<String, List<Cidade>>();
			List<Cidade> cidades = new ArrayList<Cidade>();
				cidades.add(cidadeRepository.findOne(id));
		
		    map.put("cidade", cidades);
			return map;
	}
	
	@GetMapping("/listcidades")
	public Map<String,List<Cidade>> listarCidades(){
		Map<String, List<Cidade>> map = new HashMap<String, List<Cidade>>();
		List<Cidade> cidades = new ArrayList<Cidade>();
			cidades.addAll(cidadeRepository.findAll());
	
	    map.put("cidades", cidades);
		return map;
	}
	
}
