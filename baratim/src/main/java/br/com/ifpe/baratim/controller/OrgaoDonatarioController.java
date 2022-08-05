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
import br.com.ifpe.baratim.model.dto.OrgaoDonatarioDto;
import br.com.ifpe.baratim.repository.OrgaoDonatarioRepository;

@RestController
@RequestMapping("donatario")
public class OrgaoDonatarioController {

	@Autowired
	OrgaoDonatarioRepository orgaoDonatarioRepository;

	@CrossOrigin("*")
	@PostMapping
	public ResponseEntity<OrgaoDonatarioDto> cadastrarDonatario(@RequestBody OrgaoDonatario orgaoDonatario,
			UriComponentsBuilder uriBuilder) {

		OrgaoDonatario orgaoSave = orgaoDonatarioRepository.save(orgaoDonatario);

		URI uri = uriBuilder.path("/donatario/{id}").buildAndExpand(orgaoSave.getId()).toUri();

		return ResponseEntity.created(uri).body(new OrgaoDonatarioDto(orgaoSave));
	}
	
	@CrossOrigin("*")
	@GetMapping
	public ResponseEntity<List<OrgaoDonatario>> listar(){
		List<OrgaoDonatario> orgaos = orgaoDonatarioRepository.findAll();
		return ResponseEntity.ok(orgaos);
	}
}