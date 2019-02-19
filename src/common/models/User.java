package common.models;

import common.Callback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

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

    void notifyCallback(String message) throws RemoteException;

    boolean isFollowing(User user) throws RemoteException;

    Map<User, List<Tweet>> getMessages() throws RemoteException;

    void addMessage(User user, Tweet message) throws RemoteException;

    List<Tweet> getConversation(User user) throws RemoteException;

    List<Tweet> getTimeline() throws RemoteException;

    void setNotificationCallback(Callback callback) throws RemoteException;
}
