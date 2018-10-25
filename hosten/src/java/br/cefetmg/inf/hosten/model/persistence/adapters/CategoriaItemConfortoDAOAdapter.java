package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.CategoriaItemConforto;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICategoriaItemConfortoDAO;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.persistence.jdbc.CategoriaItemConfortoDAO;

public class CategoriaItemConfortoDAOAdapter implements ICategoriaItemConfortoDAO {

    private static ICategoriaItemConfortoDAO instancia;

    public static synchronized ICategoriaItemConfortoDAO getInstance() {
        if (instancia == null) {
            instancia = CategoriaItemConfortoDAO.getInstance();
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
