package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.ServicoAreaJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.IServicoAreaDaoJpa;

public class ServicoAreaDaoJpa implements IServicoAreaDaoJpa {

    private static final String NAMED_QUERY_BASE = "ServicoArea.findBy";

    private static ServicoAreaDaoJpa instancia;

    private final EntityManager em;

    private ServicoAreaDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized ServicoAreaDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new ServicoAreaDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(ServicoAreaJpa servicoArea) throws SQLException {
        em.getTransaction().begin();
        em.persist(servicoArea);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public ServicoAreaJpa buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        ServicoAreaJpa servicoArea = em.find(ServicoAreaJpa.class, id);
        em.getTransaction().commit();

        return servicoArea;
    }

    @Override
    public List<ServicoAreaJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "codservicoarea":
                qryBusca += "CodServicoArea";
                parametro = "codServicoArea";
                break;
            case "nomservicoarea":
                qryBusca += "NomServicoArea";
                parametro = "nomServicoArea";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<ServicoAreaJpa> tq = em
                .createNamedQuery(qryBusca, ServicoAreaJpa.class)
                .setParameter(parametro, dadoBusca);
        List<ServicoAreaJpa> servicoAreas = tq.getResultList();

        em.getTransaction().commit();

        return servicoAreas;
    }

    @Override
    public List<ServicoAreaJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<ServicoAreaJpa> tq = em.createNamedQuery("ServicoArea.findAll", ServicoAreaJpa.class);
        List<ServicoAreaJpa> servicoAreas = tq.getResultList();

        em.getTransaction().commit();

        return servicoAreas;
    }

    @Override
    public boolean atualiza(String id, ServicoAreaJpa servicoAreaAtualizado)
            throws SQLException {
        em.getTransaction().begin();

        ServicoAreaJpa servicoArea = em.find(ServicoAreaJpa.class, id);
        servicoArea.setNomServicoArea(servicoAreaAtualizado.getNomServicoArea());

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(ServicoAreaJpa servicoArea) throws SQLException {
        em.getTransaction().begin();
        em.remove(servicoArea);
        em.getTransaction().commit();

        return true;
    }
}
