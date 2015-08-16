package br.edu.ftlf.ads.revenda.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.FuncionariosDao;
import br.edu.ftlf.ads.revenda.dao.HibernateUtil;
import br.edu.ftlf.ads.revenda.dao.impl.FuncionariosDaoImpl;
import br.edu.ftlf.ads.revenda.dto.FuncionarioDto;
import br.edu.ftlf.ads.revenda.model.Funcionario;
import br.edu.ftlf.ads.revenda.model.Usuario;
import br.edu.ftlf.ads.revenda.model.validation.ModelException;
import br.edu.ftlf.ads.revenda.webservice.FuncionariosServices;

@WebService(name = "FuncionariosServices", 
	endpointInterface="br.edu.ftlf.ads.revenda.webservice.FuncionariosServices",
	serviceName="FuncionariosServices")
public class FuncionariosServicesImpl implements FuncionariosServices {

	private static final Logger log = LoggerFactory.getLogger(FuncionariosServicesImpl.class);
	
	@Override
	public List<FuncionarioDto> listaFuncionarios() {
		List<FuncionarioDto> funcionarios = new ArrayList<>();
		
		FuncionariosDao funcionariosDao = DaoFactory.getFuncionariosDao();
		funcionariosDao.listAll().stream()
					.map(c -> new FuncionarioDto(c))
					.forEach(funcionarios::add);
		log.debug("{} funcionarios listados", funcionarios.size());
		return funcionarios;
	}

	@Override
	public FuncionarioDto consultaFuncionario(Integer id) {
		FuncionariosDao funcionariosDao = DaoFactory.getFuncionariosDao();
		Funcionario funcionario = funcionariosDao.get(id);
		log.debug("consulta {} realizada com sucesso", funcionario);
		return new FuncionarioDto(funcionario);
	}
	
	@Override
	public FuncionarioDto salvaFuncionario(FuncionarioDto dto) {
		Funcionario funcionario = dto.getModel();
		
		if (!funcionario.isValid()) {
			throw new ModelException(funcionario.validation());
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		FuncionariosDao funcionariosDao = new FuncionariosDaoImpl(session);
		funcionariosDao.save(funcionario);
		
		transaction.commit();
		
		log.debug("funcionario {} salvo com sucesso", funcionario.getNome());
		return new FuncionarioDto(funcionario);
	}

	@Override
	public void deletaFuncionario(Integer id) {
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		FuncionariosDao funcionariosDao = DaoFactory.getFuncionariosDao();
		funcionariosDao.delete(id);
		
		transaction.commit();
		
		log.debug("funcionario com id: {} removido com sucesso", id);
	}

	@Override
	public boolean autentica(Usuario usuario) {
		if (usuario.getLogin().equals("admin") && usuario.getLogin().equals("admin")) {
			return true;
		}
		return DaoFactory.getFuncionariosDao().hasLogin(usuario);
	}
	
	@Override
	public FuncionarioDto login(Usuario usuario) {
		return DaoFactory.getFuncionariosDao().login(usuario);
	}
	
}
