package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.HospedagemJpa;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.HospedeJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.IHospedagemDaoJpa;

public class HospedagemDaoJpa implements IHospedagemDaoJpa {

    private static final String NAMED_QUERY_BASE = "Hospedagem.findBy";

    private static HospedagemDaoJpa instancia;

    private final EntityManager em;

    private HospedagemDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized HospedagemDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new HospedagemDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(HospedagemJpa hospedagem) throws SQLException {
        em.getTransaction().begin();
        em.persist(hospedagem);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public HospedagemJpa buscaPorPk(Integer id) throws SQLException {
        em.getTransaction().begin();
        HospedagemJpa hospedagem = em.find(HospedagemJpa.class, id);
        em.getTransaction().commit();

        return hospedagem;
    }

    @Override
    public List<HospedagemJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "seqhospedagem":
                qryBusca += "SeqHospedagem";
                parametro = "seqHospedagem";
                break;
            case "datcheckin":
                qryBusca += "DatCheckin";
                parametro = "datCheckin";
                break;
            case "datcheckout":
                qryBusca += "DatCheckout";
                parametro = "datCheckout";
                break;
            case "vlrpago":
                qryBusca += "VlrPago";
                parametro = "vlrPago";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<HospedagemJpa> tq = em
                .createNamedQuery(qryBusca, HospedagemJpa.class)
                .setParameter(parametro, dadoBusca);
        List<HospedagemJpa> hospedagems = tq.getResultList();

        em.getTransaction().commit();

        return hospedagems;
    }

    @Override
    public List<HospedagemJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<HospedagemJpa> tq = em.createNamedQuery("Hospedagem.findAll", HospedagemJpa.class);
        List<HospedagemJpa> hospedagems = tq.getResultList();

        em.getTransaction().commit();

        return hospedagems;
    }

    @Override
    public boolean atualiza(Integer id, HospedagemJpa hospedagemNov)
            throws SQLException {
        em.getTransaction().begin();

        HospedagemJpa hospedagemAnt = em.find(HospedagemJpa.class, id);

        hospedagemAnt.setDatCheckin(hospedagemNov.getDatCheckin());
        hospedagemAnt.setDatCheckout(hospedagemNov.getDatCheckout());
        hospedagemAnt.setVlrPago(hospedagemNov.getVlrPago());

        HospedeJpa hospedeAnt = hospedagemAnt.getCodCpf();
        HospedeJpa hospedeNov = hospedagemNov.getCodCpf();
        if (hospedeAnt != null && hospedeNov != null) {
            if (!hospedagemAnt.equals(hospedagemNov)) {
                hospedeAnt.removeHospedagem(hospedeNov, hospedagemAnt);
            }
        }

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(HospedagemJpa hospedagem) throws SQLException {
        em.getTransaction().begin();
        em.remove(hospedagem);
        em.getTransaction().commit();

        return true;
    }
}
