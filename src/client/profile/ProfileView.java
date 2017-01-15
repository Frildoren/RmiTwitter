package client.profile;


import client.base.ParentView;
import client.base.View;
import common.models.User;

import java.util.List;

public interface ProfileView extends ParentView<ProfilePresenter> {

    void setUser(User user);

<<<<<<< HEAD
=======
    void setFollowVisible(boolean visible);

    void setUnfollowVisible(boolean visible);

    void setMessageVisible(boolean visible);
>>>>>>> 406d0afdf2eaa214cf5b99584ca3d547cab9f65f
}
