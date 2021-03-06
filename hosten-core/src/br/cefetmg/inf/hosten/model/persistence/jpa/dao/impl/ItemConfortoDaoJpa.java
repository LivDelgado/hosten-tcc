package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IItemConfortoDao;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ItemConfortoDaoJpa implements IItemConfortoDao {

    private static final String NAMED_QUERY_BASE = "ItemConforto.findBy";

    private static ItemConfortoDaoJpa instancia;

    private final EntityManager em;

    private ItemConfortoDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized ItemConfortoDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new ItemConfortoDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(ItemConforto itemConforto) throws SQLException {
        em.getTransaction().begin();
        em.persist(itemConforto);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public ItemConforto buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        ItemConforto itemConforto = em.find(ItemConforto.class, id);
        em.getTransaction().commit();

        return itemConforto;
    }

    @Override
    public List<ItemConforto> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "coditem":
                qryBusca += "CodItem";
                parametro = "codItem";
                break;
            case "desitem":
                qryBusca += "DesItem";
                parametro = "desItem";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<ItemConforto> tq = em
                .createNamedQuery(qryBusca, ItemConforto.class)
                .setParameter(parametro, dadoBusca);
        List<ItemConforto> itemConfortos = tq.getResultList();

        em.getTransaction().commit();

        return itemConfortos;
    }

    @Override
    public List<ItemConforto> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<ItemConforto> tq = em.createNamedQuery("ItemConforto.findAll", ItemConforto.class);
        List<ItemConforto> itemConfortos = tq.getResultList();

        em.getTransaction().commit();

        return itemConfortos;
    }

    @Override
    public boolean atualiza(String id, ItemConforto itemConfortoAtualizado)
            throws SQLException {
        em.getTransaction().begin();
        ItemConforto itemConforto = em.find(ItemConforto.class, id);
        itemConforto.setDesItem(itemConfortoAtualizado.getDesItem());
        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(String codItem) throws SQLException {
        em.getTransaction().begin();
        em.remove(buscaPorPk(codItem));
        em.getTransaction().commit();

        return true;
    }
}
