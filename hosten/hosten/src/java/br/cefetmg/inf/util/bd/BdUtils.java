package br.cefetmg.inf.util.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class BdUtils {
    private static EntityManager em;
    
    public enum BdEnum {
        PERSISTENCE_UNIT("hosten");

        private final String propriedade;
        private BdEnum(String propriedade) {
            this.propriedade = propriedade;
        }

        public String getPropriedade() {
            return propriedade;
        }        
    }
    
    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(BdEnum.PERSISTENCE_UNIT.getPropriedade());
        
        if (em == null) {
            em = factory.createEntityManager();
        }
        return em;
    }
    
    public static int contaLinhasResultSet(ResultSet rs) throws SQLException {
        rs.last();
        int nroLinhas = rs.getRow();

        return nroLinhas;
    }
}