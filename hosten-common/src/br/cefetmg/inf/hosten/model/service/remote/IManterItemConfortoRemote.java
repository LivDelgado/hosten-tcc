package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.service.*;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IManterItemConfortoRemote extends Remote {

    public boolean inserir(ItemConforto itemConforto) 
            throws NegocioException, SQLException, RemoteException;

    public List<ItemConforto> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException, RemoteException;
    
    public List<ItemConforto> listarTodos()
            throws NegocioException, SQLException, RemoteException;

    public boolean alterar(String codRegistro, ItemConforto itemConforto) 
            throws NegocioException, SQLException, RemoteException;

    public boolean excluir(String codRegistro) 
            throws NegocioException, SQLException, RemoteException;
}
