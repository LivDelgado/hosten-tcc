package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoEstadoDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.rel.QuartoEstadoDao;
import java.sql.SQLException;
import java.util.List;

public class QuartoEstadoDaoAdapter implements IQuartoEstadoDao {
    
    private final IQuartoEstadoDao dao;
    private static IQuartoEstadoDao instancia;

    public QuartoEstadoDaoAdapter() {
        dao = QuartoEstadoDao.getInstance();
    }

    public static synchronized IQuartoEstadoDao getInstance() {
        if (instancia == null) {
            instancia = new QuartoEstadoDaoAdapter();
        }
        return instancia;
    }

    @Override
    public List<QuartoEstado> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }
}
