package br.edu.ftlf.ads.revenda.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.edu.ftlf.ads.revenda.model.Enums.TipoPagamento;
import br.edu.ftlf.ads.revenda.model.Pagamento;
import br.edu.ftlf.ads.revenda.model.Venda;
import br.edu.ftlf.ads.revenda.model.VendaPagamento;

@XmlAccessorType(XmlAccessType.FIELD)
public class VendaDto extends Dto<Venda> {

	private Integer id;
	
	@XmlElement(required=true)
	private Date data;
	private String obs;
	
	@XmlElement(required=true)
	private BigDecimal valor;
	private BigDecimal valorComissao;
	
	@XmlElement(required=true)
	private AquisicaoDto aquisicao;
	
	@XmlElement(required=true)
	private ClienteDto cliente;
	
	@XmlElement(required=true)
	private FuncionarioDto vendedor;
	
	@XmlElement(required=true)
	private List<PagamentoDto> pagamentos;
	
	public VendaDto(Venda venda) {
		super();
		fill(venda);
	}

	@Override
	protected Venda parse() {
		Venda venda = new Venda();
		venda.setId(id);
		venda.setData(data);
		venda.setObs(obs);
		venda.setValor(valor);
		venda.setValorComissao(valorComissao);
		venda.setFuncionario(vendedor.getModel());
		venda.setAquisicao(aquisicao.getModel());
		venda.setCliente(cliente.getModel());
		for (PagamentoDto pagamentoDto : pagamentos) {
			Pagamento pagamento = pagamentoDto.getModel();
			pagamento.setData(venda.getData());
			pagamento.setTipo(TipoPagamento.CREDITO);
			venda.addVendasPagamento(new VendaPagamento(venda, pagamento));
		}
		return venda;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public VendaDto fill(Venda model) {
		super.fill(model);
		id = model.getId();
		data = model.getData();
		obs = model.getObs();
		valor = model.getValor();
		valorComissao = model.getValorComissao();
		vendedor = new FuncionarioDto(model.getFuncionario());
		aquisicao = new AquisicaoDto(model.getAquisicao());
		cliente = new ClienteDto(model.getCliente());
		pagamentos = new ArrayList<PagamentoDto>();
		for (VendaPagamento vendaPagamento : model.getVendasPagamentos()) {
			pagamentos.add(new PagamentoDto(vendaPagamento.getPagamento()));
		}
		return this;
	}

}