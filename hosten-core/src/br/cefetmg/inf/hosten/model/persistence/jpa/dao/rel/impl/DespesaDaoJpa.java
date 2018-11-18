package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.impl;
import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IDespesaDao;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;

public class DespesaDaoJpa implements IDespesaDao {

    private static DespesaDaoJpa instancia;

    private final EntityManager em;

    private DespesaDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized DespesaDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new DespesaDaoJpa();
        }
        return instancia;
    }

    @Override
    public List<Despesa> busca(int seqHospedagem, short nroQuarto) throws SQLException {
        List<Despesa> despesas = em
                .createNamedQuery("Despesa.findBySeqHospedagemAndNroQuarto", Despesa.class)
                .setParameter("seqHospedagem", seqHospedagem)
                .setParameter("nroQuarto", nroQuarto)
                .getResultList();

        return despesas;
    }
}
