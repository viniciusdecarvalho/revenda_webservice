package br.edu.ftlf.ads.revenda.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.AquisicoesDao;
import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.GastosDao;
import br.edu.ftlf.ads.revenda.dao.HibernateUtil;
import br.edu.ftlf.ads.revenda.dao.impl.GastosDaoImpl;
import br.edu.ftlf.ads.revenda.dto.AquisicaoDto;
import br.edu.ftlf.ads.revenda.dto.GastoDto;
import br.edu.ftlf.ads.revenda.model.Aquisicao;
import br.edu.ftlf.ads.revenda.model.Gasto;
import br.edu.ftlf.ads.revenda.model.validation.ModelException;
import br.edu.ftlf.ads.revenda.webservice.GastosServices;

@WebService(endpointInterface="br.edu.ftlf.ads.revenda.webservice.GastosServices")
public class GastosServicesImpl implements GastosServices {

private static final Logger log = LoggerFactory.getLogger(GastosServicesImpl.class);
	
	@Override
	public List<GastoDto> listaGastos() {
		List<GastoDto> gastos = new ArrayList<>();
		
		GastosDao gastosDao = DaoFactory.getGastosDao();
		gastosDao.listAll().stream()
					.map(c -> new GastoDto(c))
					.forEach(gastos::add);
		log.debug("{} gastos listados", gastos.size());
		return gastos;
	}

	@Override
	public GastoDto consultaGasto(Integer id) {
		GastosDao gastosDao = DaoFactory.getGastosDao();
		Gasto gasto = gastosDao.get(id);
		log.debug("consulta {} realizada com sucesso", gasto);
		return new GastoDto(gasto);
	}

	
	@Override
	public GastoDto salvaGasto(GastoDto dto) {
		Gasto gasto = dto.getModel();
		
		if (!gasto.isValid()) {
			throw new ModelException(gasto.validation());
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		GastosDao gastosDao = new GastosDaoImpl(session);
		gastosDao.save(gasto);
		
		transaction.commit();
		
		log.debug("gasto {} salvo com sucesso", gasto.getPagamento().getDescricao());
		return new GastoDto(gasto);
	}

	@Override
	public void deletaGasto(Integer id) {
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		GastosDao gastosDao = DaoFactory.getGastosDao();
		gastosDao.delete(id);
		
		transaction.commit();
		
		log.debug("gasto com id: {} removido com sucesso", id);
		
	}

	@Override
	public List<GastoDto> consultaGastos(AquisicaoDto aquisicao) {
	 	AquisicoesDao aquisicoesDao =  DaoFactory.getAquisicoesDao();
	 	Integer aquisicaoId = aquisicao.getModel().getId();
	 	Aquisicao aquisicaoDB = aquisicoesDao.get(aquisicaoId);
	 	return aquisicaoDB.getGastos().stream().map(g -> new GastoDto(g)).collect(Collectors.toList());
	}

}
