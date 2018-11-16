package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CategoriaQuartoJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.ICategoriaQuartoDaoJpa;

public class CategoriaQuartoDaoJpa implements ICategoriaQuartoDaoJpa {

    private static final String NAMED_QUERY_BASE = "CategoriaQuarto.findBy";

    private static CategoriaQuartoDaoJpa instancia;

    private final EntityManager em;

    private CategoriaQuartoDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized CategoriaQuartoDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new CategoriaQuartoDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(CategoriaQuartoJpa categoriaQuarto) throws SQLException {
        em.getTransaction().begin();
        em.persist(categoriaQuarto);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public CategoriaQuartoJpa buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        CategoriaQuartoJpa categoriaQuarto = em.find(CategoriaQuartoJpa.class, id);
        em.getTransaction().commit();

        return categoriaQuarto;
    }

    @Override
    public List<CategoriaQuartoJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "codcategoria":
                qryBusca += "CodCategoria";
                parametro = "codCategoria";
                break;
            case "nomcategoria":
                qryBusca += "NomCategoria";
                parametro = "nomCategoria";
                break;
            case "vlrdiaria":
                qryBusca += "VlrDiaria";
                parametro = "vlrDiaria";
                break;
        }

        em.getTransaction().begin();

        List<CategoriaQuartoJpa> categoriaQuartos = em
                .createNamedQuery(qryBusca, CategoriaQuartoJpa.class)
                .setParameter(parametro, dadoBusca)
                .getResultList();

        em.getTransaction().commit();

        return categoriaQuartos;
    }

    @Override
    public List<CategoriaQuartoJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<CategoriaQuartoJpa> tq = em.createNamedQuery("CategoriaQuarto.findAll", CategoriaQuartoJpa.class);
        List<CategoriaQuartoJpa> categoriaQuartos = tq.getResultList();

        em.getTransaction().commit();

        return categoriaQuartos;
    }

    @Override
    public boolean atualiza(String id, CategoriaQuartoJpa cqNova)
            throws SQLException {
        em.getTransaction().begin();

        CategoriaQuartoJpa cqAnt = em.find(CategoriaQuartoJpa.class, id);
        cqAnt.setNomCategoria(cqNova.getNomCategoria());
        cqAnt.setVlrDiaria(cqNova.getVlrDiaria());

        cqAnt.transferItemConforto(cqNova);

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(CategoriaQuartoJpa categoriaQuarto) throws SQLException {
        em.getTransaction().begin();
        em.remove(categoriaQuarto);
        em.getTransaction().commit();

        return true;
    }
}
