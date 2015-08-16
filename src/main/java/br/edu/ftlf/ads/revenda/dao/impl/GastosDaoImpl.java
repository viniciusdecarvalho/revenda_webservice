package br.edu.ftlf.ads.revenda.dao.impl;

import java.util.List;

import org.hibernate.Session;

import br.edu.ftlf.ads.revenda.dao.GastosDao;
import br.edu.ftlf.ads.revenda.dao.HibernateDao;
import br.edu.ftlf.ads.revenda.model.Gasto;

public class GastosDaoImpl implements
			GastosDao {

	private HibernateDao<Gasto> dao;
	
	public GastosDaoImpl(Session session) {
		dao = HibernateDao.create(session, Gasto.class);
	}

	@Override
	public List<Gasto> listAll() {
		return dao.listAll();
	}

	@Override
	public Gasto get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void save(Gasto gasto) {
		dao.save(gasto);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Gasto> listAll(int start, int limite) {
		return dao.listAll(start, limite);
	}

}
