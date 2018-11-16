package br.cefetmg.inf.hosten.model.service.remote;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IControlarDespesasRemote extends Remote {
    boolean inserir(QuartoConsumo quartoConsumo) 
            throws NegocioException, SQLException, RemoteException;

    List<Despesa> listar(int seqHospedagem, int nroQuarto)
            throws NegocioException, SQLException, RemoteException;

    boolean excluir(QuartoConsumo quartoConsumo) 
            throws NegocioException, SQLException, RemoteException;
    
    Map<String, Object> retornaDespesa(int seqHospedagem, int nroQuarto) 
            throws NegocioException, SQLException,RemoteException;
}
