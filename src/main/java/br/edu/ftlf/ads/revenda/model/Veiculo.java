package br.edu.ftlf.ads.revenda.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the veiculos database table.
 * 
 */
@Entity
@Table(name="veiculos")
public class Veiculo extends Model {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(nullable=false)
	private String ano;

	@NotEmpty
	@Column(nullable=false)
	private String chassi;

	@NotEmpty
	@Column(nullable=false)
	private String especie;

	@NotEmpty
	@Column(nullable=false)
	private String marca;

	@NotEmpty
	@Column(nullable=false)
	private String modelo;

	@NotEmpty
	@Column(length=7)
	private String placa;

	@NotEmpty
	@Column(length=9)
	private String renavan;

	@OneToMany(mappedBy="veiculo")
	private List<Aquisicao> aquisicoes;

	public Veiculo() {
	}

	public Veiculo(Integer id) {
		setId(id);
	}

	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getChassi() {
		return this.chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getEspecie() {
		return this.especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavan() {
		return this.renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public List<Aquisicao> getAquisicoes() {
		return this.aquisicoes;
	}

	public void setAquisicoes(List<Aquisicao> aquisicoes) {
		this.aquisicoes = aquisicoes;
	}

	public Aquisicao addAquisicao(Aquisicao aquisicao) {
		getAquisicoes().add(aquisicao);
		aquisicao.setVeiculo(this);

		return aquisicao;
	}

	public Aquisicao removeAquisicoe(Aquisicao aquisicao) {
		getAquisicoes().remove(aquisicao);
		aquisicao.setVeiculo(null);

		return aquisicao;
	}

}