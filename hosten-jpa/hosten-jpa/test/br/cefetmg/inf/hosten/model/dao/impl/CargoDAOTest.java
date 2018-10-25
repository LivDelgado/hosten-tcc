package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.ICargoDAO;
import br.cefetmg.inf.hosten.model.dao.IProgramaDAO;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nícolas
 */
public class CargoDAOTest extends BaseTest<Cargo, ICargoDAO> {

    public CargoDAOTest() throws SQLException {
        dao = CargoDAO.getInstance();
        limpaTabelas();
    }

    @Override
    final void limpaTabelas() throws SQLException {
        IProgramaDAO progDao = ProgramaDAO.getInstance();
        for (Programa p : progDao.buscaTodos()) {
            Iterator<Cargo> itC = p.getCargos().iterator();
            while (itC.hasNext()) {
                Cargo c = itC.next();
                p.removeCargo(c);
            }
            progDao.deleta(p);
        }
        for (Cargo c : dao.buscaTodos()) {
            dao.deleta(c);
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
        System.out.println("\n\n\n\n ");
    }

    @After
    public void tearDown() {
    }
    */
    
    @Override
    Cargo getNewInstanciaDomain(int i) {
        if (i < 10) {
            return new Cargo("00" + i, "Cargo " + i, false);
        } else if (i >= 10 && i < 100) {
            return new Cargo("0" + i, "Cargo " + i, false);
        } else {
            return new Cargo(String.valueOf(i), "Cargo " + i, false);
        }
    }

    
    /**
     * Test of adiciona method, of class CargoDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");
        try {
            Cargo c = getNewInstanciaDomain(1);
            imprimeConteudoObjeto(c, "");

            dao.adiciona(c);
            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class CargoDAO.
     */
    @Test
    public void testBuscaPorPk() {
        imprimeTituloTeste("buscaPorPk");

        Cargo expResult = getNewInstanciaDomain(2);

        imprimeConteudoObjeto(expResult, "expResult = ");
        try {
            dao.adiciona(expResult);

            Cargo result = dao.buscaPorPk(expResult.getCodCargo());

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
     * Test of buscaPorColuna method, of class CargoDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        try {
            Cargo cargo = getNewInstanciaDomain(3);
            imprimeConteudoObjeto(cargo, "");

            dao.adiciona(cargo);

            List<Cargo> result = dao.buscaPorColuna(cargo.getCodCargo(), "codCargo");

            boolean sucesso = false;
            for (Cargo c : result) {
                imprimeConteudoObjeto(c, "");

                if (cargo.equals(c)) {
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
     * Test of buscaTodos method, of class CargoDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");

        Cargo c1 = getNewInstanciaDomain(4);
        imprimeConteudoObjeto(c1, "c1 = ");
        Cargo c2 = getNewInstanciaDomain(5);
        imprimeConteudoObjeto(c2, "c2 = ");

        try {
            dao.adiciona(c1);
            dao.adiciona(c2);

            int j = 0;
            List<Cargo> result = dao.buscaTodos();
            for (Cargo c : result) {
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
     * Test of atualiza method, of class CargoDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        Cargo c = getNewInstanciaDomain(6);

        try {
            dao.adiciona(c);
            imprimeConteudoObjeto(c, "Cargo antes do Update = ");

            Cargo c2 = getNewInstanciaDomain(7);
            imprimeConteudoObjeto(c2, "O outro cargo = ");

            dao.atualiza(c.getCodCargo(), c2);

            c = dao.buscaPorPk(c.getCodCargo());

            imprimeConteudoObjeto(c, "Cargo depois do Update =");

            if (c.getNomCargo().equals(c2.getNomCargo())) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class CargoDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        Cargo c = getNewInstanciaDomain(8);

        try {
            dao.adiciona(c);

            dao.deleta(c);

            Cargo c2 = dao.buscaPorPk(c.getCodCargo());
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
