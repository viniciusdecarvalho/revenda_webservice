package br.edu.ftlf.ads.revenda.model.validation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BeanValidator {

	private static Validator validator;
	
	public ValidationResult validation(Object object) {
		return new ValidationResult(getValidator().validate(object));
	}
	
	private static Validator getValidator() {
		if (validator == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
		}
		return validator;
	}
	
	public boolean isValid(Object object) {
		return !validation(object).hasErros();
	}
	
}
