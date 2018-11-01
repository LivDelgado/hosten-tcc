package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.CategoriaItemConforto;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.persistence.jdbc.rel.CategoriaItemConfortoDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.ICategoriaItemConfortoDao;

public class CategoriaItemConfortoDaoAdapter implements ICategoriaItemConfortoDao {

    private static ICategoriaItemConfortoDao instancia;

    public static synchronized ICategoriaItemConfortoDao getInstance() {
        if (instancia == null) {
            instancia = CategoriaItemConfortoDao.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(CategoriaItemConforto categoriaItemConforto)
            throws SQLException {
        return instancia.adiciona(categoriaItemConforto);
    }

    @Override
    public List<CategoriaItemConforto> busca(String cod, String coluna) 
            throws SQLException {
        return instancia.busca(cod, coluna);
    }
    
    @Override
    public List<ItemConforto> buscaItensConfortoRelacionados(String codCategoria) 
            throws SQLException {
        return instancia.buscaItensConfortoRelacionados(codCategoria);
    }

    @Override
    public boolean deletaPorColuna(String dadoBusca, String coluna) 
            throws SQLException {
        return instancia.deletaPorColuna(dadoBusca, coluna);
    }
}
