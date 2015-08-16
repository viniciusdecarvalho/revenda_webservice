package br.edu.ftlf.ads.revenda.dto;

import java.util.HashMap;
import java.util.Map;

public class DefaultDtoMapping implements DtoMapping {

	private Map<Class<?>, Class<? extends Dto<?>>> mappings = 
			new HashMap<Class<?>, Class<? extends Dto<?>>>();
	
	DefaultDtoMapping() {}
	
	@Override
	public <T> DtoMapping register(Class<T> modelClass, Class<? extends Dto<T>> dtoClass) {
		mappings.put(modelClass, dtoClass);
		return this;
	}
	
	@Override
	public <T> DtoMapping remove(Class<T> modelClass) {
		mappings.remove(modelClass);
		return this;
	}
	
	@Override
	public DtoMapping clear() {
		mappings.clear();
		return this;
	}
	
	@Override
	public Class<? extends Dto<?>> get(Class<?> type) {
		if (contains(type))
			return mappings.get(type);
		return null;
	}
	
	@Override
	public boolean contains(Class<?> type) {
		if (type == null) 
			return false;
		return mappings.containsKey(type);
	}
	
	@SuppressWarnings("unchecked")
	private <T, E extends Dto<T>> E instanceFor(Class<T> modelClass) {
		try {
			return (E) get(modelClass).asSubclass(Dto.class).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException(String.format("%s must has default contructor without parameters", 
													modelClass.getSimpleName()));
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T, E extends Dto<T>> E instanceFor(T model) {
		Class<T> modelClass = (Class<T>) model.getClass();

		if (!contains(modelClass))
			return null;
		
		E dto = instanceFor(modelClass);
		dto.fill(model);
		return dto;
	}
	
}   
