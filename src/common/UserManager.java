package common;

import common.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserManager extends Remote {

    User connect(String nick, String password) throws RemoteException;
    User register(String nick, String name, String password) throws RemoteException;
    boolean disconnect(String nick) throws RemoteException;

    List<User> search(String search) throws RemoteException;
}
