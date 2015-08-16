package br.edu.ftlf.ads.revenda.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.HibernateUtil;
import br.edu.ftlf.ads.revenda.dao.VendasDao;
import br.edu.ftlf.ads.revenda.dao.impl.VendasDaoImpl;
import br.edu.ftlf.ads.revenda.dto.ClienteDto;
import br.edu.ftlf.ads.revenda.dto.VendaDto;
import br.edu.ftlf.ads.revenda.model.Venda;
import br.edu.ftlf.ads.revenda.model.validation.ModelException;
import br.edu.ftlf.ads.revenda.webservice.VendasServices;

@WebService(endpointInterface="br.edu.ftlf.ads.revenda.webservice.VendasServices")
public class VendasServicesImpl implements VendasServices {

	private static final Logger log = LoggerFactory.getLogger(VendasServicesImpl.class);
	
	@Override
	public List<VendaDto> listaVendas() {
		List<VendaDto> vendas = new ArrayList<VendaDto>();
		VendasDao vendasDao = DaoFactory.getVendasDao();
		vendasDao.listAll().stream()
					.map(c -> new VendaDto(c))
					.forEach(vendas::add);
		
		log.debug("{} vendas listados", vendas.size());
		return vendas;
	}

	@Override
	public VendaDto consultaVenda(Integer id) {
		VendasDao vendasDao = DaoFactory.getVendasDao();
		Venda venda = vendasDao.get(id);
		log.debug("consulta {} realizada com sucesso", venda);
		return new VendaDto(venda);
	}

	@Override
	public List<VendaDto> consultaVendasPorCliente(ClienteDto clienteDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VendaDto salvaVenda(VendaDto dto) {
		Venda venda = dto.getModel();
		
		if (!venda.isValid()) {
			throw new ModelException(venda.validation());
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		VendasDao vendasDao = new VendasDaoImpl(session);
		vendasDao.save(venda);
		
		transaction.commit();
		
		log.debug("venda {} salva com sucesso", venda.getAquisicao().getVeiculo());
		return new VendaDto(venda);
	}

	@Override
	public void cancelaVenda(VendaDto venda) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		Integer id = venda.getModel().getId();
		DaoFactory.getVendasDao(session).delete(id);
		
		transaction.commit();
		
		log.debug("venda com id: {} removido com sucesso", id);
	}

}
