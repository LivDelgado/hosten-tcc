package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedagemDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.HospedagemDao;

public final class HospedagemDaoAdapter implements IHospedagemDao {

    private final IHospedagemDao dao;
    private static IHospedagemDao instancia;

    private HospedagemDaoAdapter() {
        dao = HospedagemDao.getInstance();
    }

    public static synchronized IHospedagemDao getInstance() {
        if (instancia == null) {
            instancia = new HospedagemDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Hospedagem hospedagem) throws SQLException {
        return dao.adiciona(hospedagem);
    }

    @Override
    public Hospedagem buscaPorPk(int id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<Hospedagem> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<Hospedagem> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(int pK, Hospedagem hospedagemAtualizado) throws SQLException {
        return dao.atualiza(pK, hospedagemAtualizado);
    }

    @Override
    public boolean deleta(int pK) throws SQLException {
        return dao.deleta(pK);
    }
}
