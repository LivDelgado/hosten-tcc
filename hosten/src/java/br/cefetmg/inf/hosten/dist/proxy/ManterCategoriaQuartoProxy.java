package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.impl.ManterCategoriaQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;


public class ManterCategoriaQuartoProxy implements IManterCategoriaQuarto {
    private final IManterCategoriaQuarto manterCategoriaQuarto;

    public ManterCategoriaQuartoProxy() {
        this.manterCategoriaQuarto = new ManterCategoriaQuarto();
    }

    @Override
    public boolean inserir(CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria) throws NegocioException, SQLException {
        return manterCategoriaQuarto.inserir(categoriaQuarto, itensCategoria);
    }

    @Override
    public List<CategoriaQuarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        return manterCategoriaQuarto.listar(dadoBusca, coluna);
    }

    @Override
    public List<CategoriaQuarto> listarTodos() throws NegocioException, SQLException {
        return manterCategoriaQuarto.listarTodos();
    }

    @Override
    public List<ItemConforto> listarItensRelacionados(String codCategoria) throws NegocioException, SQLException {
        return manterCategoriaQuarto.listarItensRelacionados(codCategoria);
    }

    @Override
    public boolean alterar(String codRegistro, CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria) throws NegocioException, SQLException {
        return manterCategoriaQuarto.alterar(codRegistro, categoriaQuarto, itensCategoria);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        return manterCategoriaQuarto.excluir(codRegistro);
    }

}
