package client.userMessages.impl;


import client.base.View;
import client.base.impl.BasePresenter;
import client.tweets.TweetsPresenter;
import client.tweets.impl.TweetsPresenterImpl;
import client.userMessages.UserMessagesPresenter;
import client.userMessages.UserMessagesView;
import common.models.Tweet;
import common.models.User;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class UserMessagesPresenterImpl extends BasePresenter<UserMessagesView> implements UserMessagesPresenter {

    private User user;

    @Override
    protected UserMessagesView createView() {
        UserMessagesView userMessagesView = new UserMessagesViewImpl();
        userMessagesView.create(this);
        return userMessagesView;
    }

    @Override
    public void setNestedView(View view) {
        getView().setNestedView(view);
    }

    @Override
    public void sendMessage(String tweet) {

        Tweet t = new Tweet();
        t.setUser(getClient().getUser());
        t.setDate(new Date(System.currentTimeMillis()));
        t.setTweet(tweet);

        try {
            getClient().getServer().getUserManager().sendMessage(user, t);
            getClient().getServer().getUserManager().sendMessage(getClient().getUser(), t);
            showMessages();
        } catch (RemoteException e) {
            getView().showError("Error sending message");
            e.printStackTrace();
        }

    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    private void showMessages(){

        try {
            List<Tweet> list = getClient().getUser().getConversation(getClient().getUser());
            TweetsPresenter tweetsPresenter = createPresenter(TweetsPresenterImpl.class);
            tweetsPresenter.setParentPresenter(this);
            tweetsPresenter.setTweets(list);

            getView().setNestedView(tweetsPresenter.getView());
        } catch (RemoteException e) {
            getView().showError("Could not load messages");
            e.printStackTrace();
        }

    }


}
