package br.com.ifpe.baratim.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ifpe.baratim.model.OrgaoFiscalizador;
import br.com.ifpe.baratim.repository.OrgaoFiscalizadorRepository;

@RestController
@RequestMapping("/fiscalizador")
public class OrgaoFiscalizadorController {

	@Autowired
	OrgaoFiscalizadorRepository orgaoFiscalizadorRepository;
	
	@CrossOrigin("*")
	@PostMapping
	public ResponseEntity<OrgaoFiscalizador> cadastrarFiscalizador(@RequestBody OrgaoFiscalizador orgaoFiscalizador, UriComponentsBuilder uriBuilder){
		
		OrgaoFiscalizador orgaoSave = orgaoFiscalizadorRepository.save(orgaoFiscalizador);
		
		URI uri = uriBuilder.path("/fiscalizador/{id}").buildAndExpand(orgaoSave.getId()).toUri();
		
		return ResponseEntity.created(uri).body(orgaoSave);
	}
	
	@CrossOrigin("*")
	@GetMapping
	public ResponseEntity<List<OrgaoFiscalizador>> listar(){
		List<OrgaoFiscalizador> orgaos = orgaoFiscalizadorRepository.findAll();
		return ResponseEntity.ok(orgaos);
	}
	
}
