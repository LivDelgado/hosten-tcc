package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.ICategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

        TypedQuery<CategoriaQuarto> tq = em
                .createNamedQuery(qryBusca, CategoriaQuarto.class)
                .setParameter(parametro, dadoBusca);
        List<CategoriaQuarto> categoriaQuartos = tq.getResultList();

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
        
        Set<ItemConforto> itensAnt = cqAnt.getItemConfortos();
        Set<ItemConforto> itensNovos = cqNova.getItemConfortos();
        
        Iterator<ItemConforto> itItensNovos = itensNovos.iterator();
        while(itItensNovos.hasNext()) {
            ItemConforto itemNovo = itItensNovos.next();
            
            if(!itensAnt.contains(itemNovo)) {
                cqAnt.addItemConforto(itemNovo);
            }
        }
        Iterator<ItemConforto> itItensAnt = itensAnt.iterator();
        while (itItensAnt.hasNext()) {
            ItemConforto itemAnt = itItensNovos.next();

            if (!itensNovos.contains(itemAnt)) {
                cqAnt.removeItemConforto(itemAnt);
            }
        }
        
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

    @Override
    public boolean adicionaQuartoACategoriaQuarto(CategoriaQuarto categoriaQuarto, Quarto quarto) throws SQLException {
        categoriaQuarto.addQuarto(quarto);
        
        return true;
    }

    @Override
    public boolean relacionaItemConfortoCategoriaQuarto(CategoriaQuarto categoriaQuarto, ItemConforto itemConforto) throws SQLException {
        categoriaQuarto.addItemConforto(itemConforto);
        
        return true;
    }
}
