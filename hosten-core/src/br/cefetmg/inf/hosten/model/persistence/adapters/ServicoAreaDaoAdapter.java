package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ServicoAreaDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoAreaDao;

public class ServicoAreaDaoAdapter implements IServicoAreaDao{

    private static IServicoAreaDao instancia;

    public static synchronized IServicoAreaDao getInstance() {
        if (instancia == null) {
            instancia = ServicoAreaDao.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaServicoArea(ServicoArea servicoArea) 
            throws SQLException {
        return instancia.adicionaServicoArea(servicoArea);
    }

    @Override
    public List<ServicoArea> buscaServicoArea(Object dadoBusca, String coluna) 
            throws SQLException {
        return instancia.buscaServicoArea(dadoBusca, coluna);
    }

    @Override
    public List<ServicoArea> buscaTodosServicoAreas() throws SQLException {
        return instancia.buscaTodosServicoAreas();
    }

    @Override
    public boolean atualizaServicoArea(
            Object pK, 
            ServicoArea servicoAreaAtualizado) throws SQLException {
        return instancia.atualizaServicoArea(pK, servicoAreaAtualizado);
    }

    @Override
    public boolean deletaServicoArea(Object pK) throws SQLException {
        return instancia.deletaServicoArea(pK);
    }
}
