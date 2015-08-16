package br.edu.ftlf.ads.revenda.model;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class Contato implements Serializable {

	private static final long serialVersionUID = 7359381889020640602L;
	
	private String celular;
	private String fone;
	private String email;

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
