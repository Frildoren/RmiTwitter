package client.profile;


import client.base.ParentPresenter;
import common.models.User;

public interface ProfilePresenter extends ParentPresenter<ProfileView> {

    void setUser(User user);
    void onUserFollowing(User user);

}
