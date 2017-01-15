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
    private List<Tweet> messages;

    public UserImpl() throws RemoteException {
        tweets = new ArrayList<>();
        following = new ArrayList<>();
        messages = new ArrayList<>();

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
    public void addTweet(Tweet tweet) throws RemoteException {
        getTweets().add(tweet);
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

    @Override
    public void follow(User user) throws RemoteException {
        if(!isFollowing(user)) {
            following.add(user);
        }
    }

    @Override
    public void unfollow(User user) throws RemoteException {
        if (isFollowing(user)) {
            following.remove(user);
        }
    }

    @Override
    public boolean isFollowing(User user) throws RemoteException {
        return following.contains(user);
    }

    @Override
    public List<Tweet> getMessages() throws RemoteException {
        return messages;
    }

    @Override
    public void addMessage(Tweet message) throws RemoteException {
        getMessages().add(message);
    }

    @Override
    public void setMessages(List<Tweet> messages) throws RemoteException {
        this.messages = messages;
    }

}
