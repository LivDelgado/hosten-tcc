package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.persistence.jdbc.rel.QuartoHospedagemDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoHospedagemDao;

public class QuartoHospedagemDaoAdapter implements IQuartoHospedagemDao {

    private static IQuartoHospedagemDao instancia;

    public static synchronized IQuartoHospedagemDao getInstance() {
        if (instancia == null) {
            instancia = QuartoHospedagemDao.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoHospedagem quartoHospedagem) throws SQLException {
        return instancia.adiciona(quartoHospedagem);
    }

    @Override
    public List<QuartoHospedagem> busca(Object dadoBusca, String coluna)
            throws SQLException {
        return instancia.busca(dadoBusca, coluna);
    }

    @Override
    public List<QuartoEstado> buscaTodos() throws SQLException {
        return instancia.buscaTodos();
    }

    @Override
    public boolean deletaPorPk(int seqHospedagem, int nroQuarto) throws SQLException {
        return instancia.deletaPorPk(seqHospedagem, nroQuarto);
    }

    @Override
    public boolean deleta(QuartoHospedagem quartoHospedagem) throws SQLException {
        return instancia.deleta(quartoHospedagem);
    }

}
