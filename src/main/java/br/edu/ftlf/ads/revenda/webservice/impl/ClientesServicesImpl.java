package br.edu.ftlf.ads.revenda.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.ClientesDao;
import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.HibernateUtil;
import br.edu.ftlf.ads.revenda.dao.impl.ClientesDaoImpl;
import br.edu.ftlf.ads.revenda.dto.ClienteDto;
import br.edu.ftlf.ads.revenda.model.Cliente;
import br.edu.ftlf.ads.revenda.model.validation.ModelException;
import br.edu.ftlf.ads.revenda.webservice.ClientesServices;

@WebService(name = "ClientesServices", 
	endpointInterface="br.edu.ftlf.ads.revenda.webservice.ClientesServices",
	serviceName="ClientesServices")
public class ClientesServicesImpl implements ClientesServices {

	private static final Logger log = LoggerFactory.getLogger(ClientesServicesImpl.class);
	
	@Override
	public List<ClienteDto> listaClientes() {
		List<ClienteDto> clientes = new ArrayList<>();
		
		ClientesDao clientesDao = DaoFactory.getClientesDao();
		clientesDao.listAll().stream()
					.map(c -> new ClienteDto(c))
					.forEach(clientes::add);
		
		log.debug("{} clientes listados", clientes.size());
		return clientes;
	}

	@Override
	public ClienteDto consultaCliente(Integer id) {
		ClientesDao clientesDao = DaoFactory.getClientesDao();
		Cliente cliente = clientesDao.get(id);
		log.debug("consulta {} realizada com sucesso", cliente);
		return new ClienteDto(cliente);
	}

	
	@Override
	public ClienteDto salvaCliente(ClienteDto dto) {
		Cliente cliente = dto.getModel();
		
		if (!cliente.isValid()) {
			throw new ModelException(cliente.validation());
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		ClientesDao clientesDao = new ClientesDaoImpl(session);
		clientesDao.save(cliente);
		
		transaction.commit();
		
		log.debug("cliente {} salvo com sucesso", cliente.getRazaoSocial());
		return new ClienteDto(cliente);
	}

	@Override
	public void deletaCliente(Integer id) {
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		ClientesDao clientesDao = DaoFactory.getClientesDao(session);
		clientesDao.delete(id);
		
		transaction.commit();
		
		log.debug("cliente com id: {} removido com sucesso", id);
		
	}

}
