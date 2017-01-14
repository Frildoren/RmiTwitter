package server;

import common.UserManager;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    String NAME = "twitter.server";

    UserManager getUserManager() throws RemoteException;
}
