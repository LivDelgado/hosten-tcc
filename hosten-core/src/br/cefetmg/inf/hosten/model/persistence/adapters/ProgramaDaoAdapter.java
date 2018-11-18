package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Programa;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IProgramaDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IProgramaDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ProgramaDao;

public final class ProgramaDaoAdapter implements IProgramaDao {

    private final IProgramaDao dao;
    private static IProgramaDao instancia;

    private ProgramaDaoAdapter() {
        dao = ProgramaDao.getInstance();
    }

    public static synchronized IProgramaDao getInstance() {
        if (instancia == null) {
            instancia = new ProgramaDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Programa programa) throws SQLException {
        return dao.adiciona(programa);
    }

    @Override
    public Programa buscaPorPk(String id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<Programa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<Programa> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(String pK, Programa programaAtualizado) throws SQLException {
        return dao.atualiza(pK, programaAtualizado);
    }

    @Override
    public boolean deleta(String pK) throws SQLException {
        return dao.deleta(pK);
    }
}
