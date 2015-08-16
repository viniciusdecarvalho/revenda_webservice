package br.edu.ftlf.ads.revenda.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {

	@NotEmpty
	@XmlElement(required=true)
	private String login;
	
	@NotEmpty
	@XmlElement(required=true)
	private String senha;

	public Usuario() {
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
	
	@Override
	public String toString() {
		return login;
	}
}