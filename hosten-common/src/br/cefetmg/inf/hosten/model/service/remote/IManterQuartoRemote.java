package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IManterQuartoRemote extends Remote {

    public boolean inserir(Quarto quarto) throws NegocioException, SQLException, RemoteException;

    public List<Quarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException, RemoteException;

    public List<Quarto> listarTodos() throws NegocioException, SQLException, RemoteException;

    public boolean alterar(short codRegistro, Quarto quarto) throws NegocioException, SQLException, RemoteException;

    public boolean excluir(short codRegistro) throws NegocioException, SQLException, RemoteException;

    public int buscaUltimoRegistroRelacionadoAoQuarto(short nroQuarto) throws NegocioException, SQLException, RemoteException;
}
