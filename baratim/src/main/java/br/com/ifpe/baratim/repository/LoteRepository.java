package br.com.ifpe.baratim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifpe.baratim.model.Lote;

public interface LoteRepository extends JpaRepository<Lote, Long>{

	List<Lote> findByDonatario_id(Long id);
	
}
