package common.models;

import common.Callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class UserImpl extends UnicastRemoteObject implements User {

    private String name;
    private String nick;
    private String password;

    private List<Tweet> tweets;
    private List<User> following;
    private List<User> followed;
    private Callback callback;
    private Map<User, List<Tweet>> messages;

    public UserImpl() throws RemoteException {
        tweets = new ArrayList<>();
        following = new ArrayList<>();
        followed = new ArrayList<>();
        messages = new HashMap<>();
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
        notifyCallback(null);
        getFollowers().forEach(user -> {
            try {
                user.notifyCallback(getName() + " just tweeted!");
            } catch (RemoteException e) {}
        });
    }

    @Override
    public List<User> getFollowing() throws RemoteException {
        return following;
    }

    @Override
    public List<User> getFollowers() throws RemoteException {
        return followed;
    }

    @Override
    public void follow(User user) throws RemoteException {
        following.add(user);
        notifyCallback(null);
    }

    @Override
    public void followed(User user) throws RemoteException {
        followed.add(user);
        notifyCallback(user.getName() + " is now following you!");
    }

    @Override
    public void unfollow(User user) throws RemoteException {
        following.remove(user);
        notifyCallback(null);
    }

    @Override
    public void unfollowed(User user) throws RemoteException {
        followed.remove(user);
        notifyCallback(user.getName() + " is no longer following you!");
    }

    @Override
    public void notifyCallback(String message) throws RemoteException {
        if(callback != null){
            try {
                callback.onCall(message);
            } catch(RemoteException e){}
        }
    }

    @Override
    public boolean isFollowing(User user) throws RemoteException {
        return following.contains(user);
    }

    @Override
    public Map<User, List<Tweet>> getMessages() throws RemoteException {
        return messages;
    }

    @Override
    public void addMessage(User user, Tweet message) throws RemoteException {

        List<Tweet> messagesList = getMessages().get(user);

        if(messagesList == null) {
            messagesList = new ArrayList<>();
            messages.put(user, messagesList);
        }

        messagesList.add(message);

    }

    @Override
    public void setMessages(Map<User, List<Tweet>> messages) throws RemoteException {
        this.messages = messages;
    }

    @Override
    public List<Tweet> getTimeline() throws RemoteException {
        Map<Date, Tweet> tweetMap = new TreeMap<>();
        getTweets().forEach(tweet -> tweetMap.put(tweet.getDate(), tweet));
        getFollowing().forEach(user -> {
            try {
                user.getTweets().forEach(tweet -> tweetMap.put(tweet.getDate(), tweet));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        return new ArrayList<>(tweetMap.values());
    }

    @Override
    public void setNotificationCallback(Callback callback) throws RemoteException {
        this.callback = callback;
    }

}
