package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.IProgramaDAO;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProgramaDAO implements IProgramaDAO {

    private static final String NAMED_QUERY_BASE = "Programa.findBy";

    private static ProgramaDAO instancia;

    private final EntityManager em;

    private ProgramaDAO() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized ProgramaDAO getInstance() {
        if (instancia == null) {
            instancia = new ProgramaDAO();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Programa programa) throws SQLException {
        em.getTransaction().begin();
        em.persist(programa);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public Programa buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        Programa programa = em.find(Programa.class, id);
        em.getTransaction().commit();

        return programa;
    }

    @Override
    public List<Programa> buscaPorColuna(Object dadoBusca, String coluna)
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

        TypedQuery<Programa> tq = em
                .createNamedQuery(qryBusca, Programa.class)
                .setParameter(parametro, dadoBusca);
        List<Programa> programas = tq.getResultList();

        em.getTransaction().commit();

        return programas;
    }

    @Override
    public List<Programa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<Programa> tq = em.createNamedQuery("Programa.findAll", Programa.class);
        List<Programa> programas = tq.getResultList();

        em.getTransaction().commit();

        return programas;
    }

    @Override
    public boolean atualiza(String id, Programa programaNov)
            throws SQLException {
        em.getTransaction().begin();

        Programa programaAnt = em.find(Programa.class, id);
        programaAnt.setDesPrograma(programaNov.getDesPrograma());

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(Programa programa) throws SQLException {
        em.getTransaction().begin();
        em.remove(programa);
        em.getTransaction().commit();

        return true;
    }
}
