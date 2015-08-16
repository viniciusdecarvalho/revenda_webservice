package br.edu.ftlf.ads.revenda.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the formaspagamentos database table.
 * 
 */
@Entity
@Table(name="formaspagamentos")
public class FormaPagamento extends Model {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private boolean ativo;

	@Column(nullable=false)
	private boolean credito;

	@Column(nullable=false)
	private boolean debito;

	@NotEmpty
	@Column(nullable=false, length=20)
	private String nome;

	@OneToMany(mappedBy="formaspagamento")
	private List<Pagamento> pagamentos;

	public FormaPagamento() {
	}

	public FormaPagamento(Integer id) {
		setId(id);
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isCredito() {
		return this.credito;
	}

	public void setCredito(boolean credito) {
		this.credito = credito;
	}

	public boolean isDebito() {
		return this.debito;
	}

	public void setDebito(boolean debito) {
		this.debito = debito;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pagamento> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public Pagamento addPagamento(Pagamento pagamento) {
		getPagamentos().add(pagamento);
		pagamento.setFormaspagamento(this);

		return pagamento;
	}

	public Pagamento removePagamento(Pagamento pagamento) {
		getPagamentos().remove(pagamento);
		pagamento.setFormaspagamento(null);

		return pagamento;
	}

}