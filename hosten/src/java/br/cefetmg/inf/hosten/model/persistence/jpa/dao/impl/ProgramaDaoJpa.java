package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CargoJpa;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.ProgramaJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.IProgramaDaoJpa;

public class ProgramaDaoJpa implements IProgramaDaoJpa {

    private static final String NAMED_QUERY_BASE = "Programa.findBy";

    private static ProgramaDaoJpa instancia;

    private final EntityManager em;

    private ProgramaDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized ProgramaDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new ProgramaDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(ProgramaJpa programa) throws SQLException {
        em.getTransaction().begin();
        em.persist(programa);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public ProgramaJpa buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        ProgramaJpa programa = em.find(ProgramaJpa.class, id);
        em.getTransaction().commit();

        return programa;
    }

    @Override
    public List<ProgramaJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "codprograma":
                qryBusca += "CodPrograma";
                parametro = "codPrograma";
                break;
            case "desprograma":
                qryBusca += "DesPrograma";
                parametro = "desPrograma";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<ProgramaJpa> tq = em
                .createNamedQuery(qryBusca, ProgramaJpa.class)
                .setParameter(parametro, dadoBusca);
        List<ProgramaJpa> programas = tq.getResultList();

        em.getTransaction().commit();

        return programas;
    }

    @Override
    public List<ProgramaJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<ProgramaJpa> tq = em.createNamedQuery("Programa.findAll", ProgramaJpa.class);
        List<ProgramaJpa> programas = tq.getResultList();

        em.getTransaction().commit();

        return programas;
    }

    @Override
    public boolean atualiza(String id, ProgramaJpa programaNov)
            throws SQLException {
        em.getTransaction().begin();

        ProgramaJpa programaAnt = em.find(ProgramaJpa.class, id);
        programaAnt.setDesPrograma(programaNov.getDesPrograma());

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(ProgramaJpa programa) throws SQLException {
        em.getTransaction().begin();
        em.remove(programa);
        em.getTransaction().commit();

        return true;
    }
}
