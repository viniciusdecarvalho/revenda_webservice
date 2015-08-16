package br.edu.ftlf.ads.revenda.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the aquisicoes_pagamentos database table.
 * 
 */
@Entity
@Table(name="aquisicoes_pagamentos")
public class AquisicaoPagamento extends Model {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="aquisicaoId", nullable=false)
	private Aquisicao aquisicao;

	@ManyToOne
	@JoinColumn(name="pagamentoId", nullable=false)
	private Pagamento pagamento;

	public AquisicaoPagamento() {
	}

	public AquisicaoPagamento(Aquisicao aquisicao, Pagamento pagamento) {
		setAquisicao(aquisicao);
		setPagamento(pagamento);
	}

	public Aquisicao getAquisicao() {
		return this.aquisicao;
	}

	public void setAquisicao(Aquisicao aquisicao) {
		this.aquisicao = aquisicao;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}