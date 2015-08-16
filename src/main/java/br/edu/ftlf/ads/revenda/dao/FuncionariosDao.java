package br.edu.ftlf.ads.revenda.dao;

import br.edu.ftlf.ads.revenda.dto.FuncionarioDto;
import br.edu.ftlf.ads.revenda.model.Funcionario;
import br.edu.ftlf.ads.revenda.model.Usuario;

public interface FuncionariosDao extends CrudDao<Funcionario> {

	boolean hasLogin(Usuario usuario);

	FuncionarioDto login(Usuario usuario);

}
