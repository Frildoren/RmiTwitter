package common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Callback extends Remote, Serializable {

    void onCall(String message) throws RemoteException;

}
