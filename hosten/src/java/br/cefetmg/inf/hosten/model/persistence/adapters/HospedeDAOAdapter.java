package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedeDAO;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.persistence.jdbc.HospedeDAO;
import java.sql.SQLException;
import java.util.List;

public final class HospedeDAOAdapter implements IHospedeDAO{

    private static IHospedeDAO instancia;

    public static synchronized IHospedeDAO getInstance() {
        if (instancia == null) {
            instancia = HospedeDAO.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaHospede(Hospede hospede) throws SQLException {
        return instancia.adicionaHospede(hospede);
    }

    @Override
    public List<Hospede> buscaHospede(
            Object dadoBusca, 
            String coluna) throws SQLException {
        return instancia.buscaHospede(dadoBusca, coluna);
    }

    @Override
    public List<Hospede> buscaTodosHospedes() throws SQLException {
        return instancia.buscaTodosHospedes();
    }

    @Override
    public boolean atualizaHospede(
            Object pK, 
            Hospede hospedeAtualizado) throws SQLException {
        return instancia.atualizaHospede(pK, hospedeAtualizado);
    }

    @Override
    public boolean deletaHospede(Object pK) throws SQLException {
        return instancia.deletaHospede(pK);
    }
}
