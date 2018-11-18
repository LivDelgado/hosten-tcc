package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.service.impl.*;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.model.service.remote.IManterCargoRemote;
import java.rmi.RemoteException;

public class ManterCargoAdapter implements IManterCargoRemote {
    private final IManterCargo manterCargo;

    public ManterCargoAdapter() {
        this.manterCargo = new ManterCargo();
    }

    @Override
    public boolean inserir(Cargo cargo, List<String> listaProgramas) throws NegocioException, SQLException, RemoteException {
        return manterCargo.inserir(cargo, listaProgramas);
    }

    @Override
    public List<Cargo> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException, RemoteException {
        return manterCargo.listar(dadoBusca, coluna);
    }

    @Override
    public List<Cargo> listarTodos() throws NegocioException, SQLException, RemoteException {
        return manterCargo.listarTodos();
    }

    @Override
    public List<Programa> listarProgramasRelacionados(String codCargo) throws NegocioException, SQLException, RemoteException {
        return manterCargo.listarProgramasRelacionados(codCargo);
    }

    @Override
    public List<Programa> listarTodosProgramas() throws NegocioException, SQLException, RemoteException {
        return manterCargo.listarTodosProgramas();
    }

    @Override
    public boolean alterar(String codRegistro, Cargo cargo, List<String> listaProgramas) throws NegocioException, SQLException, RemoteException {
        return manterCargo.alterar(codRegistro, cargo, listaProgramas);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException, RemoteException {
        return manterCargo.excluir(codRegistro);
    }
}
