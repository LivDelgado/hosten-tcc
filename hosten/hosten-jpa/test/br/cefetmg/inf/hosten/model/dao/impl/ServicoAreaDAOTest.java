package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nícolas
 */
public class ServicoAreaDAOTest {
    
    private static final String MSG_SUCESSO = "\n-> O teste foi bem-sucedido <--------";
    private static final String MSG_ERRO = "\n->!! O teste falhou !!<--------";

    private static ServicoAreaDAO DAO;

    public ServicoAreaDAOTest() {
        DAO = ServicoAreaDAO.getInstance();
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

    private ServicoArea getNewServicoArea(int i) {
        if (i < 10) {
            return new ServicoArea("00" + i, "ServicoArea " + i);
        } else if (i >= 10 && i < 100) {
            return new ServicoArea("0" + i, "ServicoArea " + i);
        } else {
            return new ServicoArea(String.valueOf(i), "ServicoArea " + i);
        }
    }

    private static void imprimeTituloTeste(String nomCasoTeste) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-> " + nomCasoTeste.toUpperCase() + " <----------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("                                    ----------------------------");
        System.out.println("                                                   -------------");
    }

    private static void finalizaCasoTeste() {
        System.out.println("                                                   -------------");
        System.out.println("                                    ----------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
    }

    private static void imprimeConteudoObjeto(ServicoArea c, String nota) {
        System.out.println(nota + c.toString());
    }

    private static void concluiComSucesso() {
        System.out.println(MSG_SUCESSO);
        assert true;
        finalizaCasoTeste();
    }

    private static void concluiComFalha(Exception e) {
        if (e != null) {
            System.out.println("--!!!!!!!!!!!!!!! Exceção gerada !!!!!!!!!!!!!!!----------------");
            System.err.println(e.getMessage());
        }
        fail(MSG_ERRO);
        finalizaCasoTeste();
    }

    /**
     * Test of adiciona method, of class ServicoAreaDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");
        try {
            ServicoArea sa = getNewServicoArea(1);
            
            List<Servico> ss = sa.getServicos();
            System.out.println(ss == null);
            
            DAO.adiciona(sa);
            Servico s = new Servico("SERVICAO", BigDecimal.valueOf(10.0));
            sa.addServico(s);
            
            ServicoArea sa2 = getNewServicoArea(10);
            DAO.adiciona(sa2);
            sa.removeServico(s, sa2);
            
            imprimeConteudoObjeto(sa, "");
            
            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class ServicoAreaDAO.
     */
    @Test
    public void testBuscaPorPk() {
        imprimeTituloTeste("buscaPorPk");

        ServicoArea expResult = getNewServicoArea(2);

        imprimeConteudoObjeto(expResult, "expResult = ");
        try {
            DAO.adiciona(expResult);

            ServicoArea result = DAO.buscaPorPk(expResult.getCodServicoArea());

            imprimeConteudoObjeto(result, "result = ");

            if (expResult.equals(result)) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorColuna method, of class ServicoAreaDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        try {
            ServicoArea servicoArea = getNewServicoArea(3);
            imprimeConteudoObjeto(servicoArea, "");

            DAO.adiciona(servicoArea);

            List<ServicoArea> result = DAO.buscaPorColuna(servicoArea.getCodServicoArea(), "codServicoArea");

            boolean sucesso = false;
            for (ServicoArea c : result) {
                imprimeConteudoObjeto(c, "");

                if (servicoArea.equals(c)) {
                    sucesso = true;
                }
            }
            if (sucesso) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaTodos method, of class ServicoAreaDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");

        ServicoArea c1 = getNewServicoArea(4);
        imprimeConteudoObjeto(c1, "c1 = ");
        ServicoArea c2 = getNewServicoArea(5);
        imprimeConteudoObjeto(c2, "c2 = ");

        try {
            DAO.adiciona(c1);
            DAO.adiciona(c2);

            int j = 0;
            List<ServicoArea> result = DAO.buscaTodos();
            for (ServicoArea c : result) {
                imprimeConteudoObjeto(c, "");
                if (c1.equals(c) || c2.equals(c)) {
                    j++;
                }
            }

            if (j >= 2) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of atualiza method, of class ServicoAreaDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        ServicoArea c = getNewServicoArea(6);

        try {
            DAO.adiciona(c);
            imprimeConteudoObjeto(c, "ServicoArea antes do Update = ");

            ServicoArea c2 = getNewServicoArea(7);
            imprimeConteudoObjeto(c2, "O outro servicoArea = ");

            DAO.atualiza(c.getCodServicoArea(), c2);

            c = DAO.buscaPorPk(c.getCodServicoArea());

            imprimeConteudoObjeto(c, "ServicoArea depois do Update =");

            if (c.getNomServicoArea().equals(c2.getNomServicoArea())) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class ServicoAreaDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        ServicoArea c = getNewServicoArea(8);

        try {
            DAO.adiciona(c);

            DAO.deleta(c);

            ServicoArea c2 = DAO.buscaPorPk(c.getCodServicoArea());
            if (c2 == null) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }
}
