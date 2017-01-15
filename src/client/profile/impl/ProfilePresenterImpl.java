package client.profile.impl;


import client.base.impl.BasePresenter;
import client.profile.ProfilePresenter;
import client.profile.ProfileView;
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
            getClient().getUser().follow(user);
            updateFollowing(user);
        } catch (RemoteException e) {
            getView().showError("Connection error");
            e.printStackTrace();
        }
    }

    @Override
    public void onUserUnfollow(User user) {
        try {
            getClient().getUser().unfollow(user);
            updateFollowing(user);
        } catch (RemoteException e) {
            getView().showError("Connection error");
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(User user) {
        getView().showError("Not implemented yet");
    }


}
