package br.cefetmg.inf.hosten.model.dao.impl;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Nícolas
 * @param <Domain>
 * @param <IDomainDAO>
 */
public abstract class BaseTest<Domain, IDomainDAO> {
    private static final String MSG_SUCESSO = "\n-> O teste foi bem-sucedido <--------";
    private static final String MSG_ERRO = "\n->!! O teste falhou !!<--------";
    
    IDomainDAO dao;

    public BaseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("\n\n\n");
    }

    @After
    public void tearDown() {
    }

    static void imprimeTituloTeste(String nomCasoTeste) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-> " + nomCasoTeste.toUpperCase() + " <----------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("                                    ----------------------------");
        System.out.println("                                                   -------------");
    }

    static void finalizaCasoTeste() {
        System.out.println("                                                   -------------");
        System.out.println("                                    ----------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
    }

    void imprimeConteudoObjeto(Domain c, String nota) {
        System.out.println(nota + c.toString());
    }

    static void concluiComSucesso() {
        System.out.println(MSG_SUCESSO);
        assert true;
        finalizaCasoTeste();
    }

    static void concluiComFalha(Exception e) {
        if (e != null) {
            System.out.println("--!!!!!!!!!!!!!!! Exceção gerada !!!!!!!!!!!!!!!----------------");
            System.err.println(e.getMessage());
        }
        fail(MSG_ERRO);
        finalizaCasoTeste();
    }
    
    abstract Domain getNewInstanciaDomain(int i);
    abstract void limpaTabelas() throws SQLException;
}
