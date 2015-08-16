package br.edu.ftlf.ads.revenda.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.edu.ftlf.ads.revenda.model.Fornecedor;

@XmlAccessorType(XmlAccessType.FIELD)
public class FornecedorDto extends Dto<Fornecedor> {

	private Integer id;
	private boolean ativo;
	@XmlElement(required=true)
	private String cpfCnpj;
	@XmlElement(required=true)
	private String razaoSocial;
	
	public FornecedorDto() {}
	
	public FornecedorDto(Fornecedor fornecedor) {
		super();
		fill(fornecedor);
	}

	@Override
	protected Fornecedor parse() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(id);
		fornecedor.setAtivo(ativo);
		fornecedor.setCpfCnpj(cpfCnpj);
		fornecedor.setRazaoSocial(razaoSocial);
		return fornecedor;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public FornecedorDto fill(Fornecedor model) {
		super.fill(model);
		id = model.getId();
		ativo = model.isAtivo();
		cpfCnpj = model.getCpfCnpj();
		razaoSocial = model.getRazaoSocial();
		return this;
	}

}