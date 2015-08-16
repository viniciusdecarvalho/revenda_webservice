package br.edu.ftlf.ads.revenda.dto;

public class DtoBuilder {

	public DtoMapping mapping;
	private Object model;
	
	public DtoBuilder(DtoMapping mapping) {
		this.mapping = mapping;
	}
	
	public DtoBuilder setModel(Object model) {
		this.model = model;
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Dto<?>> T build() {
		if (mapping == null) {
			throw new NullPointerException("mapping of dto null");
		}
		
		if (model == null) {
			throw new NullPointerException("model null");
		}
		
		return (T) mapping.instanceFor(model);
	}
	
}
