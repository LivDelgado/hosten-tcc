package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.impl;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoEstadoDao;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;

public class QuartoEstadoDaoJpa implements IQuartoEstadoDao {

    private static QuartoEstadoDaoJpa instancia;

    private final EntityManager em;

    private QuartoEstadoDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized QuartoEstadoDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new QuartoEstadoDaoJpa();
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
