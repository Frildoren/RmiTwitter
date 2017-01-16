package common.models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface User extends Remote {

    String getName() throws RemoteException;

    void setName(String name) throws RemoteException;

    String getNick() throws RemoteException;

    void setNick(String nick) throws RemoteException;

    String getPassword() throws RemoteException;

    void setPassword(String password) throws RemoteException;

    List<Tweet> getTweets() throws RemoteException;

    void addTweet(Tweet tweet) throws RemoteException;

    List<User> getFollowing() throws RemoteException;

    List<User> getFollowers() throws RemoteException;

    void follow(User user) throws RemoteException;

    void followed(User user) throws RemoteException;

    void unfollow(User user) throws RemoteException;

    void unfollowed(User user) throws RemoteException;

    boolean isFollowing(User user) throws RemoteException;

    List<Tweet> getMessages() throws RemoteException;

    void addMessage(Tweet message) throws RemoteException;

    List<Tweet> getTimeline() throws RemoteException;
}
