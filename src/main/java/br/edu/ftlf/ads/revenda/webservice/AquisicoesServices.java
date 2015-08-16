package br.edu.ftlf.ads.revenda.webservice;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.edu.ftlf.ads.revenda.dto.AquisicaoDto;
import br.edu.ftlf.ads.revenda.dto.ClienteDto;

@WebService
@SOAPBinding
public interface AquisicoesServices {

	@WebMethod
	@XmlElementWrapper(name="aquisicoes")
	@XmlElement(name="aquisicao")
	List<AquisicaoDto> listaAquisicoes();
	
	@WebMethod
	@XmlElementWrapper(name="aquisicoes")
	@XmlElement(name="aquisicao")
	List<AquisicaoDto> listaAquisicoesPorData(@WebParam(name="dataInicial") @XmlElement(required=true) Date dataInicial,
										@WebParam(name="dataFinal") @XmlElement(required=true) Date dataFinal);
	
	@WebMethod
	@XmlElement(name="aquisicao")
	AquisicaoDto consultaAquisicao(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
	@WebMethod
	@XmlElementWrapper(name="aquisicoes")
	@XmlElement(name="aquisicao")
	List<AquisicaoDto> consultaAquisicoesPorCliente(@WebParam(name="cliente")ClienteDto clienteDto);
	
	@WebMethod
	@XmlElement(name="aquisicao")
	AquisicaoDto salvaAquisicao(@WebParam(name="aquisicao") @XmlElement(required=true) AquisicaoDto aquisicao);
	
	@WebMethod	
	void cancelaAquisicao(@WebParam(name="aquisicao") @XmlElement(required=true) AquisicaoDto aquisicao);

}
