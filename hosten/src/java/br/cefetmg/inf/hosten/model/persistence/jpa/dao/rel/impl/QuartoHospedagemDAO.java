package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.IQuartoHospedagemDAO;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.embeddable.QuartoHospedagemId;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class QuartoHospedagemDAO implements IQuartoHospedagemDAO {

    private static final String NAMED_QUERY_BASE = "QuartoHospedagem.findBy";

    private static QuartoHospedagemDAO instancia;

    private final EntityManager em;

    private QuartoHospedagemDAO() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized QuartoHospedagemDAO getInstance() {
        if (instancia == null) {
            instancia = new QuartoHospedagemDAO();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoHospedagem qh) throws SQLException {
        em.getTransaction().begin();
        em.persist(qh);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public QuartoHospedagem buscaPorPk(int seqHospedagem, short nroQuarto) throws SQLException {
        em.getTransaction().begin();
        QuartoHospedagem qh = em.find(QuartoHospedagem.class, new QuartoHospedagemId(seqHospedagem, nroQuarto));
        em.getTransaction().commit();

        return qh;
    }

    @Override
    public List<QuartoHospedagem> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
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

        TypedQuery<QuartoHospedagem> tq = em
                .createNamedQuery(qryBusca, QuartoHospedagem.class)
                .setParameter(parametro, dadoBusca);
        List<QuartoHospedagem> qhs = tq.getResultList();

        em.getTransaction().commit();

        return qhs;
    }

    @Override
    public List<QuartoHospedagem> buscaTodos() throws SQLException {
        em.getTransaction().begin();
        List<QuartoHospedagem> qhs = em
                .createNamedQuery("QuartoHospedagem.findAll", QuartoHospedagem.class)
                .getResultList();
        em.getTransaction().commit();

        return qhs;
    }

    @Override
    public boolean deleta(QuartoHospedagem qh) throws SQLException {
        em.getTransaction().begin();
        em.remove(qh);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public int buscaUltimoRegistro(int nroQuarto) throws SQLException {
        int seqHospedagem = em
                .createNamedQuery("QuartoHospedagem.fetchUltimoRegistroQuarto",
                        QuartoHospedagem.class)
                .setParameter("nroQuarto", nroQuarto)
                .getResultList()
                .get(0)
                .getId()
                .getSeqHospedagem();

        return seqHospedagem;
    }
}
