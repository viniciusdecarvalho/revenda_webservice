package br.edu.ftlf.ads.revenda.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.edu.ftlf.ads.revenda.dto.ClienteDto;

@WebService
@SOAPBinding
public interface ClientesServices {

	@WebMethod
	@XmlElementWrapper(name="clientes")
	@XmlElement(name="cliente")
	List<ClienteDto> listaClientes();
	
	@WebMethod
	@XmlElement(name="cliente")
	ClienteDto consultaCliente(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
	@WebMethod
	@XmlElement(name="cliente")
	ClienteDto salvaCliente(@WebParam(name="cliente") @XmlElement(required=true) ClienteDto cliente);

	@WebMethod
	void deletaCliente(@WebParam(name="id") @XmlElement(required=true) Integer id);

}
