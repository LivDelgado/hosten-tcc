package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.IQuartoDAO;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class QuartoDAO implements IQuartoDAO {

    private static final String NAMED_QUERY_BASE = "Quarto.findBy";

    private static QuartoDAO instancia;

    private final EntityManager em;

    private QuartoDAO() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized QuartoDAO getInstance() {
        if (instancia == null) {
            instancia = new QuartoDAO();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Quarto quarto) throws SQLException {
        em.getTransaction().begin();
        em.persist(quarto);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public Quarto buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        Quarto quarto = em.find(Quarto.class, id);
        em.getTransaction().commit();

        return quarto;
    }

    @Override
    public List<Quarto> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "nroquarto":
                qryBusca += "NroQuarto";
                parametro = "nroQuarto";
                break;
            case "idtocupado":
                qryBusca += "IdtOcupado";
                parametro = "idtOcupado";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<Quarto> tq = em
                .createNamedQuery(qryBusca, Quarto.class)
                .setParameter(parametro, dadoBusca);
        List<Quarto> quartos = tq.getResultList();

        em.getTransaction().commit();

        return quartos;
    }

    @Override
    public List<Quarto> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<Quarto> tq = em.createNamedQuery("Quarto.findAll", Quarto.class);
        List<Quarto> quartos = tq.getResultList();

        em.getTransaction().commit();

        return quartos;
    }

    @Override
    public boolean atualiza(String id, Quarto quartoNov)
            throws SQLException {
        em.getTransaction().begin();

        Quarto quartoAnt = em.find(Quarto.class, id);
        quartoAnt.setIdtOcupado(quartoNov.getIdtOcupado());

        CategoriaQuarto cqAnt = quartoAnt.getCodCategoria();
        CategoriaQuarto cqNov = quartoNov.getCodCategoria();
        if (!cqAnt.equals(cqNov)) {
            cqAnt.removeQuarto(quartoAnt, cqNov);
        }

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(Quarto quarto) throws SQLException {
        em.getTransaction().begin();
        em.remove(quarto);
        em.getTransaction().commit();

        return true;
    }
}
