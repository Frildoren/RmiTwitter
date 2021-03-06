package client.main;

import client.base.FrameView;
import client.base.ParentView;
import client.base.View;
import common.models.User;

import java.util.Collection;

public interface MainView extends FrameView<MainPresenter>, ParentView<MainPresenter> {

    void setUserName(String name);
    void setUserNick(String nick);
    void setUserTweets(int tweets);
    void setUserFollowing(int following);
    void setUserFollowers(int size);
}
