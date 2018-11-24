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
    
    private static IControlarDespesasRemote controlarDespesas;
    private static IControlarDespesasRemote controlarDespesasStub;
    private static IControlarHospedagemRemote controlarHospedagem;
    private static IControlarHospedagemRemote controlarHospedagemStub;
    private static IManterCargoRemote manterCargo;
    private static IManterCargoRemote manterCargoStub;
    private static IManterCategoriaQuartoRemote manterCategoriaQuarto;
    private static IManterCategoriaQuartoRemote manterCategoriaQuartoStub;
    private static IManterHospedeRemote manterHospede;
    private static IManterHospedeRemote manterHospedeStub;
    private static IManterItemConfortoRemote manterItemConforto;
    private static IManterItemConfortoRemote manterItemConfortoStub;
    private static IManterQuartoRemote manterQuarto;
    private static IManterQuartoRemote manterQuartoStub;
    private static IManterServicoAreaRemote manterServicoArea;
    private static IManterServicoAreaRemote manterServicoAreaStub;
    private static IManterServicoRemote manterServico;
    private static IManterServicoRemote manterServicoStub;
    private static IManterUsuarioRemote manterUsuario;
    private static IManterUsuarioRemote manterUsuarioStub;

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
        controlarDespesas = new ControlarDespesasAdapter();
        controlarDespesasStub = (IControlarDespesasRemote) UnicastRemoteObject.exportObject(controlarDespesas, 0);
        registro.rebind("ControlarDespesas", controlarDespesasStub);

        controlarHospedagem = new ControlarHospedagemAdapter();
        controlarHospedagemStub = (IControlarHospedagemRemote) UnicastRemoteObject.exportObject(controlarHospedagem, 0);
        registro.rebind("ControlarHospedagem", controlarHospedagemStub);

        manterCargo = new ManterCargoAdapter();
        manterCargoStub = (IManterCargoRemote) UnicastRemoteObject.exportObject(manterCargo, 0);
        registro.rebind("ManterCargo", manterCargoStub);

        manterCategoriaQuarto = new ManterCategoriaQuartoAdapter();
        manterCategoriaQuartoStub = (IManterCategoriaQuartoRemote) UnicastRemoteObject.exportObject(manterCategoriaQuarto, 0);
        registro.rebind("ManterCategoriaQuarto", manterCategoriaQuartoStub);

        manterHospede = new ManterHospedeAdapter();
        manterHospedeStub = (IManterHospedeRemote) UnicastRemoteObject.exportObject(manterHospede, 0);
        registro.rebind("ManterHospede", manterHospedeStub);

        manterItemConforto = new ManterItemConfortoAdapter();
        manterItemConfortoStub = (IManterItemConfortoRemote) UnicastRemoteObject.exportObject(manterItemConforto, 0);
        registro.rebind("ManterItemConforto", manterItemConfortoStub);

        manterQuarto = new ManterQuartoAdapter();
        manterQuartoStub = (IManterQuartoRemote) UnicastRemoteObject.exportObject(manterQuarto, 0);
        registro.rebind("ManterQuarto", manterQuartoStub);

        manterServicoArea = new ManterServicoAreaAdapter();
        manterServicoAreaStub = (IManterServicoAreaRemote) UnicastRemoteObject.exportObject(manterServicoArea, 0);
        registro.rebind("ManterServicoArea", manterServicoAreaStub);

        manterServico = new ManterServicoAdapter();
        manterServicoStub = (IManterServicoRemote) UnicastRemoteObject.exportObject(manterServico, 0);
        registro.rebind("ManterServico", manterServicoStub);

        manterUsuario = new ManterUsuarioAdapter();
        manterUsuarioStub = (IManterUsuarioRemote) UnicastRemoteObject.exportObject(manterUsuario, 0);
        registro.rebind("ManterUsuario", manterUsuarioStub);

    }

    private static void removeStubs() throws NoSuchObjectException {
        UnicastRemoteObject.unexportObject(registro, true);
    }
}
