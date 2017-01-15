package client.profile.impl;


import client.base.impl.BasePresenter;
import client.profile.ProfilePresenter;
import client.profile.ProfileView;
import common.models.User;

public class ProfilePresenterImpl extends BasePresenter<ProfileView> implements ProfilePresenter {

    @Override
    protected ProfileView createView() {
        ProfileView profileView = new ProfileViewImpl();
        profileView.create(this);
        return profileView;
    }

    @Override
    public void setUser(User user) {
        
    }

    @Override
    public void onUserFollowing(User user) {

    }


}
