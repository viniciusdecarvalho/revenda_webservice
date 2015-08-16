package br.edu.ftlf.ads.revenda.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ftlf.ads.revenda.model.Enums.SituacaoAquisicao;
import br.edu.ftlf.ads.revenda.model.Enums.SituacaoGasto;
import br.edu.ftlf.ads.revenda.model.Enums.TipoGasto;

/**
 * The persistent class for the aquisicoes database table.
 * 
 */
@Entity
@Table(name="aquisicoes")
public class Aquisicao extends Model {
	private static final long serialVersionUID = 1L;

	public enum Combustivel { GASOLINA, ALCOOL, DIESEL, FLEX, GAS_NATURAL }
	
	@NotNull
	@Column(nullable=false)
	private String cidade;

	@NotNull
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private Combustivel combustivel;

	@NotNull
	@Column(nullable=false)
	private String cor;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date data;

	@Min(0)
	@Column(nullable=false)
	private int km;

	@Lob
	private String obs;

	@NotEmpty
	@Column(nullable=false)
	private String uf;

	@NotNull
	@Min(0)
	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valor;
	
	@NotNull
	@Min(0)
	@Column(precision=10, scale=2)
	private BigDecimal valorComissao;

	@NotNull
	@Min(0)
	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valorPedido;

	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="clienteId", nullable=false)
	private Cliente cliente;

	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="vendedorId", nullable=false)
	private Funcionario funcionario;

	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="veiculoId", nullable=false)
	private Veiculo veiculo;

	@OneToMany(mappedBy="aquisicao", fetch=FetchType.EAGER)
	private List<AquisicaoPagamento> aquisicoesPagamentos;

	@OneToMany(mappedBy="aquisicao")
	private List<Gasto> gastos;

	@Enumerated(EnumType.STRING)
	@Column(name="situacao")
	private SituacaoAquisicao situacao;

	public Aquisicao() {
		aquisicoesPagamentos = new ArrayList<AquisicaoPagamento>();
		gastos = new ArrayList<Gasto>();
	}

	public Aquisicao(Integer id) {
		this();
		setId(id);
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Combustivel getCombustivel() {
		return this.combustivel;
	}

	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}

	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getKm() {
		return this.km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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

	public BigDecimal getValorPedido() {
		return this.valorPedido;
	}

	public void setValorPedido(BigDecimal valorPedido) {
		this.valorPedido = valorPedido;
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

	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public SituacaoAquisicao getSituacao() {
		return situacao;
	}
	
	public void setSituacao(SituacaoAquisicao situacao) {
		this.situacao = situacao;
	}

	public List<AquisicaoPagamento> getAquisicoesPagamentos() {
		return this.aquisicoesPagamentos;
	}

	public void setAquisicoesPagamentos(List<AquisicaoPagamento> aquisicoesPagamentos) {
		this.aquisicoesPagamentos = aquisicoesPagamentos;
	}

	public AquisicaoPagamento addAquisicoesPagamento(AquisicaoPagamento aquisicoesPagamento) {
		getAquisicoesPagamentos().add(aquisicoesPagamento);
		aquisicoesPagamento.setAquisicao(this);

		return aquisicoesPagamento;
	}

	public AquisicaoPagamento removeAquisicoesPagamento(AquisicaoPagamento aquisicoesPagamento) {
		getAquisicoesPagamentos().remove(aquisicoesPagamento);
		aquisicoesPagamento.setAquisicao(null);

		return aquisicoesPagamento;
	}

	public List<Gasto> getGastos() {
		return this.gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	public Gasto addGasto(Gasto gasto) {
		getGastos().add(gasto);
		gasto.setAquisicao(this);

		return gasto;
	}

	public Gasto removeGasto(Gasto gasto) {
		getGastos().remove(gasto);
		gasto.setAquisicao(null);

		return gasto;
	}
	
	public void cancelar() {
		situacao = Enums.SituacaoAquisicao.CANCELADO;
		getGastos().forEach(g -> {
			if (TipoGasto.AQUISICAO.equals(g.getSituacao())) {
				g.setSituacao(SituacaoGasto.CANCELADO);
			}
		});
	}

}