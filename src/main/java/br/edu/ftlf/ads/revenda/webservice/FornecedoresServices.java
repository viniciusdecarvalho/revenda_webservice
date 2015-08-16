package br.edu.ftlf.ads.revenda.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.edu.ftlf.ads.revenda.dto.FornecedorDto;

@WebService
@SOAPBinding
public interface FornecedoresServices {

	@WebMethod
	@XmlElementWrapper(name="fornecedores")
	@XmlElement(name="fornecedor")
	List<FornecedorDto> listarFornecedores();
	
	@WebMethod
	@XmlElementWrapper(name="fornecedores")
	@XmlElement(name="fornecedor")
	FornecedorDto consultaFornecedor(@WebParam(name="id") Integer id);
	
	@WebMethod
	@XmlElement(name="fornecedor")
	FornecedorDto salvarFornecedor(@WebParam(name="fornecedor") @XmlElement(required=true) FornecedorDto fornecedor);

	@WebMethod
	void deletarFornecedor(@WebParam(name="id") @XmlElement(required=true) int id);
}
