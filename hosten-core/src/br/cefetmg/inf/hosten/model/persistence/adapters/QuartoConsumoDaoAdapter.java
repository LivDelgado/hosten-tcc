package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoConsumoDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.rel.QuartoConsumoDao;
import java.sql.Date;

public class QuartoConsumoDaoAdapter implements IQuartoConsumoDao {

    private final IQuartoConsumoDao dao;
    private static IQuartoConsumoDao instancia;

    private QuartoConsumoDaoAdapter() {
        dao = QuartoConsumoDao.getInstance();
    }

    public static synchronized IQuartoConsumoDao getInstance() {
        if (instancia == null) {
            instancia = new QuartoConsumoDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoConsumo quartoConsumo) throws SQLException {
        return dao.adiciona(quartoConsumo);
    }

    @Override
    public QuartoConsumo buscaPorPk(int seqHospedagem, short nroQuarto, Date datConsumo) throws SQLException {
        return dao.buscaPorPk(seqHospedagem, nroQuarto, datConsumo);
    }

    @Override
    public List<QuartoConsumo> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<QuartoConsumo> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean deleta(int seqHospedagem, short nroQuarto, Date datConsumo) throws SQLException {
        return dao.deleta(seqHospedagem, nroQuarto, datConsumo);
    }
}
