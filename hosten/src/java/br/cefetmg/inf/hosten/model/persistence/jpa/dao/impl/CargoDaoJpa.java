package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CargoJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.ICargoDaoJpa;

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
    public boolean adiciona(CargoJpa cargo) throws SQLException {
        em.getTransaction().begin();
        em.persist(cargo);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public CargoJpa buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        CargoJpa cargo = em.find(CargoJpa.class, id);
        em.getTransaction().commit();

        return cargo;
    }

    @Override
    public List<CargoJpa> buscaPorColuna(Object dadoBusca, String coluna)
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

        TypedQuery<CargoJpa> tq = em
                .createNamedQuery(qryBusca, CargoJpa.class)
                .setParameter(coluna, dadoBusca);
        List<CargoJpa> cargos = tq.getResultList();

        em.getTransaction().commit();

        return cargos;
    }

    @Override
    public List<CargoJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<CargoJpa> tq = em.createNamedQuery("Cargo.findAll", CargoJpa.class);
        List<CargoJpa> cargos = tq.getResultList();

        em.getTransaction().commit();

        return cargos;
    }

    @Override
    public boolean atualiza(String id, CargoJpa cargoAtualizado)
            throws SQLException {
        CargoJpa cargo = em.find(CargoJpa.class, id);

        em.getTransaction().begin();
        cargo.setNomCargo(cargoAtualizado.getNomCargo());
        cargo.setIdtMaster(cargoAtualizado.isIdtMaster());
        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(CargoJpa cargo) throws SQLException {
        em.getTransaction().begin();
        em.remove(cargo);
        em.getTransaction().commit();

        return true;
    }
}
