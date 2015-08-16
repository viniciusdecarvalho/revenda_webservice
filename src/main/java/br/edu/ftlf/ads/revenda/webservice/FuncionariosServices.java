package br.edu.ftlf.ads.revenda.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.edu.ftlf.ads.revenda.dto.FuncionarioDto;
import br.edu.ftlf.ads.revenda.model.Usuario;

@WebService
@SOAPBinding
public interface FuncionariosServices {

	@WebMethod
	@XmlElementWrapper(name="funcionarios")
	@XmlElement(name="funcionario")
	List<FuncionarioDto> listaFuncionarios();
	
	@WebMethod
	@XmlElement(name="funcionario")
	FuncionarioDto consultaFuncionario(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
	@WebMethod
	@XmlElement(name="funcionario")
	FuncionarioDto salvaFuncionario(@WebParam(name="funcionario") @XmlElement(required=true) FuncionarioDto funcionario);

	@WebMethod
	void deletaFuncionario(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
	@WebMethod
	public boolean autentica(@WebParam(name="usuario")@XmlElement(required=true)Usuario usuario);
	
	@WebMethod
	@XmlElement(name="funcionario")
	public FuncionarioDto login(@WebParam(name="usuario")@XmlElement(required=true)Usuario usuario);
}
