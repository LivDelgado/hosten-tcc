package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.dao.ICategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CategoriaQuarto;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CategoriaQuartoDAO implements ICategoriaQuartoDAO {

    private static final String NAMED_QUERY_BASE = "CategoriaQuarto.findBy";

    private static CategoriaQuartoDAO instancia;

    private final EntityManager em;

    private CategoriaQuartoDAO() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized CategoriaQuartoDAO getInstance() {
        if (instancia == null) {
            instancia = new CategoriaQuartoDAO();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(CategoriaQuarto categoriaQuarto) throws SQLException {
        em.getTransaction().begin();
        em.persist(categoriaQuarto);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public CategoriaQuarto buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        CategoriaQuarto categoriaQuarto = em.find(CategoriaQuarto.class, id);
        em.getTransaction().commit();

        return categoriaQuarto;
    }

    @Override
    public List<CategoriaQuarto> buscaPorColuna(Object dadoBusca, String coluna)
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

        List<CategoriaQuarto> categoriaQuartos = em
                .createNamedQuery(qryBusca, CategoriaQuarto.class)
                .setParameter(parametro, dadoBusca)
                .getResultList();

        em.getTransaction().commit();

        return categoriaQuartos;
    }

    @Override
    public List<CategoriaQuarto> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<CategoriaQuarto> tq = em.createNamedQuery("CategoriaQuarto.findAll", CategoriaQuarto.class);
        List<CategoriaQuarto> categoriaQuartos = tq.getResultList();

        em.getTransaction().commit();

        return categoriaQuartos;
    }

    @Override
    public boolean atualiza(String id, CategoriaQuarto cqNova)
            throws SQLException {
        em.getTransaction().begin();

        CategoriaQuarto cqAnt = em.find(CategoriaQuarto.class, id);
        cqAnt.setNomCategoria(cqNova.getNomCategoria());
        cqAnt.setVlrDiaria(cqNova.getVlrDiaria());

        cqAnt.transferItemConforto(cqNova);

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(CategoriaQuarto categoriaQuarto) throws SQLException {
        em.getTransaction().begin();
        em.remove(categoriaQuarto);
        em.getTransaction().commit();

        return true;
    }
}
