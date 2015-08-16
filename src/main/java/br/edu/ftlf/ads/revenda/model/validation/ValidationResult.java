package br.edu.ftlf.ads.revenda.model.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationResult {

	@XmlElementWrapper(name="errors")
	@XmlElement(name="error")
	private List<Error> errors;

	public ValidationResult(Set<ConstraintViolation<Object>> validate) {
		this.errors = validate.stream().map(this::getError).collect(Collectors.toList());
	}

	public ValidationResult(Error... errors) {
		this.errors = new ArrayList<>(Arrays.asList(errors));
	}

	private Error getError(ConstraintViolation<Object> violation) {
		String name = violation.getPropertyPath().iterator().next().getName();
		String message = violation.getMessage();
		return new Error(name, message);
	}

	public boolean hasErros() {
		return !errors.isEmpty();
	}
	
	public List<Error> getErrors() {
		return Collections.unmodifiableList(errors);
	}
	
	public ValidationResult addError(Error error) {
		if (error != null)
			errors.add(error);
		return this;
	}
	
}
