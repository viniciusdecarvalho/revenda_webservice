package br.edu.ftlf.ads.revenda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.edu.ftlf.ads.revenda.model.Enums.SituacaoGasto;

/**
 * The persistent class for the gastos database table.
 * 
 */
@Entity
@Table(name="gastos")
public class Gasto extends Model {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private SituacaoGasto situacao;

	@NotNull
	@ManyToOne
	@JoinColumn(name="aquisicaoId", nullable=false)
	private Aquisicao aquisicao;

	@NotNull
	@ManyToOne
	@JoinColumn(name="fornecedorId", nullable=false)
	private Fornecedor fornecedor;

	@NotNull
	@ManyToOne
	@JoinColumn(name="pagamentoId", nullable=false)
	private Pagamento pagamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="funcionarioId", nullable=false)
	private Funcionario funcionario;

	public Gasto() {
	}

	public SituacaoGasto getSituacao() {
		return this.situacao;
	}

	public void setSituacao(SituacaoGasto situacao) {
		this.situacao = situacao;
	}

	public Aquisicao getAquisicao() {
		return this.aquisicao;
	}

	public void setAquisicao(Aquisicao aquisicao) {
		this.aquisicao = aquisicao;
	}

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public void cancelar() {	
		situacao = SituacaoGasto.CANCELADO;
	}

	public void estornar() {
		situacao = SituacaoGasto.ABERTO;
	}
	
	public void pagar() {		
		situacao = SituacaoGasto.PAGO;
	}

}