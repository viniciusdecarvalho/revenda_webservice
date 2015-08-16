package br.edu.ftlf.ads.revenda.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.edu.ftlf.ads.revenda.model.Enums.SituacaoVenda;
import br.edu.ftlf.ads.revenda.model.Enums.TipoGasto;

/**
 * The persistent class for the vendas database table.
 * 
 */
@Entity
@Table(name="vendas")
public class Venda extends Model {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy="venda")
	private List<VendaPagamento> vendasPagamentos;

	@NotNull
	@Future
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date data;
	
	@Lob
	private String obs;
	
	@NotNull
	@Min(0)
	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valor;
	
	@Min(0)
	@Column(precision=10, scale=2)
	private BigDecimal valorComissao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="aquisicaoId", nullable=false)
	private Aquisicao aquisicao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="clienteId", nullable=false)
	private Cliente cliente;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="funcionarioId", nullable=false)
	private Funcionario funcionario;
	
	@Enumerated(EnumType.STRING)
	private SituacaoVenda situacao;
	
	public Venda() {
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorComissao() {
		return this.valorComissao;
	}

	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
	}

	public Aquisicao getAquisicao() {
		return aquisicao;
	}
	
	public void setAquisicao(Aquisicao aquisicao) {
		this.aquisicao = aquisicao;
	}
	
	public SituacaoVenda getSituacao() {
		return situacao;
	}
	
	public void setSituacao(SituacaoVenda situacao) {
		this.situacao = situacao;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<VendaPagamento> getVendasPagamentos() {
		return this.vendasPagamentos;
	}

	public void setVendasPagamentos(List<VendaPagamento> vendasPagamentos) {
		this.vendasPagamentos = vendasPagamentos;
	}

	public VendaPagamento addVendasPagamento(VendaPagamento vendasPagamento) {
		getVendasPagamentos().add(vendasPagamento);
		vendasPagamento.setVenda(this);

		return vendasPagamento;
	}

	public VendaPagamento removeVendasPagamento(VendaPagamento vendasPagamento) {
		getVendasPagamentos().remove(vendasPagamento);
		vendasPagamento.setVenda(null);

		return vendasPagamento;
	}
	
	public void cancelar() {
		situacao = Enums.SituacaoVenda.CANCELADO;
		getAquisicao().getGastos().forEach(g -> {
			if (TipoGasto.VENDA.equals(g.getSituacao())) {
				g.cancelar();
			}
		});
	}

}