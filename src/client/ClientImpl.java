package client;

import client.base.impl.BasePresenter;
import client.login.impl.LoginPresenterImpl;
import server.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientImpl implements Client {

    private Server server;

    public void setServer(Server server){
        this.server = server;
    }

    public static void main(String[] args){

        String address = "127.0.0.1";
        if(args.length > 0){
            address = args[0];
        }

        System.setProperty("java.rmi.server.hostname", address);
        System.setProperty("java.rmi.server.codebase", "http://" + address + "/twitter/");
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");

        System.setProperty("java.security.policy", "twitter.policy");

        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            Registry register = LocateRegistry.getRegistry(address, Registry.REGISTRY_PORT);

            ClientImpl client = new ClientImpl();
            Server server = (Server) register.lookup( Server.NAME );
            client.setServer(server);

            BasePresenter.createPresenter(client, LoginPresenterImpl.class);

        } catch (RemoteException | NotBoundException e) {
            System.err.println("Error on client connection:");
            e.printStackTrace(System.err);
        }

    }


}
