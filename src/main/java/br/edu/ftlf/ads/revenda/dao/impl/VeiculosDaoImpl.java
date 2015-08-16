package br.edu.ftlf.ads.revenda.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ftlf.ads.revenda.dao.HibernateDao;
import br.edu.ftlf.ads.revenda.dao.VeiculosDao;
import br.edu.ftlf.ads.revenda.model.Veiculo;

public class VeiculosDaoImpl implements
		VeiculosDao {

	private HibernateDao<Veiculo> dao;
	
	public VeiculosDaoImpl(Session session) {
		dao = HibernateDao.create(session, Veiculo.class);
	}

	@Override
	public List<Veiculo> listAll() {
		return dao.listAll();
	}

	@Override
	public Veiculo get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void save(Veiculo veiculo) {
		dao.save(veiculo);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Veiculo> listAll(int start, int limite) {
		return dao.listAll(start, limite);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Veiculo> listStock() {
		StringBuilder hql = new StringBuilder();
		hql.append("select v from Veiculo v where v.id not in(");
		hql.append("	select a.veiculo.id from Aquisicao a where a.situacao = 'OK'");
		hql.append(")");
		Query query = dao.query(hql.toString());
		List list = query.list();
		return list;
	}

}
