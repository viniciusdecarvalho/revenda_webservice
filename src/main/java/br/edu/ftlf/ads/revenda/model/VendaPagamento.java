package br.edu.ftlf.ads.revenda.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the vendas_pagamentos database table.
 * 
 */
@Entity
@Table(name="vendas_pagamentos")
public class VendaPagamento extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="vendaId", nullable=false)
	private Venda venda;

	@ManyToOne
	@JoinColumn(name="pagamentoId", nullable=false)
	private Pagamento pagamento;

	public VendaPagamento() {
	}

	public VendaPagamento(Venda venda, Pagamento pagamento) {
		setVenda(venda);
		setPagamento(pagamento);
	}

	public Venda getVenda() {
		return this.venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}