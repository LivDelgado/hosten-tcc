package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.service.*;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IManterServicoRemote extends Remote {

    public boolean inserir(Servico servico) 
            throws NegocioException, SQLException, RemoteException;

    public List<Servico> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException, RemoteException;

    public List<Servico> listarTodos()
            throws NegocioException, SQLException, RemoteException;

    public boolean alterar(String codRegistro, Servico servico)
            throws NegocioException, SQLException, RemoteException;

    public boolean excluir(String codRegistro) 
            throws NegocioException, SQLException, RemoteException;
}
