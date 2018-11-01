package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.views.QuartoEstadoJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.IQuartoEstadoDaoJpa;

public class QuartoEstadoDaoJpa implements IQuartoEstadoDaoJpa {

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
    public List<QuartoEstadoJpa> buscaTodos() throws SQLException {
        List<QuartoEstadoJpa> quartoEstados = em
                .createNamedQuery("QuartoEstado.findAll", QuartoEstadoJpa.class)
                .getResultList();
        
        return quartoEstados;
    }
}
