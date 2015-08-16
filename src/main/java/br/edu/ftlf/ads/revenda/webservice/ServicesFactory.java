package br.edu.ftlf.ads.revenda.webservice;

public interface ServicesFactory {

	AquisicoesServices getAquisicoesServices();
	
	ClientesServices getClientesServices();
	
	FormasPagamentosServices getFormasPagamentosServices();
	
	FornecedoresServices getFornecedoresServices();
	
	FuncionariosServices getFuncionariosServices();
	
	GastosServices getGastosServices();
	
	VeiculosServices getVeiculosServices();
	
	VendasServices getVendasServices();
	
}
