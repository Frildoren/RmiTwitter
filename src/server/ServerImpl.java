package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {



    protected ServerImpl() throws RemoteException {
        
    }

    public static void main(String[] args){

        String address = "127.0.0.1";
        if(args.length > 0){
            address = args[0];
        }

        System.setProperty("java.rmi.server.hostname", address);
        System.setProperty("java.rmi.server.codebase", "http://" + address + "/twitter/");
        System.setProperty("java.security.policy", "twitter.policy");

        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            Registry register = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            register.rebind(NAME, new ServerImpl());

            System.out.println("Server ready on "+ address + "...");

        } catch (RemoteException e) {
            System.err.println("Error on server startup:");
            e.printStackTrace(System.err);
        }
    }

}
