package br.cefetmg.inf.hosten.model.persistencia.adapters;

import br.cefetmg.inf.hosten.model.persistencia.interfaces.ICargoDAO;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.persistencia.jdbc.CargoDAO;
import java.sql.SQLException;
import java.util.List;

public class CargoDAOAdapter implements ICargoDAO{

    private static ICargoDAO instancia;

    public static synchronized ICargoDAO getInstance() {
        if (instancia == null) {
            //
            //
                instancia = CargoDAO.getInstance();
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
