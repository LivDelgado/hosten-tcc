package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.persistence.jdbc.HospedagemDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedagemDao;

public final class HospedagemDaoAdapter implements IHospedagemDao{

    private static IHospedagemDao instancia;

    public static synchronized IHospedagemDao getInstance() {
        if (instancia == null) {
            instancia = HospedagemDao.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaHospedagem(
            Hospedagem hospedagem) throws SQLException {
        return instancia.adicionaHospedagem(hospedagem);
    }

    @Override
    public List<Hospedagem> buscaHospedagem(Object dadoBusca, String coluna)
            throws SQLException {
        return instancia.buscaHospedagem(dadoBusca, coluna);
    }

    @Override
    public List<Hospedagem> buscaTodosHospedagems() throws SQLException {
        return instancia.buscaTodosHospedagems();
    }

    @Override
    public boolean atualizaHospedagemPorPk(
            Object pK, 
            Hospedagem hospedagemAtualizado) throws SQLException {
        return instancia.atualizaHospedagemPorPk(pK, hospedagemAtualizado);
    }

    @Override
    public boolean deletaHospedagem(Object pK) throws SQLException {
        return instancia.deletaHospedagem(pK);
    }

    @Override
    public List<Hospedagem> busca(Hospedagem hospedagem) throws SQLException {
        return instancia.busca(hospedagem);
    }

}
