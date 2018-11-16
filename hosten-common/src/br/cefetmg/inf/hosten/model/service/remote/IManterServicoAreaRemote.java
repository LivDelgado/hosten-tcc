package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.service.*;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IManterServicoAreaRemote extends Remote {
    
    public boolean inserir(ServicoArea servicoArea) 
            throws NegocioException, SQLException, RemoteException;
    
    public List<ServicoArea> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException, RemoteException;
    
    public List<ServicoArea> listarTodos()
            throws NegocioException, SQLException, RemoteException;
    
    public boolean alterar(String codRegistro, ServicoArea servicoArea) 
            throws NegocioException, SQLException, RemoteException;
    
    public boolean excluir(String codRegistro) 
            throws NegocioException, SQLException, RemoteException;
}
