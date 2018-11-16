package br.cefetmg.inf.hosten.dist;

import br.cefetmg.inf.hosten.model.service.adapter.*;
import br.cefetmg.inf.hosten.model.service.remote.*;
import br.cefetmg.inf.util.dist.ConstantesDist;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorRMI {

    private static Registry registro;

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            System.err.println("SERVER ON");
            registro = iniciaRegistro();
            cadastraStubs();

            Scanner entrada = new Scanner(System.in);

            if (entrada.hasNext()) {
                removeStubs();
                System.err.println("Conexão com o servidor encerrada.");
                System.exit(0);
            }
        } catch (Exception ex) {
            Logger.getLogger(ServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Registry iniciaRegistro()
            throws InterruptedException, RemoteException {
        Registry registro = null;
        if (LocateRegistry.getRegistry(ConstantesDist.PORTA) != null) {
            return LocateRegistry.createRegistry(ConstantesDist.PORTA);
        } else {
            return LocateRegistry.getRegistry(ConstantesDist.PORTA);
        }
    }

    private static void cadastraStubs() throws RemoteException {
        IControlarDespesasRemote controlarDespesas = new ControlarDespesasAdapter();
        IControlarDespesasRemote controlarDespesasStub = (IControlarDespesasRemote) UnicastRemoteObject.exportObject(controlarDespesas, 0);
        registro.rebind("ControlarDespesas", controlarDespesasStub);

        IControlarHospedagemRemote controlarHospedagem = new ControlarHospedagemAdapter();
        IControlarHospedagemRemote controlarHospedagemStub = (IControlarHospedagemRemote) UnicastRemoteObject.exportObject(controlarHospedagem, 0);
        registro.rebind("ControlarHospedagem", controlarHospedagemStub);

        IManterCargoRemote manterCargo = new ManterCargoAdapter();
        IManterCargoRemote manterCargoStub = (IManterCargoRemote) UnicastRemoteObject.exportObject(manterCargo, 0);
        registro.rebind("ManterCargo", manterCargoStub);

        IManterCategoriaQuartoRemote manterCategoriaQuarto = new ManterCategoriaQuartoAdapter();
        IManterCategoriaQuartoRemote manterCategoriaQuartoStub = (IManterCategoriaQuartoRemote) UnicastRemoteObject.exportObject(manterCategoriaQuarto, 0);
        registro.rebind("ManterCategoriaQuarto", manterCategoriaQuartoStub);

        IManterHospedeRemote manterHospede = new ManterHospedeAdapter();
        IManterHospedeRemote manterHospedeStub = (IManterHospedeRemote) UnicastRemoteObject.exportObject(manterHospede, 0);
        registro.rebind("ManterHospede", manterHospedeStub);

        IManterItemConfortoRemote manterItemConforto = new ManterItemConfortoAdapter();
        IManterItemConfortoRemote manterItemConfortoStub = (IManterItemConfortoRemote) UnicastRemoteObject.exportObject(manterItemConforto, 0);
        registro.rebind("ManterItemConforto", manterItemConfortoStub);

        IManterQuartoRemote manterQuarto = new ManterQuartoAdapter();
        IManterQuartoRemote manterQuartoStub = (IManterQuartoRemote) UnicastRemoteObject.exportObject(manterQuarto, 0);
        registro.rebind("ManterQuarto", manterQuartoStub);

        IManterServicoAreaRemote manterServicoArea = new ManterServicoAreaAdapter();
        IManterServicoAreaRemote manterServicoAreaStub = (IManterServicoAreaRemote) UnicastRemoteObject.exportObject(manterServicoArea, 0);
        registro.rebind("ManterServicoArea", manterServicoAreaStub);

        IManterServicoRemote manterServico = new ManterServicoAdapter();
        IManterServicoRemote manterServicoStub = (IManterServicoRemote) UnicastRemoteObject.exportObject(manterServico, 0);
        registro.rebind("ManterServico", manterServicoStub);

        IManterUsuarioRemote manterUsuario = new ManterUsuarioAdapter();
        IManterUsuarioRemote manterUsuarioStub = (IManterUsuarioRemote) UnicastRemoteObject.exportObject(manterUsuario, 0);
        registro.rebind("ManterUsuario", manterUsuarioStub);

    }

    private static void removeStubs() throws NoSuchObjectException {
        UnicastRemoteObject.unexportObject(registro, true);
    }
}
