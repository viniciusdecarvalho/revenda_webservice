package br.edu.ftlf.ads.revenda.dao;

import java.util.List;

import br.edu.ftlf.ads.revenda.model.Veiculo;

public interface VeiculosDao extends CrudDao<Veiculo> {

	List<Veiculo> listStock();

}
