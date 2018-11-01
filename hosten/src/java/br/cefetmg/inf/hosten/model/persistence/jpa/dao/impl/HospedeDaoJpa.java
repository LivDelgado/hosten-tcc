package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.HospedeJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.IHospedeDaoJpa;

public class HospedeDaoJpa implements IHospedeDaoJpa {

    private static final String NAMED_QUERY_BASE = "Hospede.findBy";

    private static HospedeDaoJpa instancia;

    private final EntityManager em;

    HospedeDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized HospedeDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new HospedeDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(HospedeJpa hospede) throws SQLException {
        em.getTransaction().begin();
        em.persist(hospede);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public HospedeJpa buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        HospedeJpa hospede = em.find(HospedeJpa.class, id);
        em.getTransaction().commit();

        return hospede;
    }

    @Override
    public List<HospedeJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "codcpf":
                qryBusca += "CodCpf";
                parametro = "codCpf";
                break;
            case "nomhospede":
                qryBusca += "NomHospede";
                parametro = "nomHospede";
                break;
            case "destelefone":
                qryBusca += "DesTelefone";
                parametro = "desTelefone";
                break;
            case "desemail":
                qryBusca += "DesEmail";
                parametro = "desEmail";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<HospedeJpa> tq = em
                .createNamedQuery(qryBusca, HospedeJpa.class)
                .setParameter(parametro, dadoBusca);
        List<HospedeJpa> hospedes = tq.getResultList();

        em.getTransaction().commit();

        return hospedes;
    }

    @Override
    public List<HospedeJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<HospedeJpa> tq = em.createNamedQuery("Hospede.findAll", HospedeJpa.class);
        List<HospedeJpa> hospedes = tq.getResultList();

        em.getTransaction().commit();

        return hospedes;
    }

    @Override
    public boolean atualiza(String id, HospedeJpa hospedeNov)
            throws SQLException {
        em.getTransaction().begin();

        HospedeJpa hospedeAnt = em.find(HospedeJpa.class, id);
        hospedeAnt.setNomHospede(hospedeNov.getNomHospede());
        hospedeAnt.setDesEmail(hospedeNov.getDesEmail());
        hospedeAnt.setDesTelefone(hospedeNov.getDesTelefone());

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(HospedeJpa hospede) throws SQLException {
        em.getTransaction().begin();
        em.remove(hospede);
        em.getTransaction().commit();

        return true;
    }
}
