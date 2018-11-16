package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.model.service.remote.IManterItemConfortoRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManterItemConfortoProxy implements IManterItemConforto {
    private final IManterItemConfortoRemote manterItemConforto;

    public ManterItemConfortoProxy() {
        this.manterItemConforto = (IManterItemConfortoRemote) ClienteRMI.getInstance().receberObjeto("ManterItemConforto");
    }

    @Override
    public boolean inserir(ItemConforto itemConforto) throws NegocioException, SQLException {
        try {
            return manterItemConforto.inserir(itemConforto);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterItemConfortoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<ItemConforto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        try {
            return manterItemConforto.listar(dadoBusca, coluna);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterItemConfortoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ItemConforto> listarTodos() throws NegocioException, SQLException {
        try {
            return manterItemConforto.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ManterItemConfortoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean alterar(String codRegistro, ItemConforto itemConforto) throws NegocioException, SQLException {
        try {
            return manterItemConforto.alterar(codRegistro, itemConforto);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterItemConfortoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        try {
            return manterItemConforto.excluir(codRegistro);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterItemConfortoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
