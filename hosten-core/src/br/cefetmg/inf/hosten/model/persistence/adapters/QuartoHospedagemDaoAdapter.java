package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.jdbc.rel.QuartoHospedagemDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoHospedagemDao;

public class QuartoHospedagemDaoAdapter implements IQuartoHospedagemDao {

    private final IQuartoHospedagemDao dao;
    private static IQuartoHospedagemDao instancia;

    private QuartoHospedagemDaoAdapter() {
        dao = QuartoHospedagemDao.getInstance();
    }

    public static synchronized IQuartoHospedagemDao getInstance() {
        if (instancia == null) {
            instancia = new QuartoHospedagemDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoHospedagem quartoHospedagem) throws SQLException {
        return dao.adiciona(quartoHospedagem);
    }

    @Override
    public QuartoHospedagem buscaPorPk(int seqHospedagem, short nroQuarto) throws SQLException {
        return dao.buscaPorPk(seqHospedagem, nroQuarto);
    }

    @Override
    public List<QuartoHospedagem> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<QuartoHospedagem> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean deleta(int seqHospedagem, short nroQuarto) throws SQLException {
        return dao.deleta(seqHospedagem, nroQuarto);
    }

    @Override
    public int buscaUltimoRegistro(short nroQuarto) throws SQLException {
        return dao.buscaUltimoRegistro(nroQuarto);
    }
}
