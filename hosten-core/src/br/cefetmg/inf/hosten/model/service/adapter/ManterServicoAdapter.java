package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.service.impl.ManterServico;
import br.cefetmg.inf.hosten.model.service.remote.IManterServicoRemote;
import java.rmi.RemoteException;

public class ManterServicoAdapter implements IManterServicoRemote {
    private final IManterServico manterServico;

    public ManterServicoAdapter() {
        this.manterServico = new ManterServico();
    }

    @Override
    public boolean inserir(Servico servico) throws NegocioException, SQLException, RemoteException {
        return manterServico.inserir(servico);
    }

    @Override
    public List<Servico> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException, RemoteException {
        return manterServico.listar(dadoBusca, coluna);
    }

    @Override
    public List<Servico> listarTodos() throws NegocioException, SQLException, RemoteException {
        return manterServico.listarTodos();
    }

    @Override
    public boolean alterar(short codRegistro, Servico servico) throws NegocioException, SQLException, RemoteException {
        return manterServico.alterar(codRegistro, servico);
    }

    @Override
    public boolean excluir(short codRegistro) throws NegocioException, SQLException, RemoteException {
        return manterServico.excluir(codRegistro);
    }
}
