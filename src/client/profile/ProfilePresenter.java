package client.profile;


import client.base.Presenter;
import common.models.User;

import java.util.List;

public interface ProfilePresenter extends Presenter<ProfileView> {

    void setUser(User user);

    void onUserFollowing(User user);

}
