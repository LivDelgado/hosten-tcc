package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.views.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.views.IQuartoEstadoDAO;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.views.QuartoEstado;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;

public class QuartoEstadoDAO implements IQuartoEstadoDAO {

    private static QuartoEstadoDAO instancia;

    private final EntityManager em;

    private QuartoEstadoDAO() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized QuartoEstadoDAO getInstance() {
        if (instancia == null) {
            instancia = new QuartoEstadoDAO();
        }
        return instancia;
    }

    @Override
    public List<QuartoEstado> buscaTodos() throws SQLException {
        List<QuartoEstado> quartoEstados = em
                .createNamedQuery("QuartoEstado.findAll", QuartoEstado.class)
                .getResultList();
        
        return quartoEstados;
    }
}
