package br.edu.ftlf.ads.revenda.dto;


public abstract class Dto<T> {

	private transient T model;
	private transient boolean modified;

	Dto() {
		modified = true;		
	}
	
	public T getModel() {
		if (!modified && model != null) {
			return model;
		}
		model = parse();
		modified = false;
		return model;
	}

	@SuppressWarnings("unchecked")
	public <E extends Dto<T>> E fill(T model) {
		this.model = model;
		modified = false;
		return (E) this;
	}

	protected abstract T parse();                                                                                                                 
	
	@Override
	public String toString() {
		return "DTO: [ " + getModel().toString() + " ]";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Dto<T> other = (Dto<T>) obj;
		return getModel().equals(other.getModel());
	}
	
}