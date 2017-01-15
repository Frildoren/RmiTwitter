package client.profile;


import client.base.Presenter;
import common.models.User;

import java.util.List;

public interface ProfilePresenter extends Presenter<ProfileView> {

    void setTitle(String title);

    void onUserFollowing(User user);

}
