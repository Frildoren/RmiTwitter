package client.profile;


import client.base.ParentPresenter;
import client.base.Presenter;
import common.models.User;

public interface ProfilePresenter extends ParentPresenter<ProfileView> {

    void setUser(User user);

    void onUserFollow(User user);

    void onUserUnfollow(User user);

    void sendMessage(User user);
}
