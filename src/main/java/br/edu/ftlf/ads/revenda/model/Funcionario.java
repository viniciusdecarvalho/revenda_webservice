package br.edu.ftlf.ads.revenda.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the funcionarios database table.
 * 
 */
@Entity
@Table(name="funcionarios")
public class Funcionario extends Model {
	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_LOGIN = "login";
	
	@Column(nullable=false)
	private boolean ativo;

	@Column(nullable=false)
	private boolean gerente;
		
	@Column(nullable=false)
	private boolean usuario;

	@Column(nullable=false)
	private boolean vendedor;
	
	@NotEmpty
	@Column(nullable=false)
	private String cpf;

	@NotEmpty
	@Column(nullable=false, length=255)
	private String nome;

	@Column(nullable = true)
	private String login;
	
	@Column(nullable = true)
	private String senha;

	@OneToMany(mappedBy="funcionario")
	private List<Aquisicao> aquisicoes;

	@OneToMany(mappedBy="funcionario")
	private List<Venda> vendas;
	
	@OneToMany(mappedBy="funcionario")
	private List<Gasto> gastos;
	
	public Funcionario() {
	}

	public Funcionario(Integer id) {
		setId(id);
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isGerente() {
		return this.gerente;
	}

	public void setGerente(boolean gerente) {
		this.gerente = gerente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isUsuario() {
		return this.usuario;
	}

	public void setUsuario(boolean usuario) {
		this.usuario = usuario;
	}

	public boolean isVendedor() {
		return this.vendedor;
	}

	public void setVendedor(boolean vendedor) {
		this.vendedor = vendedor;
	}

	public List<Aquisicao> getAquisicoes() {
		return this.aquisicoes;
	}

	public void setAquisicoes(List<Aquisicao> aquisicoes) {
		this.aquisicoes = aquisicoes;
	}

	public Aquisicao addAquisicoe(Aquisicao aquisicoe) {
		getAquisicoes().add(aquisicoe);
		aquisicoe.setFuncionario(this);

		return aquisicoe;
	}

	public Aquisicao removeAquisicoe(Aquisicao aquisicoe) {
		getAquisicoes().remove(aquisicoe);
		aquisicoe.setFuncionario(null);

		return aquisicoe;
	}

	public List<Venda> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Venda addVenda(Venda venda) {
		getVendas().add(venda);
		venda.setFuncionario(this);

		return venda;
	}

	public Venda removeVenda(Venda venda) {
		getVendas().remove(venda);
		venda.setFuncionario(null);

		return venda;
	}
	
	public List<Gasto> getGastos() {
		return gastos;
	}
	
	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}
	
	public Gasto addGasto(Gasto gasto) {
		getGastos().add(gasto);
		gasto.setFuncionario(this);

		return gasto;
	}
	
	public Gasto removeGasto(Gasto gasto) {
		getGastos().remove(gasto);
		gasto.setFuncionario(null);

		return gasto;
	}

}