package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.IServicoDAO;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ServicoDAO implements IServicoDAO {

    private static final String NAMED_QUERY_BASE = "Servico.findBy";

    private static ServicoDAO instancia;

    private final EntityManager em;

    private ServicoDAO() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized ServicoDAO getInstance() {
        if (instancia == null) {
            instancia = new ServicoDAO();
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
    public Servico buscaPorPk(String id) throws SQLException {
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
    public boolean atualiza(String id, Servico servicoAtualizado)
            throws SQLException {
        em.getTransaction().begin();
        
        Servico servico = em.find(Servico.class, id);
        servico.setDesServico(servicoAtualizado.getDesServico());
        servico.setVlrUnit(servicoAtualizado.getVlrUnit());
        
        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(Servico servico) throws SQLException {
        em.getTransaction().begin();
        em.remove(servico);
        em.getTransaction().commit();

        return true;
    }
}