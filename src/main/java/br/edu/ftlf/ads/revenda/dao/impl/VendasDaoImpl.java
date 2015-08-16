package br.edu.ftlf.ads.revenda.dao.impl;

import java.util.List;

import org.hibernate.Session;

import br.edu.ftlf.ads.revenda.dao.HibernateDao;
import br.edu.ftlf.ads.revenda.dao.VendasDao;
import br.edu.ftlf.ads.revenda.model.Venda;

public class VendasDaoImpl implements
		VendasDao {

	private HibernateDao<Venda> dao;
	
	public VendasDaoImpl(Session session) {
		dao = HibernateDao.create(session, Venda.class);
	}

	@Override
	public List<Venda> listAll() {
		return dao.listAll();
	}

	@Override
	public Venda get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void save(Venda venda) {
		dao.save(venda);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Venda> listAll(int start, int limite) {
		return dao.listAll(start, limite);
	}

}
