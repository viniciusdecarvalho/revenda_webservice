package br.edu.ftlf.ads.revenda.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.edu.ftlf.ads.revenda.model.Veiculo;

@XmlAccessorType(XmlAccessType.FIELD)
public class VeiculoDto extends Dto<Veiculo> {

	private Integer id;
	
	@XmlElement(required=true)
	private String ano;
	
	@XmlElement(required=true)
	private String chassi;
	
	@XmlElement(required=true)
	private String especie;
	
	@XmlElement(required=true)
	private String marca;
	
	@XmlElement(required=true)
	private String modelo;
	
	@XmlElement(required=true)
	private String placa;
	
	@XmlElement(required=true)
	private String renavan;
	
	public VeiculoDto() {}
	
	public VeiculoDto(Veiculo veiculo) {
		super();
		fill(veiculo);
	}

	@Override
	protected Veiculo parse() {
		Veiculo veiculo = new Veiculo();
		veiculo.setId(id);
		veiculo.setAno(ano);
		veiculo.setChassi(chassi);
		veiculo.setEspecie(especie);
		veiculo.setMarca(marca);
		veiculo.setModelo(modelo);
		veiculo.setPlaca(placa);
		veiculo.setRenavan(renavan);
		return veiculo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VeiculoDto fill(Veiculo model) {
		super.fill(model);
		id = model.getId();
		ano = model.getAno();
		chassi = model.getChassi();
		especie = model.getEspecie();
		marca = model.getMarca();
		modelo = model.getModelo();
		placa = model.getPlaca();
		renavan = model.getRenavan();
		return this;
	}
}