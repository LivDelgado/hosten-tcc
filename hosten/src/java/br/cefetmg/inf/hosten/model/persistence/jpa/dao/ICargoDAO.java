package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.Cargo;
import java.sql.SQLException;
import java.util.List;

public interface ICargoDAO {

    boolean adiciona(Cargo cargo) throws SQLException;

    Cargo buscaPorPk(String id) throws SQLException;

    List<Cargo> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<Cargo> buscaTodos() throws SQLException;

    boolean atualiza(String id, Cargo cargoAtualizado) throws SQLException;

    boolean deleta(Cargo cargo) throws SQLException;
}
