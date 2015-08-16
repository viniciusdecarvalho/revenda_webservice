package br.edu.ftlf.ads.revenda.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import br.edu.ftlf.ads.revenda.model.FormaPagamento;

@XmlAccessorType(XmlAccessType.FIELD)
public class FormaPagamentoDto extends Dto<FormaPagamento> {

	private Integer id;
	private String nome;
	private boolean credito;
	private boolean debito;
	private boolean ativo;
	
//	private BigDecimal total;	
	
	public FormaPagamentoDto() {}
	
	public FormaPagamentoDto(FormaPagamento formaPagamento) {
		super();
		fill(formaPagamento);
	}

	@Override
	protected FormaPagamento parse() {
		FormaPagamento forma = new FormaPagamento();
		forma.setId(id);
		forma.setNome(nome);
		forma.setCredito(credito);
		forma.setDebito(debito);
		forma.setAtivo(ativo);		
		return forma;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Dto<FormaPagamento>> E fill(FormaPagamento model) {
		super.fill(model);
		id = model.getId();
		nome = model.getNome();
		credito = model.isCredito();
		debito = model.isDebito();
		ativo = model.isAtivo();
//		total = model.getPagamentos().stream()
//									 .map(Pagamento::getValor)
//									 .reduce(new BigDecimal(0), (i, v) -> i.add(v));
		return (E) this;
	}

}
