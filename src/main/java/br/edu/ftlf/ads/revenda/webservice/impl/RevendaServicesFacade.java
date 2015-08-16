package br.edu.ftlf.ads.revenda.webservice.impl;

import java.util.Date;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import br.edu.ftlf.ads.revenda.dto.AquisicaoDto;
import br.edu.ftlf.ads.revenda.dto.ClienteDto;
import br.edu.ftlf.ads.revenda.dto.FormaPagamentoDto;
import br.edu.ftlf.ads.revenda.dto.FornecedorDto;
import br.edu.ftlf.ads.revenda.dto.FuncionarioDto;
import br.edu.ftlf.ads.revenda.dto.GastoDto;
import br.edu.ftlf.ads.revenda.dto.VeiculoDto;
import br.edu.ftlf.ads.revenda.dto.VendaDto;
import br.edu.ftlf.ads.revenda.model.Usuario;
import br.edu.ftlf.ads.revenda.webservice.RevendaServices;
import br.edu.ftlf.ads.revenda.webservice.ServicesFactory;

@WebService(name = "RevendaServices", 
			endpointInterface="br.edu.ftlf.ads.revenda.webservice.RevendaServices",
			serviceName="RevendaServices")
@HandlerChain(file="handlers.xml")
public class RevendaServicesFacade implements RevendaServices {

//	@Resource
//	private WebServiceContext context;
	private ServicesFactory services;

	public RevendaServicesFacade() {
		this.services = new ServicesFactoryImpl();
	}
	
	@Override
	public List<AquisicaoDto> listaAquisicoes() {
		return services.getAquisicoesServices().listaAquisicoes();
	}
	
	@Override
	public List<AquisicaoDto> listaAquisicoesPorData(Date dataInicial,
			Date dataFinal) {
		return services.getAquisicoesServices().listaAquisicoesPorData(dataInicial, dataFinal);
	}

	@Override
	public AquisicaoDto consultaAquisicao(Integer id) {
		return services.getAquisicoesServices().consultaAquisicao(id);
	}

	@Override
	public List<AquisicaoDto> consultaAquisicoesPorCliente(ClienteDto clienteDto) {
		return services.getAquisicoesServices().consultaAquisicoesPorCliente(clienteDto);
	}

	@Override
	public AquisicaoDto salvaAquisicao(AquisicaoDto aquisicao) {
		return services.getAquisicoesServices().salvaAquisicao(aquisicao);
	}

	@Override
	public void cancelaAquisicao(AquisicaoDto aquisicao) {
		services.getAquisicoesServices().cancelaAquisicao(aquisicao);
	}

	@Override
	public List<ClienteDto> listaClientes() {
		return services.getClientesServices().listaClientes();
	}

	@Override
	public ClienteDto consultaCliente(Integer id) {
		return services.getClientesServices().consultaCliente(id);
	}

	@Override
	public ClienteDto salvaCliente(ClienteDto cliente) {
		return services.getClientesServices().salvaCliente(cliente);
	}

	@Override
	public void deletaCliente(Integer id) {
		services.getClientesServices().deletaCliente(id);
	}

	@Override
	public List<FormaPagamentoDto> listaFormasPagamentos() {
		return services.getFormasPagamentosServices().listaFormasPagamentos();
	}

	@Override
	public FormaPagamentoDto consultaFormaPagamento(Integer id) {
		return services.getFormasPagamentosServices().consultaFormaPagamento(id);
	}

	@Override
	public FormaPagamentoDto salvaFormaPagamento(
			FormaPagamentoDto formaPagamento) {
		return services.getFormasPagamentosServices().salvaFormaPagamento(formaPagamento);
	}

	@Override
	public void deletaFormaPagamento(Integer id) {
		services.getFormasPagamentosServices().deletaFormaPagamento(id);
	}

	@Override
	public List<FornecedorDto> listarFornecedores() {
		return services.getFornecedoresServices().listarFornecedores();
	}

	@Override
	public FornecedorDto consultaFornecedor(Integer id) {
		return services.getFornecedoresServices().consultaFornecedor(id);
	}

	@Override
	public FornecedorDto salvarFornecedor(FornecedorDto fornecedor) {
		return services.getFornecedoresServices().salvarFornecedor(fornecedor);
	}

	@Override
	public void deletarFornecedor(int id) {
		services.getFornecedoresServices().deletarFornecedor(id);
	}

	@Override
	public List<FuncionarioDto> listaFuncionarios() {
		return services.getFuncionariosServices().listaFuncionarios();
	}

	@Override
	public FuncionarioDto consultaFuncionario(Integer id) {
		return services.getFuncionariosServices().consultaFuncionario(id);
	}

	@Override
	public FuncionarioDto salvaFuncionario(FuncionarioDto funcionario) {
		return services.getFuncionariosServices().salvaFuncionario(funcionario);
	}

	@Override
	public void deletaFuncionario(Integer id) {
		services.getFuncionariosServices().deletaFuncionario(id);
	}

	@Override
	public boolean autentica(Usuario usuario) {
		return services.getFuncionariosServices().autentica(usuario);
	}

	@Override
	public FuncionarioDto login(Usuario usuario) {
		return services.getFuncionariosServices().login(usuario);
	}

	@Override
	public List<GastoDto> listaGastos() {
		return services.getGastosServices().listaGastos();
	}

	@Override
	public GastoDto consultaGasto(Integer id) {
		return services.getGastosServices().consultaGasto(id);
	}

	@Override
	public List<GastoDto> consultaGastos(AquisicaoDto aquisicao) {
		return services.getGastosServices().consultaGastos(aquisicao);
	}

	@Override
	public GastoDto salvaGasto(GastoDto gasto) {
		return services.getGastosServices().salvaGasto(gasto);
	}

	@Override
	public void deletaGasto(Integer id) {
		services.getGastosServices().deletaGasto(id);
	}

	@Override
	public List<VeiculoDto> listaVeiculos() {
		return services.getVeiculosServices().listaVeiculos();
	}

	@Override
	public VeiculoDto consultaVeiculo(Integer id) {
		return services.getVeiculosServices().consultaVeiculo(id);
	}

	@Override
	public VeiculoDto salvaVeiculo(VeiculoDto veiculo) {
		return services.getVeiculosServices().salvaVeiculo(veiculo);
	}

	@Override
	public void deletaVeiculo(Integer id) {
		services.getVeiculosServices().deletaVeiculo(id);
	}

	@Override
	public List<VendaDto> listaVendas() {
		return services.getVendasServices().listaVendas();
	}

	@Override
	public VendaDto consultaVenda(Integer id) {
		return services.getVendasServices().consultaVenda(id);
	}

	@Override
	public List<VendaDto> consultaVendasPorCliente(ClienteDto clienteDto) {
		return services.getVendasServices().consultaVendasPorCliente(clienteDto);
	}

	@Override
	public VendaDto salvaVenda(VendaDto venda) {
		return services.getVendasServices().salvaVenda(venda);
	}

	@Override
	public void cancelaVenda(VendaDto venda) {
		services.getVendasServices().cancelaVenda(venda);
	}

	@Override
	public List<VeiculoDto> listaVeiculosEmEstoque() {
		return services.getVeiculosServices().listaVeiculosEmEstoque();
	}

}