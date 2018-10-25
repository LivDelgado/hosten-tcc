package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IQuartoConsumoDAO;
import br.cefetmg.inf.hosten.model.persistence.jdbc.QuartoConsumoDAO;

public class QuartoConsumoDAOAdapter implements IQuartoConsumoDAO {

    private static IQuartoConsumoDAO instancia;

    public static synchronized IQuartoConsumoDAO getInstance() {
        if (instancia == null) {
            instancia = QuartoConsumoDAO.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoConsumo quartoConsumo) throws SQLException {
        return instancia.adiciona(quartoConsumo);
    }

    @Override
    public List<QuartoConsumo> busca(Object dadoBusca, String coluna)
            throws SQLException {
        return instancia.busca(dadoBusca, coluna);
    }

    @Override
    public boolean deletaPorPk(int seqHospedagem, int nroQuarto,
            Timestamp datConsumo) throws SQLException {
        return instancia.deletaPorPk(seqHospedagem, nroQuarto, datConsumo);
    }

    @Override
    public boolean deleta(QuartoConsumo quartoConsumo) throws SQLException {
        return instancia.deleta(quartoConsumo);
    }
}
