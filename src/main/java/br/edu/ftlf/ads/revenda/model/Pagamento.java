package br.edu.ftlf.ads.revenda.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ftlf.ads.revenda.model.Enums.TipoPagamento;

/**
 * The persistent class for the pagamentos database table.
 * 
 */
@Entity
@Table(name="pagamentos")
public class Pagamento extends Model {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date data;

	@NotEmpty
	@Column(nullable=false)
	private String descricao;

	@NotNull
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipo;

	@NotNull
	@Min(0)
	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valor;

	@OneToMany(mappedBy="pagamento")
	private List<Gasto> gastos;

	@ManyToOne
	@JoinColumn(name="formaPagamentoId", nullable=false)
	private FormaPagamento formaspagamento;

	public Pagamento() {
	}

	public Pagamento(Integer id) {
		setId(id);
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoPagamento getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public FormaPagamento getFormaspagamento() {
		return this.formaspagamento;
	}

	public void setFormaspagamento(FormaPagamento formaspagamento) {
		this.formaspagamento = formaspagamento;
	}

}