package br.edu.ftlf.ads.revenda.webservice.impl;

import br.edu.ftlf.ads.revenda.webservice.AquisicoesServices;
import br.edu.ftlf.ads.revenda.webservice.ClientesServices;
import br.edu.ftlf.ads.revenda.webservice.FormasPagamentosServices;
import br.edu.ftlf.ads.revenda.webservice.FornecedoresServices;
import br.edu.ftlf.ads.revenda.webservice.FuncionariosServices;
import br.edu.ftlf.ads.revenda.webservice.GastosServices;
import br.edu.ftlf.ads.revenda.webservice.ServicesFactory;
import br.edu.ftlf.ads.revenda.webservice.VeiculosServices;
import br.edu.ftlf.ads.revenda.webservice.VendasServices;

public class ServicesFactoryImpl implements ServicesFactory {

	private AquisicoesServices aquisicoes;
	private ClientesServices clientes;
	private FormasPagamentosServices formasPagamentos;
	private FornecedoresServices fornecedores;
	private FuncionariosServices funcionarios;
	private GastosServices gastos;
	private VeiculosServices veiculos;
	private VendasServices vendas;
	
	
	@Override
	public AquisicoesServices getAquisicoesServices() {
		if (aquisicoes == null) {
			aquisicoes = new AquisicoesServicesImpl();
		}
		return aquisicoes;
	}

	@Override
	public ClientesServices getClientesServices() {
		if (clientes == null) {
			clientes = new ClientesServicesImpl();
		}
		return clientes;
	}

	@Override
	public FormasPagamentosServices getFormasPagamentosServices() {
		if (formasPagamentos == null) {
			formasPagamentos = new FormasPagamentosServicesImpl();
		}
		return formasPagamentos;
	}

	@Override
	public FornecedoresServices getFornecedoresServices() {
		if (fornecedores == null) {
			fornecedores = new FornecedoresServicesImpl();
		}
		return fornecedores;
	}

	@Override
	public FuncionariosServices getFuncionariosServices() {
		if (funcionarios == null) {
			funcionarios = new FuncionariosServicesImpl();
		}
		return funcionarios;
	}

	@Override
	public GastosServices getGastosServices() {
		if (gastos == null) {
			gastos = new GastosServicesImpl();
		}
		return gastos;
	}

	@Override
	public VeiculosServices getVeiculosServices() {
		if (veiculos == null) {
			veiculos = new VeiculosServicesImpl();
		}
		return veiculos;
	}

	@Override
	public VendasServices getVendasServices() {
		if (vendas == null) {
			vendas = new VendasServicesImpl();
		}
		return vendas;
	}

}
