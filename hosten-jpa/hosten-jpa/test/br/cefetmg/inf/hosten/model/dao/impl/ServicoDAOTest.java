package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.IServicoAreaDAO;
import br.cefetmg.inf.hosten.model.dao.IServicoDAO;
import static br.cefetmg.inf.hosten.model.dao.impl.BaseTest.imprimeTituloTeste;
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
public class ServicoDAOTest extends BaseTest<Servico, IServicoDAO> {

    private final IServicoAreaDAO saDao;

    public ServicoDAOTest() throws SQLException {
        dao = ServicoDAO.getInstance();
        saDao = ServicoAreaDAO.getInstance(); 
        //limpaTabelas();
    }

    @Override
    final void limpaTabelas() throws SQLException {
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------- LIMPANDO AS TABELAS --------------------");
        System.out.println("--------------------------------------------------------");
        for (Servico q : dao.buscaTodos()) {
            dao.deleta(q);
        }
        for (ServicoArea sa : saDao.buscaTodos()) {
            saDao.deleta(sa);
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
    Servico getNewInstanciaDomain(int i) {
        Servico q = new Servico("Serviço n° " + i, BigDecimal.valueOf(i * 10.0));
        System.out.println("------------------------");
        imprimeConteudoObjeto(q, "Serviço(" + i + ")=");
        System.out.println("------------------------");

        return q;
    }

    ServicoArea getNewServicoArea(int i) {
        return new ServicoArea(String.valueOf(i), "Área de Serviço n°" + i);
    }

    /**
     * Test of adiciona method, of class ServicoDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");

        ServicoArea cq = getNewServicoArea(1);
        Servico q = getNewInstanciaDomain(1);
        cq.addServico(q);
        try {
            saDao.adiciona(cq);
            dao.adiciona(q);

            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class ServicoDAO.
     */
    @Test
    public void testBuscaPorPk() {
        imprimeTituloTeste("buscaPorPk");

        ServicoArea cq = getNewServicoArea(4);
        Servico q = getNewInstanciaDomain(2);
        cq.addServico(q);
        try {
            saDao.adiciona(cq);
            dao.adiciona(q);

            Servico result = dao.buscaPorPk(q.getSeqServico());
            imprimeConteudoObjeto(result, "result = ");

            if (q.equals(result)) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorColuna method, of class ServicoDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        ServicoArea cq = getNewServicoArea(2);
        Servico q = getNewInstanciaDomain(3);
        cq.addServico(q);
        try {
            saDao.adiciona(cq);
            dao.adiciona(q);

            List<Servico> result = dao.buscaPorColuna(q.getSeqServico(), "seqServico");

            boolean sucesso = false;
            for (Servico qBusca : result) {
                imprimeConteudoObjeto(qBusca, "");

                if (q.equals(qBusca)) {
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
     * Test of buscaTodos method, of class ServicoDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");

        ServicoArea cq = getNewServicoArea(3);

        Servico q1 = getNewInstanciaDomain(4);
        cq.addServico(q1);
        Servico q2 = getNewInstanciaDomain(5);
        cq.addServico(q2);
        try {
            saDao.adiciona(cq);
            dao.adiciona(q1);
            dao.adiciona(q2);

            int j = 0;
            List<Servico> result = dao.buscaTodos();
            for (Servico q : result) {
                imprimeConteudoObjeto(q, "");
                if (q1.equals(q) || q2.equals(q)) {
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
     * Test of atualiza method, of class ServicoDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        ServicoArea cq = getNewServicoArea(5);
        ServicoArea cq2 = getNewServicoArea(6);

        Servico q = getNewInstanciaDomain(6);
        Servico q2 = getNewInstanciaDomain(7);

        cq.addServico(q);
        cq2.addServico(q2);
        try {
            saDao.adiciona(cq);
            dao.adiciona(q);

            saDao.adiciona(cq2);

            dao.atualiza(q.getSeqServico(), q2);

            q = dao.buscaPorPk(q.getSeqServico());
            imprimeConteudoObjeto(q, "Servico depois do Update =");

            if (q.getCodServicoArea().equals(q2.getCodServicoArea())) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class ServicoDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        Servico q = getNewInstanciaDomain(8);
        ServicoArea cq = getNewServicoArea(8);
        cq.addServico(q);

        try {
            saDao.adiciona(cq);
            dao.adiciona(q);

            dao.deleta(q);

            Servico c2 = dao.buscaPorPk(q.getSeqServico());
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
