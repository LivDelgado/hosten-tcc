package br.cefetmg.inf.hosten.model.dao.rel.views.impl;

import br.cefetmg.inf.hosten.model.dao.rel.views.IDespesaDAO;
import br.cefetmg.inf.hosten.model.domain.rel.views.Despesa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;

public class DespesaDAO implements IDespesaDAO {

    private static DespesaDAO instancia;

    private final EntityManager em;

    private DespesaDAO() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized DespesaDAO getInstance() {
        if (instancia == null) {
            instancia = new DespesaDAO();
        }
        return instancia;
    }

    @Override
    public List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException {
        List<Despesa> despesas = em
                .createNamedQuery("Despesa.findBySeqHospedagemAndNroQuarto", Despesa.class)
                .setParameter("seqHospedagem", seqHospedagem)
                .setParameter("nroQuarto", nroQuarto)
                .getResultList();

        return despesas;
    }
}
