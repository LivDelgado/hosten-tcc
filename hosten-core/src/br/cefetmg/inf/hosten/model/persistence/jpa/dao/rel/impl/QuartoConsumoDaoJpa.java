package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.impl;

import br.cefetmg.inf.hosten.model.domain.idcomposto.QuartoConsumoId;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.IQuartoConsumoDaoJpa;

public class QuartoConsumoDaoJpa implements IQuartoConsumoDaoJpa {

    private static final String NAMED_QUERY_BASE = "QuartoConsumo.findBy";

    private static QuartoConsumoDaoJpa instancia;

    private final EntityManager em;

    private QuartoConsumoDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized QuartoConsumoDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new QuartoConsumoDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoConsumo quartoConsumo) throws SQLException {
        em.getTransaction().begin();
        em.persist(quartoConsumo);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public QuartoConsumo buscaPorPk(QuartoHospedagem qh, Date datConsumo) throws SQLException {
        em.getTransaction().begin();

        QuartoConsumoId qcId = new QuartoConsumoId(qh, datConsumo);
        QuartoConsumo quartoConsumo = em.find(QuartoConsumo.class, qcId);

        em.getTransaction().commit();

        return quartoConsumo;
    }

    @Override
    public List<QuartoConsumo> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "seqhospedagem":
                qryBusca += "SeqHospedagem";
                parametro = "seqHospedagem";
                break;
            case "nroquarto":
                qryBusca += "NroQuarto";
                parametro = "nroQuarto";
                break;
            case "datconsumo":
                qryBusca += "DatConsumo";
                parametro = "datConsumo";
                break;
            case "qtdconsumo":
                qryBusca += "QtdConsumo";
                parametro = "qtdConsumo";
                break;
        }

        em.getTransaction().begin();

        List<QuartoConsumo> quartoConsumos = em
                .createNamedQuery(qryBusca, QuartoConsumo.class)
                .setParameter(parametro, dadoBusca)
                .getResultList();

        em.getTransaction().commit();

        return quartoConsumos;
    }

    @Override
    public List<QuartoConsumo> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        List<QuartoConsumo> quartoConsumos = em
                .createNamedQuery("QuartoConsumo.findAll", QuartoConsumo.class)
                .getResultList();

        em.getTransaction().commit();

        return quartoConsumos;
    }

    @Override
    public boolean deleta(QuartoConsumo quartoConsumo) throws SQLException {
        em.getTransaction().begin();
        em.remove(quartoConsumo);
        em.getTransaction().commit();

        return true;
    }
}
