package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.ICargoDAO;
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
        DAO = CargoDAO.getInstance();
        
        for(Programa p : ProgramaDAO.getInstance().buscaTodos()) {
            Iterator<Cargo> itC = p.getCargos().iterator();
            while(itC.hasNext()) {
                Cargo c = itC.next();
                p.removeCargo(c);
            }
        }
        for(Cargo c : DAO.buscaTodos()) {
            DAO.deleta(c);
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

            DAO.adiciona(c);
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
            DAO.adiciona(expResult);

            Cargo result = DAO.buscaPorPk(expResult.getCodCargo());

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

            DAO.adiciona(cargo);

            List<Cargo> result = DAO.buscaPorColuna(cargo.getCodCargo(), "codCargo");

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
            DAO.adiciona(c1);
            DAO.adiciona(c2);

            int j = 0;
            List<Cargo> result = DAO.buscaTodos();
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
            DAO.adiciona(c);
            imprimeConteudoObjeto(c, "Cargo antes do Update = ");

            Cargo c2 = getNewInstanciaDomain(7);
            imprimeConteudoObjeto(c2, "O outro cargo = ");

            DAO.atualiza(c.getCodCargo(), c2);

            c = DAO.buscaPorPk(c.getCodCargo());

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
            DAO.adiciona(c);

            DAO.deleta(c);

            Cargo c2 = DAO.buscaPorPk(c.getCodCargo());
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
