package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.remote.IManterCategoriaQuartoRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ManterCategoriaQuartoProxy implements IManterCategoriaQuarto {
    private final IManterCategoriaQuartoRemote manterCategoriaQuarto;

    public ManterCategoriaQuartoProxy() {
        this.manterCategoriaQuarto = (IManterCategoriaQuartoRemote) ClienteRMI.getInstance().receberObjeto("ManterCategoriaQuarto");
    }

    @Override
    public boolean inserir(CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria) throws NegocioException, SQLException {
        try {
            return manterCategoriaQuarto.inserir(categoriaQuarto, itensCategoria);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCategoriaQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<CategoriaQuarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        try {
            return manterCategoriaQuarto.listar(dadoBusca, coluna);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCategoriaQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<CategoriaQuarto> listarTodos() throws NegocioException, SQLException {
        try {
            return manterCategoriaQuarto.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCategoriaQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ItemConforto> listarItensRelacionados(String codCategoria) throws NegocioException, SQLException {
        try {
            return manterCategoriaQuarto.listarItensRelacionados(codCategoria);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCategoriaQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean alterar(String codRegistro, CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria) throws NegocioException, SQLException {
        try {
            return manterCategoriaQuarto.alterar(codRegistro, categoriaQuarto, itensCategoria);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCategoriaQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        try {
            return manterCategoriaQuarto.excluir(codRegistro);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCategoriaQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
