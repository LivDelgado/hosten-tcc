package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.UsuarioJpa;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDaoJpa {

    boolean adiciona(UsuarioJpa usuario)
            throws SQLException;

    UsuarioJpa buscaPorPk(String id) throws SQLException;

    List<UsuarioJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException;

    List<UsuarioJpa> buscaTodos()
            throws SQLException;

    boolean atualiza(String id, UsuarioJpa usuarioAtualizado)
            throws SQLException;

    boolean deleta(UsuarioJpa usuario) throws SQLException;
    
    UsuarioJpa usuarioLogin(String email, String senha) 
            throws SQLException, 
            NoSuchAlgorithmException, UnsupportedEncodingException;
}
