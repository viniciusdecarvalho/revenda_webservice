package br.edu.ftlf.ads.revenda.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.ftlf.ads.revenda.dao.FuncionariosDao;
import br.edu.ftlf.ads.revenda.dao.HibernateDao;
import br.edu.ftlf.ads.revenda.dto.FuncionarioDto;
import br.edu.ftlf.ads.revenda.model.Funcionario;
import br.edu.ftlf.ads.revenda.model.Usuario;

public class FuncionariosDaoImpl implements FuncionariosDao {

	private HibernateDao<Funcionario> dao;

	public FuncionariosDaoImpl(Session session) {
		dao = HibernateDao.create(session, Funcionario.class);
	}
	
	@Override
	public List<Funcionario> listAll() {
		return dao.listAll();
	}

	@Override
	public Funcionario get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void save(Funcionario model) {
		dao.save(model);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public boolean hasLogin(Usuario usuario) {
		return login(usuario) != null;
	}

	@Override
	public List<Funcionario> listAll(int start, int limite) {
		return dao.listAll(start, limite);
	}

	@Override
	public FuncionarioDto login(Usuario usuario) {
		Criteria criteria = dao.getCachedCriteria();
		criteria.add(Restrictions.eq("login", usuario.getLogin()));
		criteria.add(Restrictions.eq("senha", usuario.getSenha()));
		return (FuncionarioDto) criteria.uniqueResult();
	}

}
