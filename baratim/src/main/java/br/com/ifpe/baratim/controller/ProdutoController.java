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

import br.com.ifpe.baratim.model.OrgaoDonatario;
import br.com.ifpe.baratim.model.Produto;
import br.com.ifpe.baratim.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@CrossOrigin("*")
	@PostMapping
	public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto, UriComponentsBuilder uriBuilder){
		
		Produto produtoSave = produtoRepository.save(produto);
		
		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produtoSave.getId()).toUri();
	
		return ResponseEntity.created(uri).body(produtoSave);
	}
	
	@CrossOrigin("*")
	@GetMapping
	public ResponseEntity<List<Produto>> listar(){
		List<Produto> orgaos = produtoRepository.findAll();
		return ResponseEntity.ok(orgaos);
	}
	
}
