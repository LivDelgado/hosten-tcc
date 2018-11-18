package br.cefetmg.inf.hosten.model.persistence.interfaces;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import java.sql.SQLException;
import java.util.List;

public interface ICargoDao {

    boolean adiciona(Cargo cargo) throws SQLException;

    Cargo buscaPorPk(String id) throws SQLException;

    List<Cargo> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<Cargo> buscaTodos() throws SQLException;

    boolean atualiza(String id, Cargo cargoAtualizado) throws SQLException;

    boolean deleta(String cargo) throws SQLException;
}
