package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.IServicoAreaDAO;
import br.cefetmg.inf.hosten.model.dao.IServicoDAO;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nícolas
 */
public class ServicoAreaDAOTest extends BaseTest<ServicoArea, IServicoAreaDAO> {

    public ServicoAreaDAOTest() throws SQLException {
        dao = ServicoAreaDAO.getInstance();
        limpaTabelas();
    }

    @Override
    final void limpaTabelas() throws SQLException {
        IServicoDAO sDAO = ServicoDAO.getInstance();
        for (Servico s : sDAO.buscaTodos()) {
            sDAO.deleta(s);
        }
        for (ServicoArea sa : dao.buscaTodos()) {
            dao.deleta(sa);
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /*
    @Before
    public void setUp() {
        System.out.println("\n\n\n");
    }

    @After
    public void tearDown() {
    }
     */
    @Override
    ServicoArea getNewInstanciaDomain(int i) {
        if (i < 10) {
            return new ServicoArea("00" + i, "ServicoArea " + i);
        } else if (i >= 10 && i < 100) {
            return new ServicoArea("0" + i, "ServicoArea " + i);
        } else {
            return new ServicoArea(String.valueOf(i), "ServicoArea " + i);
        }
    }

    /**
     * Test of adiciona method, of class ServicoAreaDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");
        try {
            ServicoArea sa = getNewInstanciaDomain(1);

            dao.adiciona(sa);

            Servico s = new Servico("SERVICAO", BigDecimal.valueOf(10.0));
            sa.addServico(s);

            ServicoArea sa2 = getNewInstanciaDomain(10);
            dao.adiciona(sa2);
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

        ServicoArea expResult = getNewInstanciaDomain(2);

        imprimeConteudoObjeto(expResult, "expResult = ");
        try {
            dao.adiciona(expResult);

            ServicoArea result = dao.buscaPorPk(expResult.getCodServicoArea());

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
            ServicoArea servicoArea = getNewInstanciaDomain(3);
            imprimeConteudoObjeto(servicoArea, "");

            dao.adiciona(servicoArea);

            List<ServicoArea> result = dao.buscaPorColuna(servicoArea.getCodServicoArea(), "codServicoArea");

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

        ServicoArea c1 = getNewInstanciaDomain(4);
        imprimeConteudoObjeto(c1, "c1 = ");
        ServicoArea c2 = getNewInstanciaDomain(5);
        imprimeConteudoObjeto(c2, "c2 = ");

        try {
            dao.adiciona(c1);
            dao.adiciona(c2);

            int j = 0;
            List<ServicoArea> result = dao.buscaTodos();
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

        ServicoArea c = getNewInstanciaDomain(6);

        try {
            dao.adiciona(c);
            imprimeConteudoObjeto(c, "ServicoArea antes do Update = ");

            ServicoArea c2 = getNewInstanciaDomain(7);
            imprimeConteudoObjeto(c2, "O outro servicoArea = ");

            dao.atualiza(c.getCodServicoArea(), c2);

            c = dao.buscaPorPk(c.getCodServicoArea());

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

        ServicoArea c = getNewInstanciaDomain(8);

        try {
            dao.adiciona(c);

            dao.deleta(c);

            ServicoArea c2 = dao.buscaPorPk(c.getCodServicoArea());
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
