package br.edu.ftlf.ads.revenda.dao;

import org.hibernate.Session;

import br.edu.ftlf.ads.revenda.dao.impl.AquisicoesDaoImpl;
import br.edu.ftlf.ads.revenda.dao.impl.ClientesDaoImpl;
import br.edu.ftlf.ads.revenda.dao.impl.FormasPagamentosDaoImpl;
import br.edu.ftlf.ads.revenda.dao.impl.FornecedoresDaoImpl;
import br.edu.ftlf.ads.revenda.dao.impl.FuncionariosDaoImpl;
import br.edu.ftlf.ads.revenda.dao.impl.GastosDaoImpl;
import br.edu.ftlf.ads.revenda.dao.impl.VeiculosDaoImpl;
import br.edu.ftlf.ads.revenda.dao.impl.VendasDaoImpl;

public class DaoFactory {

	public static ClientesDao getClientesDao(Session session) {
		return new ClientesDaoImpl(session);
	}
	
	public static ClientesDao getClientesDao() {
		return getClientesDao(getSession());
	}

	private static Session getSession() {
		return HibernateUtil.getSession();
	}
	
	public static FornecedoresDao getFornecedoresDao() {
		return new FornecedoresDaoImpl(getSession());
	}

	public static VeiculosDao getVeiculosDao() {
		return new VeiculosDaoImpl(getSession());
	}

	public static FuncionariosDao getFuncionariosDao() {
		return new FuncionariosDaoImpl(getSession());
	}

	public static FormasPagamentosDao getFormasPagamentosDao() {
		return new FormasPagamentosDaoImpl(getSession());
	}

	public static GastosDao getGastosDao() {
		return new GastosDaoImpl(getSession());
	}

	public static AquisicoesDao getAquisicoesDao(Session session) {
		return new AquisicoesDaoImpl(session);
	}

	public static AquisicoesDao getAquisicoesDao() {
		return getAquisicoesDao(getSession());
	}

	public static VendasDao getVendasDao(Session session) {
		return new VendasDaoImpl(session);
	}
	
	public static VendasDao getVendasDao() {
		return new VendasDaoImpl(getSession());
	}
}
