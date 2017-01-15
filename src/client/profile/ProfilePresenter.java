package client.profile;


import client.base.Presenter;
import common.models.User;

import java.util.List;

public interface ProfilePresenter extends Presenter<ProfileView> {

    void setUser(User user);

    void onUserFollow(User user);

    void onUserUnfollow(User user);

    void sendMessage(User user);
}
