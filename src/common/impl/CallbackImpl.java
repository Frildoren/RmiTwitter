package common.impl;

import common.Callback;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class CallbackImpl extends UnicastRemoteObject implements Serializable, Callback {
    protected CallbackImpl() throws RemoteException {}
}
