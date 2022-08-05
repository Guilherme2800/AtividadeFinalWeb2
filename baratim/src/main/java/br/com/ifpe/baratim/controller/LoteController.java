package br.com.ifpe.baratim.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.baratim.model.Lote;
import br.com.ifpe.baratim.model.OrgaoDonatario;
import br.com.ifpe.baratim.model.OrgaoFiscalizador;
import br.com.ifpe.baratim.model.Produto;
import br.com.ifpe.baratim.repository.LoteRepository;
import br.com.ifpe.baratim.repository.OrgaoDonatarioRepository;
import br.com.ifpe.baratim.repository.OrgaoFiscalizadorRepository;
import br.com.ifpe.baratim.repository.ProdutoRepository;
import br.com.ifpe.baratim.service.LoteService;

@RestController
@RequestMapping("lote")
public class LoteController {

	@Autowired
	private LoteRepository loteRepository;
	
	@Autowired
	private OrgaoDonatarioRepository donatarioRepository;
	
	@Autowired
	private OrgaoFiscalizadorRepository fiscalizadorRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private LoteService loteService;
	
	@CrossOrigin("*")
	@GetMapping
	public ResponseEntity<List<Lote>> listar(){
		List<Lote> lotes = loteRepository.findAll();
		return ResponseEntity.ok(lotes);
	}
	
	@CrossOrigin("*")
	@GetMapping("/{id}")
	public ResponseEntity<Lote> listar(@PathVariable Long id){
		Optional<Lote> loteOpt = loteRepository.findById(id);
		if(loteOpt.isPresent()) {
			return ResponseEntity.ok(loteOpt.get());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@CrossOrigin("*")
	@GetMapping("/donatario/{id}")
	public ResponseEntity<List<Lote>> listarPorDonatario(@PathVariable Long id){
		List<Lote> lotes = loteRepository.findByDonatario_id(id);
		return ResponseEntity.ok(lotes);
	}
	
	@CrossOrigin("*")
	@PostMapping
	public ResponseEntity<Lote> cadastrarLote(@RequestBody Lote lote){
		
		Optional<OrgaoDonatario> donatarioOpt = donatarioRepository.findById(lote.getDonatario().getId());
		if(donatarioOpt.isPresent()) {
			lote.setDonatario(donatarioOpt.get());
		}
		
		Optional<OrgaoFiscalizador> fiscalizadorOpt = fiscalizadorRepository.findById(lote.getFiscalizador().getId());
		if(fiscalizadorOpt.isPresent()) {
			lote.setFiscalizador(fiscalizadorOpt.get());
		}
		
		List<Produto> produtos = new ArrayList<>(lote.getProduto());
		lote.getProduto().removeAll(lote.getProduto());
		
		for(Produto produtoLote : produtos) {
			Optional<Produto> produtoOpt = produtoRepository.findById(produtoLote.getId());
			if(produtoOpt.isPresent()) {
				Produto produto = produtoOpt.get();
				lote.getProduto().add(produto);
			}
		}
		
		lote.setDataCadastro(new Date());
		
		Lote save = loteRepository.save(lote);
		
		return ResponseEntity.ok(save);
	}

	@CrossOrigin("*")
	@DeleteMapping("{id}")
	public ResponseEntity<?> removerLote(@PathVariable Long id) {

		Optional<Lote> loteOpt = loteRepository.findById(id);
		if (loteOpt.isPresent()) {
			Lote lote = loteOpt.get();

			LocalDateTime dataEntrega = lote.getDataCadastro().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDateTime();
			LocalDateTime agora = LocalDateTime.now();

			Long diferencaMinutos = 0l;

			try {
				diferencaMinutos = loteService.diferencaHoras(dataEntrega, agora);
			} catch (ParseException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(e);
			}

			if (diferencaMinutos <= 30) {
				loteRepository.deleteById(id);
				return ResponseEntity.ok().build();
			}

		}

		return ResponseEntity.unprocessableEntity().build();
	}

}
