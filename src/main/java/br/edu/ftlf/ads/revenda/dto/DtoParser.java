package br.edu.ftlf.ads.revenda.dto;

import br.edu.ftlf.ads.revenda.model.Aquisicao;
import br.edu.ftlf.ads.revenda.model.Cliente;
import br.edu.ftlf.ads.revenda.model.Fornecedor;
import br.edu.ftlf.ads.revenda.model.Funcionario;
import br.edu.ftlf.ads.revenda.model.Gasto;
import br.edu.ftlf.ads.revenda.model.Pagamento;
import br.edu.ftlf.ads.revenda.model.Veiculo;
import br.edu.ftlf.ads.revenda.model.Venda;

public class DtoParser {

	private static DtoMapping dtoMapping;

	static {
		dtoMapping = new DefaultDtoMapping()
				.register(Cliente.class, ClienteDto.class)
				.register(Fornecedor.class, FornecedorDto.class)
				.register(Funcionario.class, FuncionarioDto.class)
				.register(Veiculo.class, VeiculoDto.class)
				.register(Pagamento.class, PagamentoDto.class)
				.register(Gasto.class, GastoDto.class)
				.register(Aquisicao.class, AquisicaoDto.class)
				.register(Venda.class, VendaDto.class);
	}

	@SuppressWarnings("unchecked")
	public static <T, E extends Dto<T>> E parse(T model) {
		return (E) dtoMapping.instanceFor(model);
	}

}
