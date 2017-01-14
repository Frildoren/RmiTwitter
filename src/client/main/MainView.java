package client.main;

import client.base.FrameView;
import common.models.User;

public interface MainView extends FrameView<MainPresenter> {

    void setUser(User user);

}
