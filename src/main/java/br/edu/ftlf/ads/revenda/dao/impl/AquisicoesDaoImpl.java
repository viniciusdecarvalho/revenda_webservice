package br.edu.ftlf.ads.revenda.dao.impl;

import java.util.List;

import org.hibernate.Session;

import br.edu.ftlf.ads.revenda.dao.AquisicoesDao;
import br.edu.ftlf.ads.revenda.dao.HibernateDao;
import br.edu.ftlf.ads.revenda.model.Aquisicao;
import br.edu.ftlf.ads.revenda.model.AquisicaoPagamento;

public class AquisicoesDaoImpl implements AquisicoesDao {

	private HibernateDao<Aquisicao> dao;

	public AquisicoesDaoImpl(Session session) {
		dao = HibernateDao.create(session, Aquisicao.class);
	}
	
	@Override
	public List<Aquisicao> listAll() {
		return dao.listAll();
	}

	@Override
	public Aquisicao get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void save(Aquisicao model) {
		dao.save(model);
		Session session = dao.getSession();
		for (AquisicaoPagamento ap : model.getAquisicoesPagamentos()) {
			session.persist(ap.getPagamento());
			session.persist(ap);
		}
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Aquisicao> listAll(int start, int limite) {
		return dao.listAll(start, limite);
	}

}
