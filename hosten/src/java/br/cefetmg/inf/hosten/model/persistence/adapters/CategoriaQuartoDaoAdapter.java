package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.persistence.jdbc.CategoriaQuartoDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICategoriaQuartoDao;

public final class CategoriaQuartoDaoAdapter implements ICategoriaQuartoDao{

    private static ICategoriaQuartoDao instancia;

    public static synchronized ICategoriaQuartoDao getInstance() {
        if (instancia == null) {
            instancia = CategoriaQuartoDao.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaCategoriaQuarto(CategoriaQuarto categoriaQuarto)
            throws SQLException {
        return instancia.adicionaCategoriaQuarto(categoriaQuarto);
    }

    @Override
    public List<CategoriaQuarto> buscaCategoriaQuarto(
            Object dadoBusca, 
            String coluna) throws SQLException {
        return instancia.buscaCategoriaQuarto(dadoBusca, coluna);
    }

    @Override
    public List<CategoriaQuarto> buscaTodosCategoriaQuartos() 
            throws SQLException {
        return instancia.buscaTodosCategoriaQuartos();
    }

    @Override
    public boolean atualizaCategoriaQuarto(
            Object pK, 
            CategoriaQuarto categoriaQuartoAtualizado) throws SQLException {
        return instancia.atualizaCategoriaQuarto(pK, categoriaQuartoAtualizado);
    }

    @Override
    public boolean deletaCategoriaQuarto(Object pK) throws SQLException {
        return instancia.deletaCategoriaQuarto(pK);
    }
}
