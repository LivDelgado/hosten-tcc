package br.cefetmg.inf.hosten.model.dao.rel;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.domain.rel.views.QuartoEstado;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoHospedagemDAO {

    boolean adiciona(QuartoHospedagem quartoHospedagem) throws SQLException;

    List<QuartoHospedagem> busca(Object dadoBusca, String coluna) throws SQLException;
    
    List<QuartoEstado> buscaTodos() throws SQLException;

    boolean deletaPorPk(int seqHospedagem, int nroQuarto) throws SQLException;

    boolean deleta(QuartoHospedagem quartoHospedagem) throws SQLException;
    
    int buscaUltimoRegistro(int nroQuarto) throws SQLException;
}
