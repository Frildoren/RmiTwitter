package common.impl;

import common.UserManager;
import common.models.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagerImpl extends UnicastRemoteObject implements UserManager  {

    Map<String, User> usersConnected = new HashMap<>();
    Map<String, User> usersRegistered = new HashMap<>();

    public UserManagerImpl() throws RemoteException {
    }

    @Override
    public User connect(String nick, String password) throws RemoteException {

        User user = null;

        if((user=usersRegistered.get(nick)) != null) {
            if(user.getPassword().equals(password))
                usersConnected.put(nick, user);
        }

        return user;
    }

    @Override
    public User register(String nick, String name, String password) throws RemoteException {

        User user = null;

        if(!usersRegistered.containsKey(nick)) {
            user = new User();

            user.setNick(nick);
            user.setName(name);
            user.setPassword(password);

            usersRegistered.put(nick, user);
        }

        return user;

    }

    @Override
    public boolean disconnect(String nick) throws RemoteException {

        return usersConnected.remove(nick) != null;

    }
}
