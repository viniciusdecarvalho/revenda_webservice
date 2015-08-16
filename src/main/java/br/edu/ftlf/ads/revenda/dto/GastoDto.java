package br.edu.ftlf.ads.revenda.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.edu.ftlf.ads.revenda.model.Aquisicao;
import br.edu.ftlf.ads.revenda.model.Enums.SituacaoGasto;
import br.edu.ftlf.ads.revenda.model.Fornecedor;
import br.edu.ftlf.ads.revenda.model.Gasto;

@XmlAccessorType(XmlAccessType.FIELD)
public class GastoDto extends Dto<Gasto> {

	private Integer id;
	private String situacao;
	
	@XmlElement(required=true)
	private Integer aquisicaoId;
	
	@XmlElement(required=true)
	private Integer fornecedorId;
	
	@XmlElement(required=true)
	private PagamentoDto pagamento;
	
	public GastoDto() {}
	
	public GastoDto(Gasto gasto) {
		super();
		fill(gasto);
	}

	@Override
	protected Gasto parse() {
		Gasto gasto = new Gasto();
		gasto.setId(id);
		gasto.setSituacao(SituacaoGasto.valueOf(situacao));
		gasto.setAquisicao(new Aquisicao(aquisicaoId));
		gasto.setFornecedor(new Fornecedor(fornecedorId));
		gasto.setPagamento(pagamento.getModel());
		return gasto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public GastoDto fill(Gasto model) {
		super.fill(model);
		id = model.getId();
		situacao = model.getSituacao().name();
		aquisicaoId = model.getAquisicao().getId();
		fornecedorId = model.getFornecedor().getId();
		pagamento = new PagamentoDto(model.getPagamento());
		return this;
	}

}