package br.cefetmg.inf.hosten.model.persistence.interfaces.rel;

import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.domain.rel.CargoPrograma;
import java.sql.SQLException;
import java.util.List;

public interface ICargoProgramaDao {
    boolean adiciona(CargoPrograma cargoPrograma) throws SQLException;
    
    List<CargoPrograma> busca(String cod, String coluna) throws SQLException;
    
    List<Programa> buscaProgramasRelacionados(String codCargo) throws SQLException;
    
    //atualiza();
    
    boolean deletaPorColuna(String cod, String coluna) throws SQLException;
}
