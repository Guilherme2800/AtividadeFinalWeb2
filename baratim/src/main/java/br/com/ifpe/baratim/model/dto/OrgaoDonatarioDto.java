package br.com.ifpe.baratim.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ifpe.baratim.model.OrgaoDonatario;

public class OrgaoDonatarioDto {

	private Long id;
	private String nome;
	
	public OrgaoDonatarioDto(OrgaoDonatario orgao) {
		this.id = orgao.getId();
		this.nome = orgao.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static List<OrgaoDonatarioDto> converter(List<OrgaoDonatario> orgaos){
		return orgaos.stream().map(OrgaoDonatarioDto::new).collect(Collectors.toList());
	}

}
