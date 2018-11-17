package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public interface IControlarHospedagem {

    public boolean efetuarCheckIn(short nroQuarto, String codCPF, short diasEstadia, short nroAdultos, short nroCriancas);

    public int efetuarCheckOut(short nroQuarto);

    public List<QuartoEstado> listarTodos() throws NegocioException;

    public Hospedagem buscaHospedagem(int seqHospedagem) throws SQLException;

    public QuartoHospedagem buscaQuartoHospedagem(int seqHospedagem) throws SQLException;
}
