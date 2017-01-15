package client.profile.impl;


import client.base.impl.BasePresenter;
import client.profile.ProfilePresenter;
import client.profile.ProfileView;
import common.models.User;

public class ProfilePresenterImpl extends BasePresenter<ProfileView> implements ProfilePresenter {
    @Override
    public void setTitle(String title) {

    }

    @Override
    public void onUserFollowing(User user) {

    }

    @Override
    protected ProfileView createView() {
        return null;
    }
}
