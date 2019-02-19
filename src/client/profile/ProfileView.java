package client.profile;


import client.base.ParentView;
import client.base.View;
import common.models.User;

import java.util.List;

public interface ProfileView extends ParentView<ProfilePresenter> {

    void setUser(User user);

    void setFollowVisible(boolean visible);

    void setUnfollowVisible(boolean visible);

    void setMessageVisible(boolean visible);

}
