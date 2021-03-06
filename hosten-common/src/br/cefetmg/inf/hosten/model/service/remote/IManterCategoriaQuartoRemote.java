package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.service.*;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IManterCategoriaQuartoRemote extends Remote {

    public boolean inserir(CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria) 
            throws NegocioException, SQLException, RemoteException;

    public List<CategoriaQuarto> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException, RemoteException;
    
    public List<CategoriaQuarto> listarTodos()
            throws NegocioException, SQLException, RemoteException;
    
    public List<ItemConforto> listarItensRelacionados(String codCategoria)
            throws NegocioException, SQLException, RemoteException;

    public boolean alterar(String codRegistro, CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria) 
            throws NegocioException, SQLException, RemoteException;

    public boolean excluir(String codRegistro) 
            throws NegocioException, SQLException, RemoteException;
}
