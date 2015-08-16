package br.edu.ftlf.ads.revenda.dao.impl;

import java.util.List;

import org.hibernate.Session;

import br.edu.ftlf.ads.revenda.dao.FornecedoresDao;
import br.edu.ftlf.ads.revenda.dao.HibernateDao;
import br.edu.ftlf.ads.revenda.model.Fornecedor;

public class FornecedoresDaoImpl implements FornecedoresDao {

	private HibernateDao<Fornecedor> dao;

	public FornecedoresDaoImpl(Session session) {
		dao = HibernateDao.create(session, Fornecedor.class);
	}
	
	@Override
	public List<Fornecedor> listAll() {
		return dao.listAll();
	}

	@Override
	public Fornecedor get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void save(Fornecedor model) {
		dao.save(model);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Fornecedor> listAll(int start, int limite) {
		return dao.listAll(start, limite);
	}

}
