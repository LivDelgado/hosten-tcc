package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.CargoPrograma;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.persistence.jdbc.rel.CargoProgramaDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.ICargoProgramaDao;

public class CargoProgramaDaoAdapter implements ICargoProgramaDao {

    private static ICargoProgramaDao instancia;

    public static synchronized ICargoProgramaDao getInstance() {
        if (instancia == null) {
            instancia = CargoProgramaDao.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(CargoPrograma cargoPrograma) throws SQLException {
        return instancia.adiciona(cargoPrograma);
    }

    @Override
    public List<CargoPrograma> busca(String cod, String coluna)
            throws SQLException {
        return instancia.busca(cod, coluna);
    }

    @Override
    public List<Programa> buscaProgramasRelacionados(String codCargo) 
            throws SQLException {
        return instancia.buscaProgramasRelacionados(codCargo);
    }

    @Override
    public boolean deletaPorColuna(String cod, String coluna)
            throws SQLException {
        return instancia.deletaPorColuna(cod, coluna);
    }
}
