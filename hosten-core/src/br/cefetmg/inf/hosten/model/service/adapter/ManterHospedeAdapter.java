package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.model.service.impl.ManterHospede;
import br.cefetmg.inf.hosten.model.service.remote.IManterHospedeRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ManterHospedeAdapter implements IManterHospedeRemote {
    private final IManterHospede manterHospede;

    public ManterHospedeAdapter() {
        this.manterHospede = new ManterHospede();
    }

    @Override
    public boolean inserir(Hospede hospede) throws NegocioException, SQLException, RemoteException {
        return manterHospede.inserir(hospede);
    }

    @Override
    public List<Hospede> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException, RemoteException {
        return manterHospede.listar(dadoBusca, coluna);
    }

    @Override
    public List<Hospede> listarTodos() throws NegocioException, SQLException, RemoteException {
        return manterHospede.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, Hospede hospede) throws NegocioException, SQLException, RemoteException {
        return manterHospede.alterar(codRegistro, hospede);
    }
    
}
