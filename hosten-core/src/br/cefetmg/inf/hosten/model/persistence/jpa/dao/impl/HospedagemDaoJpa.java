package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedagemDao;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class HospedagemDaoJpa implements IHospedagemDao {

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
    public boolean adiciona(Hospedagem hospedagem) throws SQLException {
        em.getTransaction().begin();
        em.persist(hospedagem);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public Hospedagem buscaPorPk(int id) throws SQLException {
        em.getTransaction().begin();
        Hospedagem hospedagem = em.find(Hospedagem.class, id);
        em.getTransaction().commit();

        return hospedagem;
    }

    @Override
    public List<Hospedagem> buscaPorColuna(Object dadoBusca, String coluna)
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

        TypedQuery<Hospedagem> tq = em
                .createNamedQuery(qryBusca, Hospedagem.class)
                .setParameter(parametro, dadoBusca);
        List<Hospedagem> hospedagems = tq.getResultList();

        em.getTransaction().commit();

        return hospedagems;
    }

    @Override
    public List<Hospedagem> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<Hospedagem> tq = em.createNamedQuery("Hospedagem.findAll", Hospedagem.class);
        List<Hospedagem> hospedagems = tq.getResultList();

        em.getTransaction().commit();

        return hospedagems;
    }

    @Override
    public boolean atualiza(int id, Hospedagem hospedagemNov)
            throws SQLException {
        em.getTransaction().begin();

        Hospedagem hospedagemAnt = em.find(Hospedagem.class, id);

        hospedagemAnt.setDatCheckin(hospedagemNov.getDatCheckin());
        hospedagemAnt.setDatCheckout(hospedagemNov.getDatCheckout());
        hospedagemAnt.setVlrPago(hospedagemNov.getVlrPago());

        Hospede hospedeAnt = hospedagemAnt.getHospede();
        Hospede hospedeNov = hospedagemNov.getHospede();
        if (hospedeAnt != null && hospedeNov != null) {
            if (!hospedagemAnt.equals(hospedagemNov)) {
                hospedeAnt.removeHospedagem(hospedeNov, hospedagemAnt);
            }
        }

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(int seqHospedagem) throws SQLException {
        em.getTransaction().begin();
        em.remove(buscaPorPk(seqHospedagem));
        em.getTransaction().commit();

        return true;
    }
}
