package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.embeddable.QuartoHospedagemId;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.QuartoHospedagemJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.IQuartoHospedagemDaoJpa;

public class QuartoHospedagemDaoJpa implements IQuartoHospedagemDaoJpa {

    private static final String NAMED_QUERY_BASE = "QuartoHospedagem.findBy";

    private static QuartoHospedagemDaoJpa instancia;

    private final EntityManager em;

    private QuartoHospedagemDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized QuartoHospedagemDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new QuartoHospedagemDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoHospedagemJpa qh) throws SQLException {
        em.getTransaction().begin();
        em.persist(qh);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public QuartoHospedagemJpa buscaPorPk(int seqHospedagem, short nroQuarto) throws SQLException {
        em.getTransaction().begin();
        QuartoHospedagemJpa qh = em.find(QuartoHospedagemJpa.class, new QuartoHospedagemId(seqHospedagem, nroQuarto));
        em.getTransaction().commit();

        return qh;
    }

    @Override
    public List<QuartoHospedagemJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
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
            case "nroadultos":
                qryBusca += "NroAdultos";
                parametro = "nroAdultos";
                break;
            case "nrocriancas":
                qryBusca += "NroCriancas";
                parametro = "nroCriancas";
                break;
            case "vlrdiaria":
                qryBusca += "VlrDiaria";
                parametro = "vlrDiaria";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<QuartoHospedagemJpa> tq = em
                .createNamedQuery(qryBusca, QuartoHospedagemJpa.class)
                .setParameter(parametro, dadoBusca);
        List<QuartoHospedagemJpa> qhs = tq.getResultList();

        em.getTransaction().commit();

        return qhs;
    }

    @Override
    public List<QuartoHospedagemJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();
        List<QuartoHospedagemJpa> qhs = em
                .createNamedQuery("QuartoHospedagem.findAll", QuartoHospedagemJpa.class)
                .getResultList();
        em.getTransaction().commit();

        return qhs;
    }

    @Override
    public boolean deleta(QuartoHospedagemJpa qh) throws SQLException {
        em.getTransaction().begin();
        em.remove(qh);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public int buscaUltimoRegistro(int nroQuarto) throws SQLException {
        int seqHospedagem = em
                .createNamedQuery("QuartoHospedagem.fetchUltimoRegistroQuarto",
                        QuartoHospedagemJpa.class)
                .setParameter("nroQuarto", nroQuarto)
                .getResultList()
                .get(0)
                .getId()
                .getSeqHospedagem();

        return seqHospedagem;
    }
}
