package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.impl.ControlarDespesas;
import br.cefetmg.inf.hosten.model.service.remote.IControlarDespesasRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ControlarDespesasAdapter implements IControlarDespesasRemote {
    private final IControlarDespesas controlarDespesas;

    public ControlarDespesasAdapter() {
        this.controlarDespesas = new ControlarDespesas();
    }

    @Override
    public boolean inserir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException, RemoteException {
        return controlarDespesas.inserir(quartoConsumo);
    }

    @Override
    public List<Despesa> listar(int seqHospedagem, short nroQuarto) throws NegocioException, SQLException, RemoteException {
        return controlarDespesas.listar(seqHospedagem, nroQuarto);
    }

    @Override
    public boolean excluir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException, RemoteException {
        return controlarDespesas.excluir(quartoConsumo);
    }
}
