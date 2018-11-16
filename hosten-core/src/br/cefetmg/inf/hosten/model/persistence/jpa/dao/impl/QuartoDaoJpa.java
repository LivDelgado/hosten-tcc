package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CategoriaQuartoJpa;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.QuartoJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.IQuartoDaoJpa;

public class QuartoDaoJpa implements IQuartoDaoJpa {

    private static final String NAMED_QUERY_BASE = "Quarto.findBy";

    private static QuartoDaoJpa instancia;

    private final EntityManager em;

    private QuartoDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized QuartoDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new QuartoDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoJpa quarto) throws SQLException {
        em.getTransaction().begin();
        em.persist(quarto);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public QuartoJpa buscaPorPk(Short id) throws SQLException {
        em.getTransaction().begin();
        QuartoJpa quarto = em.find(QuartoJpa.class, id);
        em.getTransaction().commit();

        return quarto;
    }

    @Override
    public List<QuartoJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "nroquarto":
                qryBusca += "NroQuarto";
                parametro = "nroQuarto";
                break;
            case "idtocupado":
                qryBusca += "IdtOcupado";
                parametro = "idtOcupado";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<QuartoJpa> tq = em
                .createNamedQuery(qryBusca, QuartoJpa.class)
                .setParameter(parametro, dadoBusca);
        List<QuartoJpa> quartos = tq.getResultList();

        em.getTransaction().commit();

        return quartos;
    }

    @Override
    public List<QuartoJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<QuartoJpa> tq = em.createNamedQuery("Quarto.findAll", QuartoJpa.class);
        List<QuartoJpa> quartos = tq.getResultList();

        em.getTransaction().commit();

        return quartos;
    }

    @Override
    public boolean atualiza(Short id, QuartoJpa quartoNov)
            throws SQLException {
        em.getTransaction().begin();

        QuartoJpa quartoAnt = em.find(QuartoJpa.class, id);
        quartoAnt.setIdtOcupado(quartoNov.getIdtOcupado());

        CategoriaQuartoJpa cqAnt = quartoAnt.getCodCategoria();
        CategoriaQuartoJpa cqNov = quartoNov.getCodCategoria();
        if (!cqAnt.equals(cqNov)) {
            cqAnt.removeQuarto(quartoAnt, cqNov);
        }

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(QuartoJpa quarto) throws SQLException {
        em.getTransaction().begin();
        em.remove(quarto);
        em.getTransaction().commit();

        return true;
    }
}
