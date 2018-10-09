package br.cefetmg.inf.util.bd;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class BdUtils {
    public static int contaLinhasResultSet(ResultSet rs) throws SQLException {
        rs.last();
        int nroLinhas = rs.getRow();

        return nroLinhas;
    }
}
