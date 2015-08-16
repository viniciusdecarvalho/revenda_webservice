package br.edu.ftlf.ads.revenda.dto;

public interface DtoMapping {

	<T> DtoMapping register(Class<T> modelClass, Class<? extends Dto<T>> dtoClass);

	<T> DtoMapping remove(Class<T> modelClass);
	
	DtoMapping clear();
	
	Class<? extends Dto<?>> get(Class<?> type);

	boolean contains(Class<?> type);

	<T, E extends Dto<T>> E instanceFor(T model);

}