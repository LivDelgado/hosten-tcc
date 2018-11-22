package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.remote.IControlarDespesasRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlarDespesasProxy implements IControlarDespesas {

    private final IControlarDespesasRemote controlarDespesas;
    
    public ControlarDespesasProxy() {
        ClienteRMI cliente = ClienteRMI.getInstance();
        controlarDespesas = (IControlarDespesasRemote)cliente.receberObjeto("ControlarDespesas");
    }
    
    @Override
    public boolean inserir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException {
        try {
            return controlarDespesas.inserir(quartoConsumo);
        } catch (RemoteException ex) {
            Logger.getLogger(ControlarDespesasProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Despesa> listar(int seqHospedagem, short nroQuarto) throws NegocioException, SQLException {
        try {
            return controlarDespesas.listar(seqHospedagem, nroQuarto);
        } catch (RemoteException ex) {
            Logger.getLogger(ControlarDespesasProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean excluir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException {
        try {
            return controlarDespesas.excluir(quartoConsumo);
        } catch (RemoteException ex) {
            Logger.getLogger(ControlarDespesasProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
