package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.ItemConfortoJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.IItemConfortoDaoJpa;

public class ItemConfortoDaoJpa implements IItemConfortoDaoJpa {

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
    public boolean adiciona(ItemConfortoJpa itemConforto) throws SQLException {
        em.getTransaction().begin();
        em.persist(itemConforto);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public ItemConfortoJpa buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        ItemConfortoJpa itemConforto = em.find(ItemConfortoJpa.class, id);
        em.getTransaction().commit();

        return itemConforto;
    }

    @Override
    public List<ItemConfortoJpa> buscaPorColuna(Object dadoBusca, String coluna)
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

        TypedQuery<ItemConfortoJpa> tq = em
                .createNamedQuery(qryBusca, ItemConfortoJpa.class)
                .setParameter(parametro, dadoBusca);
        List<ItemConfortoJpa> itemConfortos = tq.getResultList();

        em.getTransaction().commit();

        return itemConfortos;
    }

    @Override
    public List<ItemConfortoJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<ItemConfortoJpa> tq = em.createNamedQuery("ItemConforto.findAll", ItemConfortoJpa.class);
        List<ItemConfortoJpa> itemConfortos = tq.getResultList();

        em.getTransaction().commit();

        return itemConfortos;
    }

    @Override
    public boolean atualiza(String id, ItemConfortoJpa itemConfortoAtualizado)
            throws SQLException {
        em.getTransaction().begin();
        ItemConfortoJpa itemConforto = em.find(ItemConfortoJpa.class, id);
        itemConforto.setDesItem(itemConfortoAtualizado.getDesItem());
        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(ItemConfortoJpa itemConforto) throws SQLException {
        em.getTransaction().begin();
        em.remove(itemConforto);
        em.getTransaction().commit();

        return true;
    }
}
