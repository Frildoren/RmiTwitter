package client.profile.impl;


import client.base.View;
import client.base.impl.BasePresenter;
import client.profile.ProfilePresenter;
import client.profile.ProfileView;
import client.tweets.TweetsPresenter;
import client.tweets.impl.TweetsPresenterImpl;
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
    }

    @Override
    public void onUserFollowing(User user) {

    }

    @Override
    public void setNestedView(View view) {
        getView().setNestedView(view);
    }
}
