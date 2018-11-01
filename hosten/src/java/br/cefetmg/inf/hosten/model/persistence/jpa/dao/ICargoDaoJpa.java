package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CargoJpa;
import java.sql.SQLException;
import java.util.List;

public interface ICargoDaoJpa {

    boolean adiciona(CargoJpa cargo) throws SQLException;

    CargoJpa buscaPorPk(String id) throws SQLException;

    List<CargoJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<CargoJpa> buscaTodos() throws SQLException;

    boolean atualiza(String id, CargoJpa cargoAtualizado) throws SQLException;

    boolean deleta(CargoJpa cargo) throws SQLException;
}
