package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.model.service.impl.ControlarHospedagem;
import br.cefetmg.inf.hosten.model.service.remote.IControlarHospedagemRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ControlarHospedagemAdapter implements IControlarHospedagemRemote {
    private final IControlarHospedagem controlarHospedagem;

    public ControlarHospedagemAdapter() {
        this.controlarHospedagem = new ControlarHospedagem();
    }

    @Override
    public boolean efetuarCheckIn(short nroQuarto, String codCPF, short diasEstadia, short nroAdultos, short nroCriancas) throws RemoteException {
        return controlarHospedagem.efetuarCheckIn(nroQuarto, codCPF, diasEstadia, nroAdultos, nroCriancas);
    }

    @Override
    public int efetuarCheckout(short nroQuarto) throws RemoteException {
        return controlarHospedagem.efetuarCheckout(nroQuarto);
    }

    @Override
    public List<QuartoEstado> listarTodos() throws NegocioException, RemoteException {
        return controlarHospedagem.listarTodos();
    }

    @Override
    public Hospedagem buscaHospedagem(int seqHospedagem) throws SQLException, RemoteException {
        return controlarHospedagem.buscaHospedagem(seqHospedagem);
    }

    @Override
    public QuartoHospedagem buscaQuartoHospedagem(int seqHospedagem) throws SQLException, RemoteException {
        return controlarHospedagem.buscaQuartoHospedagem(seqHospedagem);
    }
}