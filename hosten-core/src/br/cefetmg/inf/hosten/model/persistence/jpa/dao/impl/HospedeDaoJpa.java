package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedeDao;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class HospedeDaoJpa implements IHospedeDao {

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
    public boolean adiciona(Hospede hospede) throws SQLException {
        em.getTransaction().begin();
        em.persist(hospede);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public Hospede buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        Hospede hospede = em.find(Hospede.class, id);
        em.getTransaction().commit();

        return hospede;
    }

    @Override
    public List<Hospede> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "codcpf":
                qryBusca += "CodCPF";
                parametro = "codCPF";
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

        TypedQuery<Hospede> tq = em
                .createNamedQuery(qryBusca, Hospede.class)
                .setParameter(parametro, dadoBusca);
        List<Hospede> hospedes = tq.getResultList();

        em.getTransaction().commit();

        return hospedes;
    }

    @Override
    public List<Hospede> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<Hospede> tq = em.createNamedQuery("Hospede.findAll", Hospede.class);
        List<Hospede> hospedes = tq.getResultList();

        em.getTransaction().commit();

        return hospedes;
    }

    @Override
    public boolean atualiza(String id, Hospede hospedeNov)
            throws SQLException {
        em.getTransaction().begin();

        Hospede hospedeAnt = em.find(Hospede.class, id);
        hospedeAnt.setNomHospede(hospedeNov.getNomHospede());
        hospedeAnt.setDesEmail(hospedeNov.getDesEmail());
        hospedeAnt.setDesTelefone(hospedeNov.getDesTelefone());

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(String codCPF) throws SQLException {
        em.getTransaction().begin();
        em.remove(buscaPorPk(codCPF));
        em.getTransaction().commit();

        return true;
    }
}
