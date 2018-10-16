package br.cefetmg.inf.hosten.model.dao;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import java.sql.SQLException;
import java.util.List;

public interface IServicoAreaDAO {

    boolean adiciona(ServicoArea servicoArea) throws SQLException;

    ServicoArea buscaPorPk(String id) throws SQLException;

    List<ServicoArea> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<ServicoArea> buscaTodos() throws SQLException;

    boolean atualiza(String id, ServicoArea servicoAreaAtualizado) throws SQLException;

    boolean deleta(ServicoArea servicoArea) throws SQLException;
}
