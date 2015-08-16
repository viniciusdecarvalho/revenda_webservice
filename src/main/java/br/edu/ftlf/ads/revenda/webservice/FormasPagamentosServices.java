package br.edu.ftlf.ads.revenda.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.edu.ftlf.ads.revenda.dto.FormaPagamentoDto;

@WebService
@SOAPBinding
public interface FormasPagamentosServices {

	@WebMethod
	@XmlElementWrapper(name="formasPagamentos")
	@XmlElement(name="formaPagamento")
	List<FormaPagamentoDto> listaFormasPagamentos();
	
	@WebMethod
	FormaPagamentoDto consultaFormaPagamento(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
	@WebMethod
	@XmlElement(name="formaPagamento")
	FormaPagamentoDto salvaFormaPagamento(@WebParam(name="formaPagamento") @XmlElement(required=true) FormaPagamentoDto formaPagamento);

	@WebMethod
	void deletaFormaPagamento(@WebParam(name="id") @XmlElement(required=true) Integer id);
	
}
