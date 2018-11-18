package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedeDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.HospedeDao;

public final class HospedeDaoAdapter implements IHospedeDao {

    private final IHospedeDao dao;
    private static IHospedeDao instancia;

    private HospedeDaoAdapter() {
        dao = HospedeDao.getInstance();
    }

    public static synchronized IHospedeDao getInstance() {
        if (instancia == null) {
            instancia = new HospedeDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Hospede hospede) throws SQLException {
        return dao.adiciona(hospede);
    }

    @Override
    public Hospede buscaPorPk(String id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<Hospede> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<Hospede> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(String pK, Hospede hospedeAtualizado) throws SQLException {
        return dao.atualiza(pK, hospedeAtualizado);
    }

    @Override
    public boolean deleta(String pK) throws SQLException {
        return dao.deleta(pK);
    }
}
