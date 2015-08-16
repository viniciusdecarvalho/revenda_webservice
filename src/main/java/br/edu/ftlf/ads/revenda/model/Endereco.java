package br.edu.ftlf.ads.revenda.model;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = -3113253692959755213L;
	
	private String cep;
	private String cidade;
	private String complemento;
	private String estado;
	private String logradouro;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

}
