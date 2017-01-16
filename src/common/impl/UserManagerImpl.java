package common.impl;

import common.UserManager;
import common.models.Tweet;
import common.models.User;
import common.models.UserImpl;

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

        User user = usersRegistered.get(nick);

        if(user != null && user.getPassword().equals(password)) {
            usersConnected.put(nick, user);
            return user;
        } else {
            return null;
        }

    }

    @Override
    public User register(String nick, String name, String password) throws RemoteException {

        User user = null;

        if(!usersRegistered.containsKey(nick)) {
            user = new UserImpl();

            user.setNick(nick);
            user.setName(name);
            user.setPassword(password);

            usersRegistered.put(nick, user);
            usersConnected.put(nick, user);
        }

        return user;

    }

    @Override
    public boolean disconnect(String nick) throws RemoteException {

        return usersConnected.remove(nick) != null;

    }

    @Override
    public List<User> search (String search) throws RemoteException {

        List<User> users = new ArrayList<>();

        for(String i : usersRegistered.keySet()) {
            if(i.contains(search)) {
                users.add(usersRegistered.get(i));
            }
        }

        return users;

    }

    @Override
    public void sendMessage (User dest, Tweet message) throws RemoteException {
        dest.addMessage(dest, message);

    }

    @Override
    public void follow(User a, User b) throws RemoteException {
        if(!a.isFollowing(b)) {
            a.follow(b);
            b.followed(a);
        }
    }

    @Override
    public void unfollow(User a, User b) throws RemoteException{
        if(a.isFollowing(b)) {
            a.unfollow(b);
            b.unfollowed(a);
        }
    }
}
