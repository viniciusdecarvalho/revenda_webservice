package br.edu.ftlf.ads.revenda.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.edu.ftlf.ads.revenda.model.Enums.TipoPagamento;
import br.edu.ftlf.ads.revenda.model.Pagamento;

@XmlAccessorType(XmlAccessType.FIELD)
public class PagamentoDto extends Dto<Pagamento> {

	private Integer id; 
	@XmlElement(required=true)
	private Date data;
	
	@XmlElement(required=true)
	private String descricao;
	
	@XmlElement(required=true)
	private String tipo;
	
	@XmlElement(required=true)
	private BigDecimal valor;
	
	@XmlElement(required=true)
	private FormaPagamentoDto formaspagamento;
	
	public PagamentoDto() {}
	
	public PagamentoDto(Pagamento pagamento) {
		super();
		fill(pagamento);
	}

	@Override
	protected Pagamento parse() {
		Pagamento pagamento = new Pagamento();
		pagamento.setId(id);
		pagamento.setData(data);
		pagamento.setDescricao(descricao);
		pagamento.setTipo(TipoPagamento.valueOf(tipo.toUpperCase()));
		pagamento.setValor(valor);
		pagamento.setFormaspagamento(formaspagamento.getModel());
		return pagamento;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PagamentoDto fill(Pagamento model) {
		super.fill(model);
		id = model.getId();
		data = model.getData();
		descricao = model.getDescricao();
		tipo = model.getTipo().name();
		valor = model.getValor();
		formaspagamento = new FormaPagamentoDto(model.getFormaspagamento());
		return this;
	}
	
}