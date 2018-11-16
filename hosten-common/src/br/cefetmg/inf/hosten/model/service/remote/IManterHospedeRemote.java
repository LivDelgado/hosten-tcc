package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.service.*;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IManterHospedeRemote extends Remote{

    public boolean inserir(Hospede hospede) 
            throws NegocioException, SQLException, RemoteException;

    public List<Hospede> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException, RemoteException;
    public List<Hospede> listarTodos()
            throws NegocioException, SQLException, RemoteException;

    public boolean alterar(String codRegistro, Hospede hospede) 
            throws NegocioException, SQLException,RemoteException;
}
