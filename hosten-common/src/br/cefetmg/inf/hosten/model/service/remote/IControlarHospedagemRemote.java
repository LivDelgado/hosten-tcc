package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.service.*;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IControlarHospedagemRemote extends Remote {
    public boolean efetuarCheckIn(String nroQuarto, String codCPF, int diasEstadia, int nroAdultos, int nroCriancas)
            throws RemoteException;
    public int efetuarCheckOut(String nroQuarto)
            throws RemoteException;
    public List<QuartoEstado> listarTodos() throws NegocioException, RemoteException;
    public Hospedagem buscaHospedagem(int seqHospedagem) throws SQLException, RemoteException;
    public QuartoHospedagem buscaQuartoHospedagem(int seqHospedagem) throws SQLException, RemoteException;
}
