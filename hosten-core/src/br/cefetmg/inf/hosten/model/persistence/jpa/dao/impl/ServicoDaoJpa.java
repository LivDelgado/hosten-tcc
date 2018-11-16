package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CategoriaQuartoJpa;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.ServicoJpa;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.ServicoAreaJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.IServicoDaoJpa;

public class ServicoDaoJpa implements IServicoDaoJpa {

    private static final String NAMED_QUERY_BASE = "Servico.findBy";

    private static ServicoDaoJpa instancia;

    private final EntityManager em;

    private ServicoDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized ServicoDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new ServicoDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(ServicoJpa servico) throws SQLException {
        em.getTransaction().begin();
        em.persist(servico);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public ServicoJpa buscaPorPk(Short id) throws SQLException {
        em.getTransaction().begin();
        ServicoJpa servico = em.find(ServicoJpa.class, id);
        em.getTransaction().commit();

        return servico;
    }

    @Override
    public List<ServicoJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "seqservico":
                qryBusca += "SeqServico";
                parametro = "seqServico";
                break;
            case "desservico":
                qryBusca += "DesServico";
                parametro = "desServico";
                break;
            case "vlrunit":
                qryBusca += "VlrUnit";
                parametro = "vlrUnit";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<ServicoJpa> tq = em
                .createNamedQuery(qryBusca, ServicoJpa.class)
                .setParameter(parametro, dadoBusca);
        List<ServicoJpa> servicos = tq.getResultList();

        em.getTransaction().commit();

        return servicos;
    }

    @Override
    public List<ServicoJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<ServicoJpa> tq = em.createNamedQuery("Servico.findAll", ServicoJpa.class);
        List<ServicoJpa> servicos = tq.getResultList();

        em.getTransaction().commit();

        return servicos;
    }

    @Override
    public boolean atualiza(Short id, ServicoJpa servNov)
            throws SQLException {
        em.getTransaction().begin();

        ServicoJpa servAnt = em.find(ServicoJpa.class, id);
        servAnt.setDesServico(servNov.getDesServico());
        servAnt.setVlrUnit(servNov.getVlrUnit());
        
        ServicoAreaJpa saAnt = servAnt.getCodServicoArea();
        ServicoAreaJpa saNov = servNov.getCodServicoArea();
        if (!saAnt.equals(saNov)) {
            saAnt.removeServico(servAnt, saNov);
        }

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(ServicoJpa servico) throws SQLException {
        em.getTransaction().begin();
        em.remove(em.getReference(ServicoJpa.class, servico.getSeqServico()));
        em.getTransaction().commit();

        return true;
    }
}
