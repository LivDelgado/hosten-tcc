package br.cefetmg.inf.hosten.model.dao;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import java.sql.SQLException;
import java.util.List;

public interface IProgramaDAO {

    boolean adiciona(Programa programa)
            throws SQLException;

    Programa buscaPorPk(String id) throws SQLException;

    List<Programa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<Programa> buscaTodos() throws SQLException;

    boolean atualiza(String id, Programa programaAtualizado) throws SQLException;

    boolean deleta(Programa programa) throws SQLException;

    boolean relacionaProgramaCargo(Programa programa, Cargo cargo) throws SQLException;
    
    boolean apagaRelacaoProgramaCargo(Programa programa, Cargo cargo) throws SQLException;
}
