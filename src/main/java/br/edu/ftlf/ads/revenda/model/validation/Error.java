package br.edu.ftlf.ads.revenda.model.validation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public final class Error {
	
	private final String property;
	private final String message;

	public Error(String property, String message) {
		this.property = property;
		this.message = message;
	}

	public String getProperty() {
		return property;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return String.format("propertyName: %s - errorMessage: %s", property, message);
	}
}