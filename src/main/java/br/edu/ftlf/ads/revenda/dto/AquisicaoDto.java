package br.edu.ftlf.ads.revenda.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.edu.ftlf.ads.revenda.model.Aquisicao;
import br.edu.ftlf.ads.revenda.model.Aquisicao.Combustivel;
import br.edu.ftlf.ads.revenda.model.AquisicaoPagamento;
import br.edu.ftlf.ads.revenda.model.Enums.SituacaoAquisicao;
import br.edu.ftlf.ads.revenda.model.Enums.TipoPagamento;
import br.edu.ftlf.ads.revenda.model.Pagamento;

@XmlAccessorType(XmlAccessType.FIELD)
public class AquisicaoDto extends Dto<Aquisicao> {

	private Integer id;
	@XmlElement(required = true)
	private String cidade;
	@XmlElement(required = true)
	private String combustivel;
	@XmlElement(required = true)
	private String cor;
	@XmlElement(required = true)
	private Date data;
	@XmlElement(required = true)
	private int km;
	private String obs;
	@XmlElement(required = true)
	private String uf;
	@XmlElement(required = true)
	private BigDecimal valor;
	private BigDecimal valorComissao;
	@XmlElement(required = true)
	private BigDecimal valorPedido;
	@XmlElement(required = true)
	private ClienteDto cliente;
	@XmlElement(required = true)
	private FuncionarioDto funcionario;
	@XmlElement(required = true)
	private VeiculoDto veiculo;
	private SituacaoAquisicao situacao;

	@XmlElement(required=true)
	private List<PagamentoDto> pagamentos = new ArrayList<PagamentoDto>();

	public AquisicaoDto() {}
	
	public AquisicaoDto(Aquisicao aquisicao) {
		super();
		fill(aquisicao);
	}

	@Override
	protected Aquisicao parse() {
		Aquisicao aquisicao = new Aquisicao();
		aquisicao.setId(id);
		aquisicao.setCidade(cidade);
		aquisicao.setCombustivel(Combustivel.valueOf(combustivel.toUpperCase()));
		aquisicao.setCor(cor);
		aquisicao.setData(data);
		aquisicao.setKm(km);
		aquisicao.setObs(obs);
		aquisicao.setUf(uf);
		aquisicao.setValor(valor);
		aquisicao.setValorComissao(valorComissao);
		aquisicao.setValorPedido(valorPedido);
		aquisicao.setCliente(cliente.getModel());
		aquisicao.setFuncionario(funcionario.getModel());
		aquisicao.setVeiculo(veiculo.getModel());
		aquisicao.setSituacao(situacao);
		if (pagamentos != null) {
			for (PagamentoDto pagamentoDto : pagamentos) {
				Pagamento pagamento = pagamentoDto.getModel();
				pagamento.setData(aquisicao.getData());
				pagamento.setTipo(TipoPagamento.DEBITO);
				aquisicao.addAquisicoesPagamento(new AquisicaoPagamento(aquisicao, pagamento));
			}
		}
		return aquisicao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AquisicaoDto fill(Aquisicao model) {
		super.fill(model);
		id = model.getId();
		cidade = model.getCidade();
		combustivel = model.getCombustivel().name();
		cor = model.getCor();
		data = model.getData();
		km = model.getKm();
		obs = model.getObs();
		uf = model.getUf();
		valor = model.getValor();
		valorComissao = model.getValorComissao();
		valorPedido = model.getValorPedido();
		cliente = new ClienteDto(model.getCliente());
		funcionario = new FuncionarioDto(model.getFuncionario());
		veiculo = new VeiculoDto(model.getVeiculo());
		situacao = model.getSituacao();
		model.getAquisicoesPagamentos().stream()
									   .map(AquisicaoPagamento::getPagamento)
									   .map(p -> new PagamentoDto(p))
									   .forEach(pagamentos::add);
		return this;
	}

}