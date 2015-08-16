package br.edu.ftlf.ads.revenda.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.edu.ftlf.ads.revenda.dto.ClienteDto;
import br.edu.ftlf.ads.revenda.dto.VendaDto;

@WebService
@SOAPBinding
public interface VendasServices {

//	@WebMethod
//	@XmlElementWrapper(name="vendas")
//	@XmlElement(name="venda")
//	List<VendaDto> listaVendas(
//			@WebParam(name="dataInicial") @XmlElement(required=true) Date dataInicial,
//			@WebParam(name="dataFinal") @XmlElement(required=true) Date dataFinal);
	
	@WebMethod
	@XmlElementWrapper(name="vendas")
	@XmlElement(name="venda")
	List<VendaDto> listaVendas();
	
	@WebMethod
	@XmlElement(name="venda")
	VendaDto consultaVenda(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
	@WebMethod
	@XmlElementWrapper(name="vendas")
	@XmlElement(name="venda")
	List<VendaDto> consultaVendasPorCliente(@WebParam(name="cliente")ClienteDto clienteDto);
	
	@WebMethod
	@XmlElement(name="venda")
	VendaDto salvaVenda(@WebParam(name="venda") @XmlElement(required=true) VendaDto venda);

	@WebMethod	
	void cancelaVenda(@WebParam(name="venda") @XmlElement(required=true) VendaDto venda);

}
