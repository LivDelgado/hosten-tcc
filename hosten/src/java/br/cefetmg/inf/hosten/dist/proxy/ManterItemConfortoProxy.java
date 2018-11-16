package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.model.service.impl.ManterItemConforto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterItemConfortoProxy implements IManterItemConforto {
    private final IManterItemConforto manterItemConforto;

    public ManterItemConfortoProxy() {
        this.manterItemConforto = new ManterItemConforto();
    }

    @Override
    public boolean inserir(ItemConforto itemConforto) throws NegocioException, SQLException {
        return manterItemConforto.inserir(itemConforto);
    }

    @Override
    public List<ItemConforto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        return manterItemConforto.listar(dadoBusca, coluna);
    }

    @Override
    public List<ItemConforto> listarTodos() throws NegocioException, SQLException {
        return manterItemConforto.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, ItemConforto itemConforto) throws NegocioException, SQLException {
        return manterItemConforto.alterar(codRegistro, itemConforto);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        return manterItemConforto.excluir(codRegistro);
    }
}
