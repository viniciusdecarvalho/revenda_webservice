package br.edu.ftlf.ads.revenda.dao.impl;

import java.util.List;

import org.hibernate.Session;

import br.edu.ftlf.ads.revenda.dao.ClientesDao;
import br.edu.ftlf.ads.revenda.dao.HibernateDao;
import br.edu.ftlf.ads.revenda.model.Cliente;

public class ClientesDaoImpl implements
		ClientesDao {

	private HibernateDao<Cliente> dao;
	
	public ClientesDaoImpl(Session session) {
		dao = HibernateDao.create(session, Cliente.class);
	}

	@Override
	public List<Cliente> listAll() {
		return dao.listAll();
	}

	@Override
	public Cliente get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void save(Cliente cliente) {
		dao.save(cliente);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Cliente> listAll(int start, int limite) {
		return dao.listAll(start, limite);
	}

}
