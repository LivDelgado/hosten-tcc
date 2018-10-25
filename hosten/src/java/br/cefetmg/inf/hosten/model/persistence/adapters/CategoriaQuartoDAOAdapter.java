package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.persistence.interfaces.ICategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.persistence.jdbc.CategoriaQuartoDAO;
import java.sql.SQLException;
import java.util.List;

public final class CategoriaQuartoDAOAdapter implements ICategoriaQuartoDAO{

    private static ICategoriaQuartoDAO instancia;

    public static synchronized ICategoriaQuartoDAO getInstance() {
        if (instancia == null) {
            instancia = CategoriaQuartoDAO.getInstance();
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
