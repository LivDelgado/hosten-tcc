package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.model.service.impl.ManterCargo;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterCargoProxy implements IManterCargo {
    private final IManterCargo manterCargo;

    public ManterCargoProxy() {
        this.manterCargo = new ManterCargo();
    }

    @Override
    public boolean inserir(Cargo cargo, List<String> listaProgramas) throws NegocioException, SQLException {
        return manterCargo.inserir(cargo, listaProgramas);
    }

    @Override
    public List<Cargo> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        return manterCargo.listar(dadoBusca, coluna);
    }

    @Override
    public List<Cargo> listarTodos() throws NegocioException, SQLException {
        return manterCargo.listarTodos();
    }

    @Override
    public List<Programa> listarProgramasRelacionados(String codCargo) throws NegocioException, SQLException {
        return manterCargo.listarProgramasRelacionados(codCargo);
    }

    @Override
    public List<Programa> listarTodosProgramas() throws NegocioException, SQLException {
        return manterCargo.listarTodosProgramas();
    }

    @Override
    public boolean alterar(String codRegistro, Cargo cargo, List<String> listaProgramas) throws NegocioException, SQLException {
        return manterCargo.alterar(codRegistro, cargo, listaProgramas);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        return manterCargo.excluir(codRegistro);
    }
}
