package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.model.service.impl.ManterItemConforto;
import br.cefetmg.inf.hosten.model.service.remote.IManterItemConfortoRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ManterItemConfortoAdapter implements IManterItemConfortoRemote {
    private final IManterItemConforto manterItemConforto;

    public ManterItemConfortoAdapter() {
        this.manterItemConforto = new ManterItemConforto();
    }

    @Override
    public boolean inserir(ItemConforto itemConforto) throws NegocioException, SQLException, RemoteException {
        return manterItemConforto.inserir(itemConforto);
    }

    @Override
    public List<ItemConforto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException, RemoteException {
        return manterItemConforto.listar(dadoBusca, coluna);
    }

    @Override
    public List<ItemConforto> listarTodos() throws NegocioException, SQLException, RemoteException {
        return manterItemConforto.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, ItemConforto itemConforto) throws NegocioException, SQLException, RemoteException {
        return manterItemConforto.alterar(codRegistro, itemConforto);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException, RemoteException {
        return manterItemConforto.excluir(codRegistro);
    }
    
}
