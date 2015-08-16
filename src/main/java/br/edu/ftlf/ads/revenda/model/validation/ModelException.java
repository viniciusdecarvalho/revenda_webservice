package br.edu.ftlf.ads.revenda.model.validation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.ws.WebFault;

@WebFault
@XmlAccessorType(XmlAccessType.FIELD)
public class ModelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@XmlElementWrapper(name="errors")
	@XmlElement(name="error")
	private List<Error> errors;
	
	public ModelException(ValidationResult validation) {
		super(validation.getErrors().toString());
		this.errors = validation.getErrors();
	}
	
	public List<Error> getErros() {
		return errors;
	}
	
}
