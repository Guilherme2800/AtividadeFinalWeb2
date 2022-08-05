package br.com.ifpe.baratim.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Date dataEntrega;
	private String observacao;
	private Date dataCadastro;

	@ManyToOne
	OrgaoFiscalizador fiscalizador;

	@ManyToOne
	OrgaoDonatario donatario;

	@ManyToMany(fetch = FetchType.EAGER)
	List<Produto> produto = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public OrgaoFiscalizador getFiscalizador() {
		return fiscalizador;
	}

	public void setFiscalizador(OrgaoFiscalizador fiscalizador) {
		this.fiscalizador = fiscalizador;
	}

	public OrgaoDonatario getDonatario() {
		return donatario;
	}

	public void setDonatario(OrgaoDonatario donatario) {
		this.donatario = donatario;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
