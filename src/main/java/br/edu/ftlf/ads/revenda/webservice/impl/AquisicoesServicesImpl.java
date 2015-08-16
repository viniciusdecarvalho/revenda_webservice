package br.edu.ftlf.ads.revenda.webservice.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.AquisicoesDao;
import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.HibernateUtil;
import br.edu.ftlf.ads.revenda.dao.impl.AquisicoesDaoImpl;
import br.edu.ftlf.ads.revenda.dto.AquisicaoDto;
import br.edu.ftlf.ads.revenda.dto.ClienteDto;
import br.edu.ftlf.ads.revenda.model.Aquisicao;
import br.edu.ftlf.ads.revenda.model.validation.ModelException;
import br.edu.ftlf.ads.revenda.webservice.AquisicoesServices;

@WebService(endpointInterface="br.edu.ftlf.ads.revenda.webservice.AquisicoesServices")
public class AquisicoesServicesImpl implements AquisicoesServices {

	private static final Logger log = LoggerFactory.getLogger(AquisicoesServicesImpl.class);
	
	@Override
	public List<AquisicaoDto> listaAquisicoes() {
		List<AquisicaoDto> aquisicoes = new ArrayList<AquisicaoDto>();
		AquisicoesDao aquisicoesDao = DaoFactory.getAquisicoesDao();
		aquisicoesDao.listAll().stream()
					.map(c -> new AquisicaoDto(c))
					.forEach(aquisicoes::add);
		
		log.debug("{} aquisicoes listados", aquisicoes.size());
		return aquisicoes;
	}

	@Override
	public AquisicaoDto consultaAquisicao(Integer id) {
		AquisicoesDao aquisicoesDao = DaoFactory.getAquisicoesDao();
		Aquisicao aquisicao = aquisicoesDao.get(id);
		log.debug("consulta {} realizada com sucesso", aquisicao);
		return new AquisicaoDto(aquisicao);
	}

	@Override
	public List<AquisicaoDto> consultaAquisicoesPorCliente(ClienteDto clienteDto) {
		return null;
	}

	@Override
	public AquisicaoDto salvaAquisicao(AquisicaoDto dto) {
		Aquisicao aquisicao = dto.getModel();
		
		if (!aquisicao.isValid()) {
			throw new ModelException(aquisicao.validation());
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		AquisicoesDao aquisicoesDao = new AquisicoesDaoImpl(session);
		aquisicoesDao.save(aquisicao);
		
		transaction.commit();
		
		log.debug("aquisicao {} salva com sucesso", aquisicao.getVeiculo());
		return new AquisicaoDto(aquisicao);
	}

	@Override
	public void cancelaAquisicao(AquisicaoDto aquisicao) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		Integer id = aquisicao.getModel().getId();
		DaoFactory.getAquisicoesDao(session).delete(id);
		
		transaction.commit();
		
		log.debug("aquisicao com id: {} removido com sucesso", id);
	}

	@Override
	public List<AquisicaoDto> listaAquisicoesPorData(Date dataInicial,
			Date dataFinal) {
		return null;
	}

}
