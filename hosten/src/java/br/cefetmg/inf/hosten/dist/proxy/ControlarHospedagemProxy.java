package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.model.service.remote.IControlarHospedagemRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControlarHospedagemProxy implements IControlarHospedagem {
    private final IControlarHospedagemRemote controlarHospedagem;

    public ControlarHospedagemProxy() {
        this.controlarHospedagem = (IControlarHospedagemRemote) ClienteRMI.getInstance().receberObjeto("ControlarHospedagem");
    }
    
    @Override
    public boolean efetuarCheckIn(short nroQuarto, String codCPF, short diasEstadia, short nroAdultos, short nroCriancas) {
        try {
            return controlarHospedagem.efetuarCheckIn(nroQuarto, codCPF, diasEstadia, nroAdultos, nroCriancas);
        } catch (RemoteException ex) {
            Logger.getLogger(ControlarHospedagemProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int efetuarCheckout(short nroQuarto) {
        try {
            return controlarHospedagem.efetuarCheckout(nroQuarto);
        } catch (RemoteException ex) {
            Logger.getLogger(ControlarHospedagemProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<QuartoEstado> listarTodos() throws NegocioException {
        try {
            return controlarHospedagem.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ControlarHospedagemProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Hospedagem buscaHospedagem(int seqHospedagem) throws SQLException {
        try {
            return controlarHospedagem.buscaHospedagem(seqHospedagem);
        } catch (RemoteException ex) {
            Logger.getLogger(ControlarHospedagemProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public QuartoHospedagem buscaQuartoHospedagem(int seqHospedagem) throws SQLException {
        try {
            return controlarHospedagem.buscaQuartoHospedagem(seqHospedagem);
        } catch (RemoteException ex) {
            Logger.getLogger(ControlarHospedagemProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
