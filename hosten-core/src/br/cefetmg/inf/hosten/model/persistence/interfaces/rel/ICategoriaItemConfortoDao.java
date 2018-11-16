package br.cefetmg.inf.hosten.model.persistence.interfaces.rel;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.domain.rel.CategoriaItemConforto;
import java.sql.SQLException;
import java.util.List;

public interface ICategoriaItemConfortoDao {
    boolean adiciona(CategoriaItemConforto categoriaItemConforto) 
            throws SQLException;
    
    List<CategoriaItemConforto> busca(String dadoBusca, String coluna) 
            throws SQLException;
    
    List<ItemConforto> buscaItensConfortoRelacionados(String codCategoria) 
            throws SQLException;
    
    //atualiza();
    
    boolean deletaPorColuna(String dadoBusca, String coluna) 
            throws SQLException;
}
