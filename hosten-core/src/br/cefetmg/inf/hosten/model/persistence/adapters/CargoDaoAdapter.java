package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.persistence.jdbc.CargoDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICargoDao;

public class CargoDaoAdapter implements ICargoDao{

    private static ICargoDao instancia;

    public static synchronized ICargoDao getInstance() {
        if (instancia == null) {
            //
            //
                instancia = CargoDao.getInstance();
            //
            //
        }
        return instancia;
    }

    @Override
    public boolean adicionaCargo(Cargo cargo) throws SQLException {
        return instancia.adicionaCargo(cargo);
    }

    @Override
    public List<Cargo> buscaCargo(Object dadoBusca, String coluna)
            throws SQLException {
        return instancia.buscaCargo(dadoBusca, coluna);
    }

    @Override
    public List<Cargo> buscaTodosCargos() throws SQLException {
        return instancia.buscaTodosCargos();
    }

    @Override
    public boolean atualizaCargo(Object pK, Cargo cargoAtualizado) 
            throws SQLException {
        return instancia.atualizaCargo(pK, cargoAtualizado);
    }

    @Override
    public boolean deletaCargo(Object pK) throws SQLException {
        return instancia.deletaCargo(pK);
    }
}
