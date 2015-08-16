package br.edu.ftlf.ads.revenda.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.edu.ftlf.ads.revenda.dto.ClienteDto;
import br.edu.ftlf.ads.revenda.dto.FornecedorDto;
import br.edu.ftlf.ads.revenda.dto.VeiculoDto;
import br.edu.ftlf.ads.revenda.model.Usuario;

@WebService
@SOAPBinding
public interface RevendaServicesOLD {
	
	@WebMethod
	boolean autentica(@WebParam(name="usuario") @XmlElement(required=true) Usuario usuario);
	
	@WebMethod
	@XmlElementWrapper(name="clientes")
	@XmlElement(name="cliente")
	List<ClienteDto> listarClientes(@WebParam(name="id") int id, @WebParam(name="cpfCnpj") String cpfCnpj);
	
	@WebMethod
	@XmlElement(name="cliente")
	ClienteDto salvarCliente(@WebParam(name="cliente") @XmlElement(required=true) ClienteDto cliente);

	@WebMethod
	void deletarCliente(@WebParam(name="id") @XmlElement(required=true) int id);

	@WebMethod
	@XmlElementWrapper(name="fornecedores")
	@XmlElement(name="fornecedor")
	List<FornecedorDto> listarFornecedores(@WebParam(name="id") int id, @WebParam(name="cpfCnpj") String cpfCnpj);
	
	@WebMethod
	@XmlElement(name="fornecedor")
	FornecedorDto salvarFornecedor(@WebParam(name="fornecedor") @XmlElement(required=true) FornecedorDto fornecedor);

	@WebMethod
	void deletarFornecedor(@WebParam(name="id") @XmlElement(required=true) int id);
	
	@WebMethod
	@XmlElementWrapper(name="veiculos")
	@XmlElement(name="veiculo")
	List<VeiculoDto> listarVeiculos();
	
	@WebMethod
	@XmlElement(name="veiculo")
	VeiculoDto salvarVeiculo(@WebParam(name="veiculo") @XmlElement(required=true) VeiculoDto veiculo);

	@WebMethod
	void deletarVeiculo(@WebParam(name="id") @XmlElement(required=true) int id);

}