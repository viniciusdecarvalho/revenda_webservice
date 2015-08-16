package br.edu.ftlf.ads.revenda.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.edu.ftlf.ads.revenda.model.Cliente;
import br.edu.ftlf.ads.revenda.model.Contato;
import br.edu.ftlf.ads.revenda.model.Endereco;

@XmlAccessorType(XmlAccessType.FIELD)
public class ClienteDto extends Dto<Cliente> {

	private Integer id;
	@XmlElement(required=true)
	private String cpfCnpj;
	@XmlElement(required=true)
	private String razaoSocial;
	private boolean ativo;	
	private String celular;
	private String cep;
	private String cidade;
	private String complemento;
	private String email;
	private String estado;
	private String fone;
	private String logradouro;

	public ClienteDto() {}
	
	public ClienteDto(Cliente cliente) {
		super();
		fill(cliente);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ClienteDto fill(Cliente model) {
		super.fill(model);
		id = model.getId();
		ativo = model.isAtivo();
		cpfCnpj = model.getCpfCnpj();
		razaoSocial = model.getRazaoSocial();
		Contato contato = model.getContato();
		if (contato != null) {
			celular = contato.getCelular();
			fone = contato.getFone();
			email = contato.getEmail();
		}
		Endereco endereco = model.getEndereco();
		if (endereco != null) {
			cep = endereco.getCep();
			cidade = endereco.getCidade();
			complemento = endereco.getComplemento();
			estado = endereco.getEstado();
			logradouro = endereco.getLogradouro();
		}
		return this;
	}
	
	@Override
	protected Cliente parse() {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setAtivo(ativo);
		cliente.setRazaoSocial(razaoSocial);
		cliente.setCpfCnpj(cpfCnpj);
		Contato contato = new Contato();
		contato .setCelular(celular);
		contato.setEmail(email);
		contato.setFone(fone);
		cliente.setContato(contato);
		Endereco endereco = new Endereco();
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		endereco.setComplemento(complemento);
		endereco.setEstado(estado);
		endereco.setLogradouro(logradouro);
		cliente.setEndereco(endereco);
		return cliente;
	}
	
	public static void main(String[] args) {
		
		Cliente c = new Cliente();
		c.setId(1);
		c.setRazaoSocial("NOME");
		c.setAtivo(true);
		c.setCpfCnpj("12345678900");
		
		ClienteDto dto = new ClienteDto(c);
		if (c.equals(dto.parse())) {
			System.out.println("eq");
		} else {
			System.out.println("ne");
		}
	}

}
