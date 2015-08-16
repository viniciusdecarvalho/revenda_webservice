package br.edu.ftlf.ads.revenda.webservice;

import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.webservice.impl.AquisicoesServicesImpl;
import br.edu.ftlf.ads.revenda.webservice.impl.ClientesServicesImpl;
import br.edu.ftlf.ads.revenda.webservice.impl.FormasPagamentosServicesImpl;
import br.edu.ftlf.ads.revenda.webservice.impl.FornecedoresServicesImpl;
import br.edu.ftlf.ads.revenda.webservice.impl.FuncionariosServicesImpl;
import br.edu.ftlf.ads.revenda.webservice.impl.GastosServicesImpl;
import br.edu.ftlf.ads.revenda.webservice.impl.VeiculosServicesImpl;
import br.edu.ftlf.ads.revenda.webservice.impl.VendasServicesImpl;

public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		startAutenticacaoServices();
		startFuncionariosServices();
		startFormasPagamentosServices();
		startClientesServices();
		startFornecedoresServices();
		startVeiculosServices();
		startGastosServices();
		startAquisicoesServices();
		startVendasServices();
	}
	
	private static void startAutenticacaoServices() {
		try {
			ClientesServices clientesServices = new ClientesServicesImpl();
			String address = "http://localhost:9000/revenda/autenticacao";
			Endpoint.publish(address, clientesServices);
			log.info("autenticacao service stated in [{}]", address);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private static void startFuncionariosServices() {
		try {
			FuncionariosServices funcionariosServices = new FuncionariosServicesImpl();
			Endpoint.publish("http://localhost:9000/revenda/funcionarios", funcionariosServices);
			log.info("funcionarios service stated");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private static void startClientesServices() {
		try {
			ClientesServices clientesServices = new ClientesServicesImpl();
			Endpoint.publish("http://localhost:9000/revenda/clientes", clientesServices);
			log.info("clientes service stated");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private static void startFornecedoresServices() {
		try {
			FornecedoresServices fornecedoresServices = new FornecedoresServicesImpl();
			Endpoint.publish("http://localhost:9000/revenda/fornecedores", fornecedoresServices);
			log.info("fornecedores service stated");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private static void startVeiculosServices() {
		try {
			VeiculosServices veiculosServices = new VeiculosServicesImpl();
			Endpoint.publish("http://localhost:9000/revenda/veiculos", veiculosServices);
			log.info("veiculos service stated");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private static void startFormasPagamentosServices() {
		try {
			FormasPagamentosServices formasPagamentosServices = new FormasPagamentosServicesImpl();
			Endpoint.publish("http://localhost:9000/revenda/formaspagamentos", formasPagamentosServices);
			log.info("formas pagamentos service stated");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private static void startGastosServices() {
		try {
			GastosServices gastosServices = new GastosServicesImpl();
			Endpoint.publish("http://localhost:9000/revenda/gastos", gastosServices);
			log.info("gastos service stated");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private static void startAquisicoesServices() {
		try {
			AquisicoesServices aquisicoesServices = new AquisicoesServicesImpl();
			Endpoint.publish("http://localhost:9000/revenda/aquisicoes", aquisicoesServices);
			log.info("aquisicoes service stated");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private static void startVendasServices() {
		try {
			VendasServices vendasServices = new VendasServicesImpl();
			Endpoint.publish("http://localhost:9000/revenda/vendas", vendasServices);
			log.info("vendas service stated");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
