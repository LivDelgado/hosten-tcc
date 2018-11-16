package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.service.*;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IManterUsuarioRemote extends Remote {
    
    public boolean inserir(Usuario usuario) throws NegocioException, 
            SQLException, RemoteException;
    
    public List<Usuario> listar(Object dadoBusca, String coluna) 
            throws NegocioException, SQLException, RemoteException;
    
    public List<Usuario> listarTodos() 
            throws NegocioException, SQLException, RemoteException;
    
    public boolean alterar(String codRegistro, Usuario usuario) 
            throws NegocioException, SQLException, RemoteException;
    
    public boolean excluir(String codRegistro) throws NegocioException, 
            SQLException, RemoteException;
    
    public Usuario usuarioLogin(String email, String senha) 
            throws NegocioException, SQLException, RemoteException;
}
