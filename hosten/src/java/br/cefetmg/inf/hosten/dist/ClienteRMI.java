package br.cefetmg.inf.hosten.dist;

import br.cefetmg.inf.util.dist.ConstantesDist;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteRMI {

    private static ClienteRMI cliente = null;

    private String tipoObjeto;
    private Registry registro;

    private ClienteRMI() {
        try {
            registro = LocateRegistry.getRegistry(
                    ConstantesDist.HOST, ConstantesDist.PORTA
            );

        } catch (Exception ex) {
            Logger.getLogger(ClienteRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // static method to create instance of Singleton class 
    public static ClienteRMI getInstance() {
        if (cliente == null) {
            cliente = new ClienteRMI();
        }

        return cliente;
    }

    public Object receberObjeto(String tipoObjeto) {
        try {
            return registro.lookup(tipoObjeto);

        } catch (RemoteException ex) {
            Logger.getLogger(ClienteRMI.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteRMI.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
