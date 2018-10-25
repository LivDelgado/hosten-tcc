package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedagemDAO;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.persistence.jdbc.HospedagemDAO;
import java.sql.SQLException;
import java.util.List;

public final class HospedagemDAOAdapter implements IHospedagemDAO{

    private static IHospedagemDAO instancia;

    public static synchronized IHospedagemDAO getInstance() {
        if (instancia == null) {
            instancia = HospedagemDAO.getInstance();
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

    public List<Hospedagem> buscaHospedagem(
            Hospedagem hospedagem) throws SQLException {
        return instancia.busca(hospedagem);
    }

    @Override
    public boolean atualizaHospedagemPorPk(
            Object pK, 
            Hospedagem hospedagemAtualizado) throws SQLException {
        return instancia.atualizaHospedagemPorPk(pK, hospedagemAtualizado);
    }

    @Override
    public boolean atualizaHospedagem(Hospedagem hospedagemAntiga,
            Hospedagem hospedagemAtualizado) throws SQLException {
        return instancia.atualizaHospedagem(hospedagemAntiga, hospedagemAtualizado);
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
