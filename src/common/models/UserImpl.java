package common.models;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class UserImpl extends UnicastRemoteObject implements User {

    private String name;
    private String nick;
    private String password;

    private List<Tweet> tweets;
    private List<User> following;

    public UserImpl() throws RemoteException {
        tweets = new ArrayList<>();
        following = new ArrayList<>();
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String getNick() throws RemoteException {
        return nick;
    }

    @Override
    public void setNick(String nick) throws RemoteException {
        this.nick = nick;
    }

    @Override
    public String getPassword() throws RemoteException {
        return password;
    }

    @Override
    public void setPassword(String password) throws RemoteException {
        this.password = password;
    }

    @Override
    public List<Tweet> getTweets() throws RemoteException {
        return tweets;
    }

    @Override
    public void setTweets(List<Tweet> tweets) throws RemoteException {
        this.tweets = tweets;
    }

    @Override
    public List<User> getFollowing() throws RemoteException {
        return following;
    }

    @Override
    public void setFollowing(List<User> following) throws RemoteException {
        this.following = following;
    }

}
