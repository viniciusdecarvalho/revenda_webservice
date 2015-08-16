package br.edu.ftlf.ads.revenda.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.edu.ftlf.ads.revenda.dto.AquisicaoDto;
import br.edu.ftlf.ads.revenda.dto.GastoDto;

@WebService
@SOAPBinding
public interface GastosServices {

	@WebMethod
	@XmlElementWrapper(name="gastos")
	@XmlElement(name="gasto")
	List<GastoDto> listaGastos();
	
	@WebMethod
	@XmlElement(name="gasto")
	GastoDto consultaGasto(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
	@WebMethod
	@XmlElementWrapper(name="gastos")
	@XmlElement(name="gasto")
	List<GastoDto> consultaGastos(@WebParam(name="aquisicao") @XmlElement(required=true) AquisicaoDto aquisicao);
	
	@WebMethod
	@XmlElement(name="gasto")
	GastoDto salvaGasto(@WebParam(name="gasto") @XmlElement(required=true) GastoDto gasto);

	@WebMethod
	void deletaGasto(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
}
