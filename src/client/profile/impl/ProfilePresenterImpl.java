package client.profile.impl;


import client.base.View;
import client.base.impl.BasePresenter;
import client.profile.ProfilePresenter;
import client.profile.ProfileView;
import client.tweets.TweetsPresenter;
import client.tweets.impl.TweetsPresenterImpl;
import client.userMessages.UserMessagesPresenter;
import client.userMessages.impl.UserMessagesPresenterImpl;
import common.models.User;

import java.rmi.RemoteException;

public class ProfilePresenterImpl extends BasePresenter<ProfileView> implements ProfilePresenter {

    @Override
    protected ProfileView createView() {
        ProfileView profileView = new ProfileViewImpl();
        profileView.create(this);
        return profileView;
    }

    @Override
    public void setUser(User user) {
        getView().setUser(user);

        try {
            TweetsPresenter tweetsPresenter = createPresenter(TweetsPresenterImpl.class);
            tweetsPresenter.setTweets(user.getTweets());
            getView().setNestedView(tweetsPresenter.getView());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        updateFollowing(user);
    }

    private void updateFollowing(User user){
        try {
            if(user.getNick().equals(getClient().getUser().getNick())){
                getView().setFollowVisible(false);
                getView().setUnfollowVisible(false);
                getView().setMessageVisible(false);
            } else {
                boolean following = getClient().getUser().isFollowing(user);
                getView().setFollowVisible(!following);
                getView().setUnfollowVisible(following);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUserFollow(User user) {
        try {
            getClient().getServer().getUserManager().follow(getClient().getUser(), user);
            updateFollowing(user);
        } catch (RemoteException e) {
            getView().showError("Connection error");
            e.printStackTrace();
        }
    }

    @Override
    public void onUserUnfollow(User user) {
        try {
            getClient().getServer().getUserManager().unfollow(getClient().getUser(), user);
            updateFollowing(user);
        } catch (RemoteException e) {
            getView().showError("Connection error");
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(User user) {
        UserMessagesPresenter userMessagesPresenter = createPresenter(UserMessagesPresenterImpl.class);
        userMessagesPresenter.setUser(user);
        getParentPresenter().setNestedView(userMessagesPresenter.getView());
    }

    @Override
    public void setNestedView(View view) {
        getView().setNestedView(view);
    }
}
