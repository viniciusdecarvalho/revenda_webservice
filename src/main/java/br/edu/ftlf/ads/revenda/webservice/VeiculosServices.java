package br.edu.ftlf.ads.revenda.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.edu.ftlf.ads.revenda.dto.VeiculoDto;

@WebService
@SOAPBinding
public interface VeiculosServices {

	@WebMethod
	@XmlElementWrapper(name="veiculos")
	@XmlElement(name="veiculo")
	List<VeiculoDto> listaVeiculos();
	
	@WebMethod
	@XmlElementWrapper(name="veiculos")
	@XmlElement(name="veiculo")
	List<VeiculoDto> listaVeiculosEmEstoque();
	
	@WebMethod
	@XmlElementWrapper(name="veiculos")
	@XmlElement(name="veiculo")
	VeiculoDto consultaVeiculo(@WebParam(name="id") @XmlElement(required=false)Integer id);
	
	@WebMethod
	@XmlElement(name="veiculo")
	VeiculoDto salvaVeiculo(@WebParam(name="veiculo") @XmlElement(required=true) VeiculoDto veiculo);

	@WebMethod
	void deletaVeiculo(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
}
