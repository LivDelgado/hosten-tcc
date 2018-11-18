package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoDaoJpa;

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
    public boolean adiciona(Servico servico) throws SQLException {
        em.getTransaction().begin();
        em.persist(servico);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public Servico buscaPorPk(Short id) throws SQLException {
        em.getTransaction().begin();
        Servico servico = em.find(Servico.class, id);
        em.getTransaction().commit();

        return servico;
    }

    @Override
    public List<Servico> buscaPorColuna(Object dadoBusca, String coluna)
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

        TypedQuery<Servico> tq = em
                .createNamedQuery(qryBusca, Servico.class)
                .setParameter(parametro, dadoBusca);
        List<Servico> servicos = tq.getResultList();

        em.getTransaction().commit();

        return servicos;
    }

    @Override
    public List<Servico> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<Servico> tq = em.createNamedQuery("Servico.findAll", Servico.class);
        List<Servico> servicos = tq.getResultList();

        em.getTransaction().commit();

        return servicos;
    }

    @Override
    public boolean atualiza(Short id, Servico servNov)
            throws SQLException {
        em.getTransaction().begin();

        Servico servAnt = em.find(Servico.class, id);
        servAnt.setDesServico(servNov.getDesServico());
        servAnt.setVlrUnit(servNov.getVlrUnit());
        
        ServicoArea saAnt = servAnt.getServicoArea();
        ServicoArea saNov = servNov.getServicoArea();
        if (!saAnt.equals(saNov)) {
            saAnt.removeServico(servAnt, saNov);
        }

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(Servico servico) throws SQLException {
        em.getTransaction().begin();
        em.remove(em.getReference(Servico.class, servico.getSeqServico()));
        em.getTransaction().commit();

        return true;
    }
}
