package br.edu.ftlf.ads.revenda.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.edu.ftlf.ads.revenda.model.validation.BeanValidator;
import br.edu.ftlf.ads.revenda.model.validation.ValidationResult;

@MappedSuperclass
public abstract class Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static BeanValidator validator = new BeanValidator();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (getId() != null && other.getId() != null) {
			return getId().equals(other.getId());
		}
		return false;
	}
	
	public boolean isNew() {
		return id == null;
	}
	
	public ValidationResult validation() {
		return validator.validation(this);
	}
	
	public boolean isValid() {
		return validator.isValid(this);
	}
	
}
