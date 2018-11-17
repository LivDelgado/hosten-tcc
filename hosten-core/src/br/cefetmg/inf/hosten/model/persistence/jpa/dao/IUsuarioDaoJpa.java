package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDaoJpa {

    boolean adiciona(Usuario usuario)
            throws SQLException;

    Usuario buscaPorPk(String id) throws SQLException;

    List<Usuario> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException;

    List<Usuario> buscaTodos()
            throws SQLException;

    boolean atualiza(String id, Usuario usuarioAtualizado)
            throws SQLException;

    boolean deleta(Usuario usuario) throws SQLException;
    
    Usuario usuarioLogin(String email, String senha) 
            throws SQLException, 
            NoSuchAlgorithmException, UnsupportedEncodingException;
}
