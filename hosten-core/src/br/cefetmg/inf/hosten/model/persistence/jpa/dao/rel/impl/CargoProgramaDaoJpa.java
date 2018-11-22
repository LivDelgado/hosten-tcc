package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.impl;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.domain.rel.CargoPrograma;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICargoDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IProgramaDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.ICargoProgramaDao;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl.CargoDaoJpa;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl.ProgramaDaoJpa;
import br.cefetmg.inf.util.bd.BdUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;

public class CargoProgramaDaoJpa implements ICargoProgramaDao {

    private static CargoProgramaDaoJpa instancia;
    
    private final IProgramaDao progDao;
    private final ICargoDao crgDao;

    private final EntityManager em;

    private CargoProgramaDaoJpa() {
        em = BdUtils.getEntityManager();
        progDao = ProgramaDaoJpa.getInstance();
        crgDao = CargoDaoJpa.getInstance();
    }

    public static synchronized CargoProgramaDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new CargoProgramaDaoJpa();
        }
        return instancia;
    }
    
    @Override
    public boolean adiciona(CargoPrograma crgProg) throws SQLException {
        progDao.buscaPorPk(crgProg.getCodPrograma()).addCargo(crgDao.buscaPorPk(crgProg.getCodCargo()));
        
        return true;
    }

    @Override
    public List<CargoPrograma> buscaPorColuna(String cod, String coluna) throws SQLException {
        Cargo crg;
        Programa prog;
        List<CargoPrograma> lCrgProg = new ArrayList<>();
        
        if (coluna.toLowerCase().equals("codcargo")) {
            crg = crgDao.buscaPorPk(cod);
            
            Iterator<Programa> itProg = crg.getProgramas().iterator();
            while (itProg.hasNext()) {
                Programa p = itProg.next();
                lCrgProg.add(new CargoPrograma(p.getCodPrograma(), cod));
            }
        } else if (coluna.toLowerCase().equals("codprograma")) {
            prog = progDao.buscaPorPk(cod);
            
            Iterator<Cargo> itCrg = prog.getCargos().iterator();
            while (itCrg.hasNext()) {
                Cargo c = itCrg.next();
                lCrgProg.add(new CargoPrograma(cod, c.getCodCargo()));
            }
        }
        
        return lCrgProg;
    }

    @Override
    public List<Programa> buscaProgramasRelacionados(String codCargo) throws SQLException {
        return new ArrayList<>(crgDao.buscaPorPk(codCargo).getProgramas());
    }

    @Override
    public boolean deletaPorColuna(String cod, String coluna) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
