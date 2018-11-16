package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.model.service.impl.ControlarHospedagem;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;


public class ControlarHospedagemProxy implements IControlarHospedagem {
    private final IControlarHospedagem controlarHospedagem;

    public ControlarHospedagemProxy() {
        this.controlarHospedagem = new ControlarHospedagem();
    }
    
    @Override
    public boolean efetuarCheckIn(String nroQuarto, String codCPF, int diasEstadia, int nroAdultos, int nroCriancas) {
        return controlarHospedagem.efetuarCheckIn(nroQuarto, codCPF, diasEstadia, nroAdultos, nroCriancas);
    }

    @Override
    public int efetuarCheckOut(String nroQuarto) {
        return controlarHospedagem.efetuarCheckOut(nroQuarto);
    }

    @Override
    public List<QuartoEstado> listarTodos() throws NegocioException {
        return controlarHospedagem.listarTodos();
    }

    @Override
    public Hospedagem buscaHospedagem(int seqHospedagem) throws SQLException {
        return controlarHospedagem.buscaHospedagem(seqHospedagem);
    }

    @Override
    public QuartoHospedagem buscaQuartoHospedagem(int seqHospedagem) throws SQLException {
        return controlarHospedagem.buscaQuartoHospedagem(seqHospedagem);
    }

}
