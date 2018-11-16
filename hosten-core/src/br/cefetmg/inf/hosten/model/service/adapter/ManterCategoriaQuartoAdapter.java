package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.impl.ManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.remote.IManterCategoriaQuartoRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ManterCategoriaQuartoAdapter implements IManterCategoriaQuartoRemote {
    private final IManterCategoriaQuarto manterCategoriaQuarto;

    public ManterCategoriaQuartoAdapter() {
        this.manterCategoriaQuarto = new ManterCategoriaQuarto();
    }

    @Override
    public boolean inserir(CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria) throws NegocioException, SQLException, RemoteException {
        return manterCategoriaQuarto.inserir(categoriaQuarto, itensCategoria);
    }

    @Override
    public List<CategoriaQuarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException, RemoteException {
        return manterCategoriaQuarto.listar(dadoBusca, coluna);
    }

    @Override
    public List<CategoriaQuarto> listarTodos() throws NegocioException, SQLException, RemoteException {
        return manterCategoriaQuarto.listarTodos();
    }

    @Override
    public List<ItemConforto> listarItensRelacionados(String codCategoria) throws NegocioException, SQLException, RemoteException {
        return manterCategoriaQuarto.listarItensRelacionados(codCategoria);
    }

    @Override
    public boolean alterar(String codRegistro, CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria) throws NegocioException, SQLException, RemoteException {
        return manterCategoriaQuarto.alterar(codRegistro, categoriaQuarto, itensCategoria);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException, RemoteException {
        return manterCategoriaQuarto.excluir(codRegistro);
    }
}
