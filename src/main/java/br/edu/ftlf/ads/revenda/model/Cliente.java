package br.edu.ftlf.ads.revenda.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
public class Cliente extends Model {
	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_CPFCNPJ = "cpfCnpj";
	
	@Column(nullable=false)
	private boolean ativo;

	@Embedded
	private Endereco endereco = new Endereco();
	
	@Embedded
	private Contato contato = new Contato();
	
	@NotEmpty
	@Column(nullable=false)
	private String cpfCnpj;

	@NotEmpty
	@Column(nullable=false)
	private String razaoSocial;

	@OneToMany(mappedBy="cliente")
	private List<Aquisicao> aquisicoes;

	@OneToMany(mappedBy="cliente")
	private List<Venda> vendas;

	public Cliente() {
	}

	public Cliente(Integer id) {
		setId(id);
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Contato getContato() {
		return contato;
	}
	
	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public List<Aquisicao> getAquisicoes() {
		return this.aquisicoes;
	}

	public void setAquisicoes(List<Aquisicao> aquisicoes) {
		this.aquisicoes = aquisicoes;
	}

	public Aquisicao addAquisicao(Aquisicao aquisicao) {
		getAquisicoes().add(aquisicao);
		aquisicao.setCliente(this);

		return aquisicao;
	}

	public Aquisicao removeAquisicao(Aquisicao aquisicao) {
		getAquisicoes().remove(aquisicao);
		aquisicao.setCliente(null);

		return aquisicao;
	}

	public List<Venda> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Venda addVenda(Venda venda) {
		getVendas().add(venda);
		venda.setCliente(this);

		return venda;
	}

	public Venda removeVenda(Venda venda) {
		getVendas().remove(venda);
		venda.setCliente(null);

		return venda;
	}

}