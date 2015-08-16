package br.edu.ftlf.ads.revenda.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import br.edu.ftlf.ads.revenda.model.Funcionario;

@XmlAccessorType(XmlAccessType.FIELD)
public class FuncionarioDto extends Dto<Funcionario> {

	private Integer id;
	private boolean ativo;
	private boolean gerente;
	private boolean usuario;
	private boolean vendedor;
	
	@XmlElement(required=true)
	private String cpf;
	@XmlElement(required=true)
	private String nome;
	
	@XmlTransient
	private String login;
	@XmlTransient
	private String senha;
	
	public FuncionarioDto() {}
	
	public FuncionarioDto(Funcionario funcionario) {
		super();
		fill(funcionario);
	}

	@Override
	protected Funcionario parse() {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setAtivo(ativo);
		funcionario.setGerente(gerente);
		funcionario.setUsuario(usuario);
		funcionario.setVendedor(vendedor);
		funcionario.setCpf(cpf);
		funcionario.setNome(nome);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		return funcionario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FuncionarioDto fill(Funcionario model) {
		super.fill(model);
		id = model.getId();
		ativo = model.isAtivo();
		gerente = model.isGerente();
		usuario = model.isUsuario();
		vendedor = model.isVendedor();
		cpf = model.getCpf();
		nome = model.getNome();
		login = model.getLogin();
		senha = model.getSenha();
		return this;
	}

}