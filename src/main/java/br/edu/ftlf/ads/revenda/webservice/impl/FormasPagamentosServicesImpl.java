package br.edu.ftlf.ads.revenda.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.FormasPagamentosDao;
import br.edu.ftlf.ads.revenda.dao.HibernateUtil;
import br.edu.ftlf.ads.revenda.dao.impl.FormasPagamentosDaoImpl;
import br.edu.ftlf.ads.revenda.dto.FormaPagamentoDto;
import br.edu.ftlf.ads.revenda.model.FormaPagamento;
import br.edu.ftlf.ads.revenda.model.validation.ModelException;
import br.edu.ftlf.ads.revenda.webservice.FormasPagamentosServices;

@WebService(endpointInterface="br.edu.ftlf.ads.revenda.webservice.FormasPagamentosServices")
public class FormasPagamentosServicesImpl implements FormasPagamentosServices {

	private static final Logger log = LoggerFactory.getLogger(FormasPagamentosServicesImpl.class);
	
	@Override
	public List<FormaPagamentoDto> listaFormasPagamentos() {
		List<FormaPagamentoDto> fornecedores = new ArrayList<>();
		
		FormasPagamentosDao formasPagamentosDao = DaoFactory.getFormasPagamentosDao();
		formasPagamentosDao.listAll().stream()
						.map(c -> new FormaPagamentoDto(c))
						.forEach(fornecedores::add);
		log.debug("{} fornecedores listados", fornecedores.size());
		return fornecedores;
	}
	
	@Override
	public FormaPagamentoDto consultaFormaPagamento(Integer id) {
		FormasPagamentosDao fornecedoresDao = DaoFactory.getFormasPagamentosDao();	
		FormaPagamento fornecedor = fornecedoresDao.get(id);
		log.debug("consulta {} realizada com sucesso", fornecedor);
		return new FormaPagamentoDto(fornecedor);
	}

	@Override
	public FormaPagamentoDto salvaFormaPagamento(FormaPagamentoDto dto) {
		FormaPagamento formaPagamento = dto.getModel();
		
		if (!formaPagamento.isValid()) {
			throw new ModelException(formaPagamento.validation());
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		FormasPagamentosDao forecedoresDao = new FormasPagamentosDaoImpl(session);
		forecedoresDao.save(formaPagamento);
		
		transaction.commit();
		
		log.debug("fornecedor {} salvo com sucesso", formaPagamento.getNome());
		return new FormaPagamentoDto(formaPagamento);
	}

	@Override
	public void deletaFormaPagamento(Integer id) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		FormasPagamentosDao fornecedoresDao = DaoFactory.getFormasPagamentosDao();
		fornecedoresDao.delete(id);
		
		transaction.commit();
		
		log.debug("fornecedor com id: {} removido com sucesso", id);
	}

}
