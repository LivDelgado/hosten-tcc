package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IQuartoDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.QuartoDao;

public class QuartoDaoAdapter implements IQuartoDao {

    private final IQuartoDao dao;
    private static IQuartoDao instancia;

    private QuartoDaoAdapter() {
        dao = QuartoDao.getInstance();
    }

    public static synchronized IQuartoDao getInstance() {
        if (instancia == null) {
            instancia = new QuartoDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Quarto quarto) throws SQLException {
        return dao.adiciona(quarto);
    }

    @Override
    public Quarto buscaPorPk(short id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<Quarto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<Quarto> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(short pK, Quarto quartoAtualizado) throws SQLException {
        return dao.atualiza(pK, quartoAtualizado);
    }

    @Override
    public boolean deleta(short pK) throws SQLException {
        return dao.deleta(pK);
    }
}
