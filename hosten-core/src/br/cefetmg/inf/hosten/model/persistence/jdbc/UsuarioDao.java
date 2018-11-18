package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.util.SenhaUtils;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IUsuarioDao;

public class UsuarioDao implements IUsuarioDao {

    private static Connection con;
    private static UsuarioDao instancia;

    public UsuarioDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized UsuarioDao getInstance() {
        if (instancia == null) {
            instancia = new UsuarioDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Usuario usuario)
            throws SQLException,
            NoSuchAlgorithmException,
            UnsupportedEncodingException {

        final String qry
                = "INSERT INTO Usuario"
                + "(codUsuario, nomUsuario, codCargo, desSenha, desEmail)"
                + " VALUES (?,?,?,?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, usuario.getCodUsuario());
        pStmt.setString(2, usuario.getNomUsuario());
        pStmt.setString(3, usuario.getCargo().getCodCargo());
        pStmt.setString(4, SenhaUtils.stringParaSHA256(usuario.getDesSenha()));
        pStmt.setString(5, usuario.getDesEmail());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public Usuario buscaPorPk(String id) throws SQLException {

        final String qry
                = "SELECT * FROM Usuario "
                + "WHERE  codUsuario LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, id);
        ResultSet rs = pStmt.executeQuery();

        Usuario user = new Usuario(
                rs.getString(1),
                rs.getString(2),
                rs.getString(4),
                rs.getString(5));

        user.setCargo(new Cargo(rs.getString(3)));

        return user;
    }

    @Override
    public List<Usuario> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        final String qry
                = "SELECT * FROM Usuario "
                + "WHERE " + coluna + " LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {
            
            case "codusuario":
                if (dadoBusca instanceof Usuario) {
                    pStmt.setString(1, ((Usuario) dadoBusca).getCodUsuario());
                } else {
                    pStmt.setString(1, dadoBusca.toString());
                }
                break;
                
            case "nomusuario":
                pStmt.setString(1, dadoBusca.toString());
                break;
                
            case "codcargo":
                if (dadoBusca instanceof Cargo) {
                    pStmt.setString(1, ((Cargo) dadoBusca).getCodCargo());
                } else {
                    pStmt.setString(1, dadoBusca.toString());
                }
                break;
                
            case "dessenha":
                pStmt.setString(1, dadoBusca.toString());
                break;
                
            case "desemail":
                pStmt.setString(1, dadoBusca.toString());
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<Usuario> usuarioEncontrados = new ArrayList<>();

        while (rs.next()) {
            Usuario user = new Usuario(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(4),
                    rs.getString(5));

            user.setCargo(new Cargo(rs.getString(3)));

            usuarioEncontrados.add(user);
        }

        return usuarioEncontrados;
    }

    @Override
    public List<Usuario> buscaTodos() throws SQLException {

        final String qry
                = "SELECT * FROM Usuario";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<Usuario> usuariosEncontrados = new ArrayList<>();

        while (rs.next()) {
            Usuario user = new Usuario(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(4),
                    rs.getString(5));

            user.setCargo(new Cargo(rs.getString(3)));

            usuariosEncontrados.add(user);
        }

        return usuariosEncontrados;
    }

    @Override
    public boolean atualiza(String id, Usuario userNov)
            throws SQLException,
            NoSuchAlgorithmException,
            UnsupportedEncodingException {

        final String qry
                = "UPDATE Usuario "
                + "SET codUsuario = ?, "
                + "nomUsuario = ?, "
                + "codCargo = ?, "
                + "desSenha= ?, "
                + "desEmail = ? "
                + "WHERE codUsuario = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, userNov.getCodUsuario());
        pStmt.setString(2, userNov.getNomUsuario());
        pStmt.setString(3, userNov.getCargo().getCodCargo());
        pStmt.setString(4, SenhaUtils.stringParaSHA256(userNov.getDesSenha()));
        pStmt.setString(5, userNov.getDesEmail());
        pStmt.setString(6, id);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(String id) throws SQLException {
        
        final String qry
                = "DELETE FROM Usuario "
                + "WHERE codUsuario = ?";
        
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, id);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public Usuario usuarioLogin(String email, String senha)
            throws SQLException,
            NoSuchAlgorithmException, 
            UnsupportedEncodingException {
        
        String qry = "SELECT desSenha "
                + "FROM Usuario "
                + "WHERE desEmail = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, email);
        ResultSet rs = pStmt.executeQuery();

        String senhaEncontrada = "";
        while (rs.next()) {
            senhaEncontrada = rs.getString(1);
        }

        if (SenhaUtils.verificaSenha(senha, senhaEncontrada)) {
            List<Usuario> usuariosEncontrado = buscaPorColuna(email, "desEmail");
            return usuariosEncontrado.get(0);
        }
        // Retorna null caso o email não bata com a senha
        return null;
    }
}
