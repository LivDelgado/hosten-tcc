package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.model.service.impl.ManterHospede;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterHospedeProxy implements IManterHospede {
    private final IManterHospede manterHospede;

    public ManterHospedeProxy() {
        this.manterHospede = new ManterHospede();
    }

    @Override
    public boolean inserir(Hospede hospede) throws NegocioException, SQLException {
        return manterHospede.inserir(hospede);
    }

    @Override
    public List<Hospede> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        return manterHospede.listar(dadoBusca, coluna);
    }

    @Override
    public List<Hospede> listarTodos() throws NegocioException, SQLException {
        return manterHospede.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, Hospede hospede) throws NegocioException, SQLException {
        return manterHospede.alterar(codRegistro, hospede);
    }
}
