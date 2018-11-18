package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IManterCargoRemote extends Remote {

    public boolean inserir(Cargo cargo, List<String> listaProgramas) 
            throws NegocioException, SQLException, RemoteException;

    public List<Cargo> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException, RemoteException;
    
    public List<Cargo> listarTodos()
            throws NegocioException, SQLException, RemoteException;
    
    public List<Programa> listarProgramasRelacionados(String codCargo) 
            throws NegocioException, SQLException, RemoteException;
    
    public List<Programa> listarTodosProgramas()
            throws NegocioException, SQLException, RemoteException;

    public boolean alterar(String codRegistro, Cargo cargo, List<String> listaProgramas) 
            throws NegocioException, SQLException, RemoteException;

    public boolean excluir(String codRegistro) 
            throws NegocioException, SQLException, RemoteException;
}
