package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.model.service.impl.ManterQuarto;
import br.cefetmg.inf.hosten.model.service.remote.IManterQuartoRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;


public class ManterQuartoAdapter implements IManterQuartoRemote {
    private final IManterQuarto manterQuarto;

    public ManterQuartoAdapter() {
        this.manterQuarto = new ManterQuarto();
    }
    
    @Override
    public boolean inserir(Quarto quarto) throws NegocioException, SQLException, RemoteException {
        return manterQuarto.inserir(quarto);
    }

    @Override
    public List<Quarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException, RemoteException {
        return manterQuarto.listar(dadoBusca, coluna);
    }

    @Override
    public List<Quarto> listarTodos() throws NegocioException, SQLException, RemoteException {
        return manterQuarto.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, Quarto quarto) throws NegocioException, SQLException, RemoteException {
        return manterQuarto.alterar(codRegistro, quarto);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException, RemoteException {
        return manterQuarto.excluir(codRegistro);
    }

    @Override
    public int buscaUltimoRegistroRelacionadoAoQuarto(int nroQuarto) throws NegocioException, SQLException, RemoteException {
        return manterQuarto.buscaUltimoRegistroRelacionadoAoQuarto(nroQuarto);
    }
}
