package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICargoDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.CargoDao;

public class CargoDaoAdapter implements ICargoDao{

    private final ICargoDao dao;
    private static ICargoDao instancia;

    private CargoDaoAdapter() {
        dao = CargoDao.getInstance();
    }

    public static synchronized ICargoDao getInstance() {
        if (instancia == null) {
            instancia = new CargoDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Cargo cargo) throws SQLException {
        return dao.adiciona(cargo);
    }

    @Override
    public Cargo buscaPorPk(String id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<Cargo> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<Cargo> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(String pK, Cargo cargoAtualizado) throws SQLException {
        return dao.atualiza(pK, cargoAtualizado);
    }

    @Override
    public boolean deleta(String pK) throws SQLException {
        return dao.deleta(pK);
    }
}
