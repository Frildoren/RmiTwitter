package client.main;

import client.base.FrameView;
import client.base.View;
import common.models.User;

public interface MainView extends FrameView<MainPresenter> {

    void setUser(User user);
    void setNestedView(View view);

}
