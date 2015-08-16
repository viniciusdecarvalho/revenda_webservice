package br.edu.ftlf.ads.revenda.dao.impl;

import java.util.List;

import org.hibernate.Session;

import br.edu.ftlf.ads.revenda.dao.FormasPagamentosDao;
import br.edu.ftlf.ads.revenda.dao.HibernateDao;
import br.edu.ftlf.ads.revenda.model.FormaPagamento;

public class FormasPagamentosDaoImpl implements
		FormasPagamentosDao {

	private HibernateDao<FormaPagamento> dao;
	
	public FormasPagamentosDaoImpl(Session session) {
		dao = HibernateDao.create(session, FormaPagamento.class);
	}

	@Override
	public List<FormaPagamento> listAll() {
		return dao.listAll();
	}

	@Override
	public FormaPagamento get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void save(FormaPagamento formaPagamento) {
		dao.save(formaPagamento);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<FormaPagamento> listAll(int start, int limite) {
		return dao.listAll(start, limite);
	}

}
