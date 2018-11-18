package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICargoDaoJpa;

public class CargoDaoJpa implements ICargoDaoJpa {

    private static final String NAMED_QUERY_BASE = "Cargo.findBy";

    private static CargoDaoJpa instancia;

    private final EntityManager em;

    private CargoDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized CargoDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new CargoDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Cargo cargo) throws SQLException {
        em.getTransaction().begin();
        em.persist(cargo);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public Cargo buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        Cargo cargo = em.find(Cargo.class, id);
        em.getTransaction().commit();

        return cargo;
    }

    @Override
    public List<Cargo> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "codcargo":
                qryBusca += "CodCargo";
                break;
            case "nomcargo":
                qryBusca += "NomCargo";
                break;
            case "idtmaster":
                qryBusca += "IdtMaster";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<Cargo> tq = em
                .createNamedQuery(qryBusca, Cargo.class)
                .setParameter(coluna, dadoBusca);
        List<Cargo> cargos = tq.getResultList();

        em.getTransaction().commit();

        return cargos;
    }

    @Override
    public List<Cargo> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<Cargo> tq = em.createNamedQuery("Cargo.findAll", Cargo.class);
        List<Cargo> cargos = tq.getResultList();

        em.getTransaction().commit();

        return cargos;
    }

    @Override
    public boolean atualiza(String id, Cargo cargoAtualizado)
            throws SQLException {
        Cargo cargo = em.find(Cargo.class, id);

        em.getTransaction().begin();
        cargo.setNomCargo(cargoAtualizado.getNomCargo());
        cargo.setIdtMaster(cargoAtualizado.isIdtMaster());
        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(Cargo cargo) throws SQLException {
        em.getTransaction().begin();
        em.remove(cargo);
        em.getTransaction().commit();

        return true;
    }
}
