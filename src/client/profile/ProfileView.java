package client.profile;


import client.base.View;
import common.models.User;

import java.util.List;

public interface ProfileView extends View<ProfilePresenter>{

    void setUser(User user);
}
