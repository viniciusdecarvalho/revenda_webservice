package br.edu.ftlf.ads.revenda.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.FornecedoresDao;
import br.edu.ftlf.ads.revenda.dao.HibernateUtil;
import br.edu.ftlf.ads.revenda.dao.impl.FornecedoresDaoImpl;
import br.edu.ftlf.ads.revenda.dto.FornecedorDto;
import br.edu.ftlf.ads.revenda.model.Fornecedor;
import br.edu.ftlf.ads.revenda.model.validation.ModelException;
import br.edu.ftlf.ads.revenda.webservice.FornecedoresServices;

@WebService(name = "FornecedoresServices", 
	endpointInterface="br.edu.ftlf.ads.revenda.webservice.FornecedoresServices",
	serviceName="FornecedoresServices")
public class FornecedoresServicesImpl implements FornecedoresServices {

	private static final Logger log = LoggerFactory.getLogger(FornecedoresServicesImpl.class);
	
	@Override
	public List<FornecedorDto> listarFornecedores() {
		List<FornecedorDto> fornecedores = new ArrayList<>();
		
		FornecedoresDao fornecedoresDao = DaoFactory.getFornecedoresDao();
		fornecedoresDao.listAll().stream()
						.map(c -> new FornecedorDto(c))
						.forEach(fornecedores::add);
		log.debug("{} fornecedores listados", fornecedores.size());
		return fornecedores;
	}
	
	@Override
	public FornecedorDto consultaFornecedor(Integer id) {
		FornecedoresDao fornecedoresDao = DaoFactory.getFornecedoresDao();	
		Fornecedor fornecedor = fornecedoresDao.get(id);
		log.debug("consulta {} realizada com sucesso", fornecedor);
		return new FornecedorDto(fornecedor);
	}

	@Override
	public FornecedorDto salvarFornecedor(FornecedorDto dto) {
		Fornecedor fornecedor = dto.getModel();
		
		if (!fornecedor.isValid()) {
			throw new ModelException(fornecedor.validation());
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		FornecedoresDao forecedoresDao = new FornecedoresDaoImpl(session);
		forecedoresDao.save(fornecedor);
		
		transaction.commit();
		
		log.debug("fornecedor {} salvo com sucesso", fornecedor.getRazaoSocial());
		return new FornecedorDto(fornecedor);
	}

	@Override
	public void deletarFornecedor(int id) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		FornecedoresDao fornecedoresDao = DaoFactory.getFornecedoresDao();
		fornecedoresDao.delete(id);
		
		transaction.commit();
		
		log.debug("fornecedor com id: {} removido com sucesso", id);
	}

}
