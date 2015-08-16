package br.edu.ftlf.ads.revenda.webservice.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.ClientesDao;
import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.FornecedoresDao;
import br.edu.ftlf.ads.revenda.dao.HibernateUtil;
import br.edu.ftlf.ads.revenda.dao.VeiculosDao;
import br.edu.ftlf.ads.revenda.dao.impl.ClientesDaoImpl;
import br.edu.ftlf.ads.revenda.dao.impl.FornecedoresDaoImpl;
import br.edu.ftlf.ads.revenda.dao.impl.VeiculosDaoImpl;
import br.edu.ftlf.ads.revenda.dto.ClienteDto;
import br.edu.ftlf.ads.revenda.dto.FornecedorDto;
import br.edu.ftlf.ads.revenda.dto.VeiculoDto;
import br.edu.ftlf.ads.revenda.model.Cliente;
import br.edu.ftlf.ads.revenda.model.Fornecedor;
import br.edu.ftlf.ads.revenda.model.Usuario;
import br.edu.ftlf.ads.revenda.model.Veiculo;
import br.edu.ftlf.ads.revenda.model.validation.ModelException;
import br.edu.ftlf.ads.revenda.webservice.RevendaServicesOLD;

@WebService(name = "RevendaServices", 
			endpointInterface="br.edu.ftlf.ads.revenda.webservice.RevendaServices",
			serviceName="RevendaServices")
@HandlerChain(file="handlers.xml")
public class RevendaServicesImpl implements RevendaServicesOLD {

	private static final Logger log = LoggerFactory.getLogger(RevendaServicesImpl.class);
	
//	@Resource
//	private WebServiceContext context;

	/*
	 * ##################################################################
	 * ###############Cliente Services Implementation####################
	 * ################################################################## 
	 */
	
	@Override
	public List<ClienteDto> listarClientes(int id, String cpfCnpj) {
		List<ClienteDto> clientes = new ArrayList<>();
		
		ClientesDao clientesDao = DaoFactory.getClientesDao();
		if (id != 0) {
			clientes.add(new ClienteDto(clientesDao.get(id)));			
		} else {
			clientesDao.listAll().stream()
						.map(c -> new ClienteDto(c))
						.forEach(clientes::add);
		}
		return clientes;
	}

	@Override
	public ClienteDto salvarCliente(ClienteDto dto) {
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
	public void deletarCliente(int id) {
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		ClientesDao clientesDao = DaoFactory.getClientesDao();
		clientesDao.delete(id);
		
		transaction.commit();
		
		log.debug("cliente com id: {} removido com sucesso", id);
		
	}

	/*
	 * ##################################################################
	 * ###############Forncedor Services Implementation####################
	 * ################################################################## 
	 */
	
	@Override
	public List<FornecedorDto> listarFornecedores(int id, String cpfCnpj) {
		List<FornecedorDto> fornecedores = new ArrayList<>();
		
		FornecedoresDao fornecedoresDao = DaoFactory.getFornecedoresDao();
		if (id != 0) {
			fornecedores.add(new FornecedorDto(fornecedoresDao.get(id)));			
		} else {
			fornecedoresDao.listAll().stream()
						.map(c -> new FornecedorDto(c))
						.forEach(fornecedores::add);
		}
		return fornecedores;
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

	/*
	 * ##################################################################
	 * ###############Veiculo Services Implementation####################
	 * ################################################################## 
	 */
	
	@Override
	public List<VeiculoDto> listarVeiculos() {
		VeiculosDao veiculosDao = DaoFactory.getVeiculosDao();
		return veiculosDao.listAll().stream()
				.map(v -> new VeiculoDto(v))
				.collect(toList());
	}
	
	@Override
	public VeiculoDto salvarVeiculo(VeiculoDto dto) {
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
	public void deletarVeiculo(int id) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		VeiculosDao veiculosDao = DaoFactory.getVeiculosDao();
		veiculosDao.delete(id);
		
		transaction.commit();
		
		log.debug("veiculo com id: {} removido com sucesso", id);
	}

	@Override
	public boolean autentica(Usuario usuario) {
		return DaoFactory.getFuncionariosDao().hasLogin(usuario);
	}
}
