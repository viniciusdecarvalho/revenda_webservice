package br.edu.ftlf.ads.revenda.dao;

import java.util.List;

public interface CrudDao<T> {

	List<T> listAll();
	
	List<T> listAll(int start, int limite);
	
	T get(Integer id);
	
	void save(T model);
	
	void delete(Integer id);
	
}
