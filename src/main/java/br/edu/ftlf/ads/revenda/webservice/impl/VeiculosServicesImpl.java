package br.edu.ftlf.ads.revenda.webservice.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.HibernateUtil;
import br.edu.ftlf.ads.revenda.dao.VeiculosDao;
import br.edu.ftlf.ads.revenda.dao.impl.VeiculosDaoImpl;
import br.edu.ftlf.ads.revenda.dto.VeiculoDto;
import br.edu.ftlf.ads.revenda.model.Veiculo;
import br.edu.ftlf.ads.revenda.model.validation.ModelException;
import br.edu.ftlf.ads.revenda.webservice.VeiculosServices;

@WebService(name = "VeiculosServices", 
	endpointInterface="br.edu.ftlf.ads.revenda.webservice.VeiculosServices",
	serviceName="VeiculosServices")
public class VeiculosServicesImpl implements VeiculosServices {

	private static final Logger log = LoggerFactory.getLogger(VeiculosServicesImpl.class);
	
	@Override
	public List<VeiculoDto> listaVeiculos() {
		VeiculosDao veiculosDao = DaoFactory.getVeiculosDao();
		List<VeiculoDto> veiculos = veiculosDao.listAll().stream()
					.map(v -> new VeiculoDto(v))
					.collect(toList());
		log.debug("{} veiculos listados", veiculos.size());
		return veiculos;
	}
	
	@Override
	public VeiculoDto salvaVeiculo(VeiculoDto dto) {
		Veiculo veiculo = dto.getModel();
		
		if (!veiculo.isValid()) {
			throw new ModelException(veiculo.validation());
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		VeiculosDao veiculosDao = new VeiculosDaoImpl(session);
		veiculosDao.save(veiculo);
		
		transaction.commit();
		
		log.debug("fornecedor {} salvo com sucesso", veiculo.getModelo());
		return new VeiculoDto(veiculo);
	}

	@Override
	public void deletaVeiculo(Integer id) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		VeiculosDao veiculosDao = DaoFactory.getVeiculosDao();
		veiculosDao.delete(id);
		
		transaction.commit();
		
		log.debug("veiculo com id: {} removido com sucesso", id);
	}

	@Override
	public VeiculoDto consultaVeiculo(Integer id) {
		Veiculo veiculo = DaoFactory.getVeiculosDao().get(id);
		log.debug("consulta {} realizada com sucesso", veiculo);
		return new VeiculoDto(veiculo);
	}

	@Override
	public List<VeiculoDto> listaVeiculosEmEstoque() {
		VeiculosDao veiculosDao = DaoFactory.getVeiculosDao();
		List<VeiculoDto> veiculos = veiculosDao.listStock().stream()
					.map(v -> new VeiculoDto(v))
					.collect(toList());
		log.debug("{} veiculos listados", veiculos.size());
		return veiculos;
	}

}
