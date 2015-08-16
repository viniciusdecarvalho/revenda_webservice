package br.edu.ftlf.ads.revenda.webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding
public interface RevendaServices 
		extends AquisicoesServices, ClientesServices, FormasPagamentosServices, 
		FornecedoresServices, FuncionariosServices,
		GastosServices, VeiculosServices, VendasServices {

}